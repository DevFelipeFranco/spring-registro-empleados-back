package com.registro.empleados.springregistroempleadosback.dominio.servicio;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.PermisoLecturaException;
import com.registro.empleados.springregistroempleadosback.dominio.excepciones.UsuarioNoExisteException;
import com.registro.empleados.springregistroempleadosback.dominio.modelo.Usuario;
import com.registro.empleados.springregistroempleadosback.dominio.transformadores.UsuarioTransformer;
import com.registro.empleados.springregistroempleadosback.infraestructura.repositorio.UsuarioRepositorioMySQL;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static com.registro.empleados.springregistroempleadosback.dominio.constants.FileConstant.*;
import static java.nio.file.StandardCopyOption.REPLACE_EXISTING;

@Service
@AllArgsConstructor
@Slf4j
public class ProcesarImagenUploadService {

    private final UsuarioRepositorioMySQL usuarioRepositorioMySQL;

    @Transactional
    public Usuario uploadImagenPerfil(MultipartFile imagenPerfil, Long id) throws IOException {
        Usuario usuario = usuarioRepositorioMySQL.buscarUsuarioPorId(id)
                .orElseThrow(() -> new UsuarioNoExisteException("El usuario no se encuentra registrado en DB"));

        if (imagenPerfil != null) {
            Path userFolder = getPath(usuario.getUsuario());
            log.info("######## RUTA A GUARDAR IMAGEN DE PERFIL: " + userFolder);

            crearCarpetaPorUsuario(userFolder);

            Files.deleteIfExists(Paths.get(userFolder + usuario.getUsuario() + DOT + JPG_EXTENCION));
            Files.copy(imagenPerfil.getInputStream(), userFolder.resolve(usuario.getUsuario() + DOT + JPG_EXTENCION), REPLACE_EXISTING);
            usuario.setImagenPerfilUrl(setImagenPerfilUrl(usuario.getUsuario()));

            usuarioRepositorioMySQL.actualizarRutaImagenPerfil(setImagenPerfilUrl(usuario.getUsuario()), usuario.getIdUsuario());
            log.info(FILE_SAVED_IN_FILE_SYSTEM + imagenPerfil.getOriginalFilename());
        }
        return UsuarioTransformer.usuarioSinClaveNiRoles(usuario);

    }

    private void crearCarpetaPorUsuario(Path userFolder) throws IOException {
        if (!Files.exists(userFolder)) {
            Files.createDirectories(userFolder);
            log.info("######### " + DIRECTORY_CREATE + userFolder );
        }
    }

    public Path getPath(String usuario) {
        return Paths.get(USER_FOLDER).resolve(usuario).toAbsolutePath().normalize();
    }

    public String getImagenPerfilTemporal(String usuario) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(RUTA_IMAGEN_PERFIL_POR_DEFECTO + usuario).toUriString();
    }

    private String setImagenPerfilUrl(String usuario) {
        return ServletUriComponentsBuilder.fromCurrentContextPath().path(USER_IMAGE_PATH + usuario + FORWARD_SLASH + usuario + DOT + JPG_EXTENCION).toUriString();
    }
}
