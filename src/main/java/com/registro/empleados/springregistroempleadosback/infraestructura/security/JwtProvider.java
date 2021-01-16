package com.registro.empleados.springregistroempleadosback.infraestructura.security;

import com.registro.empleados.springregistroempleadosback.dominio.excepciones.CertificadoException;
import io.jsonwebtoken.Jwts;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.InputStream;
import java.security.*;
import java.security.cert.CertificateException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.Optional;

@Service
public class JwtProvider {

    private KeyStore keyStore;

    @Value("${jwt.expiration.time}")
    private Long expiracionJwt;

    @PostConstruct
    public void init() {
        try {
            keyStore = KeyStore.getInstance("JKS");
            InputStream resourceAsStream = getClass().getResourceAsStream("/registrarEmpleadosApp.jks");
            keyStore.load(resourceAsStream, "felipefranco".toCharArray());
        } catch (KeyStoreException | CertificateException | NoSuchAlgorithmException | IOException e) {
            throw new CertificadoException("Ocurio una excepcion mientras cargaba la keystore", e);
        }
    }

    public String generarToken(Authentication authentication) {
        String username = ((UserDetails) authentication.getPrincipal()).getUsername();
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getLlavePrivada())
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expiracionJwt).atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }

    public String generarTokenConUsuario(String usuario) {
        return Jwts.builder()
                .setSubject(usuario)
                .setIssuedAt(Date.from(Instant.now()))
                .signWith(getLlavePrivada())
                .setExpiration(Date.from(LocalDateTime.now().plusSeconds(expiracionJwt).atZone(ZoneId.systemDefault()).toInstant()))
                .compact();
    }

    private PrivateKey getLlavePrivada() {
        try {
            return (PrivateKey) keyStore.getKey("https-registerPerson", "felipefranco".toCharArray());
        } catch (KeyStoreException | NoSuchAlgorithmException | UnrecoverableKeyException e) {
            throw new CertificadoException("Ocurrio una excepcion mientras recuperaba la llave privada del keystore", e);
        }
    }

    public boolean validarToken(String jwt) {
        Jwts.parser().setSigningKey(getLlavePublica())
                .parseClaimsJws(jwt);
        return true;
    }

    private PublicKey getLlavePublica() {
        try {
            return keyStore.getCertificate("https-registerPerson").getPublicKey();
        } catch (KeyStoreException e) {
            throw new CertificadoException("Ocurrio una excepcion mientras recuperaba la llave publica", e);        }
    }

    public Optional<String> getSubDelToken(String token) {
        try {
            return Optional.ofNullable(Jwts.parser()
                    .setSigningKey(getLlavePublica())
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject());
        } catch (Exception e) {
            throw new CertificadoException("Ocurrio un error obteniendo el cuerpo de JWt", e);
        }
    }

    public Long getExpiracionJwt() {
        return expiracionJwt;
    }

}
