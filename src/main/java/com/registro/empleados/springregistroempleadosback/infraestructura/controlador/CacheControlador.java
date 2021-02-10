package com.registro.empleados.springregistroempleadosback.infraestructura.controlador;

import lombok.AllArgsConstructor;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/cache")
@AllArgsConstructor
public class CacheControlador {

    private final CacheManager cacheManager;

    @DeleteMapping(value = "")
    public ResponseEntity<String> clearAllCache() {
        cacheManager.getCacheNames().forEach(this::clearCacheFromCacheName);
        return ResponseEntity.ok("Se elimino la CACHE!");
    }

    @DeleteMapping(value = "/{cacheName}")
    public ResponseEntity<String> clearCache(@PathVariable("cacheName") String cacheName) {
        return clearCacheFromCacheName(cacheName) ?
                ResponseEntity.ok("Se elimino la cache: " + cacheName) :
                ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontro la Cache: " + cacheName);
    }

    private Boolean clearCacheFromCacheName(final String cacheName) {
        final Cache cache = cacheManager.getCache(cacheName);
        if (cacheExists(cache)) {
            cache.clear();
            return true;
        }
        return false;
    }

    private Boolean cacheExists(final Cache cache) {
        return cache != null;
    }
}
