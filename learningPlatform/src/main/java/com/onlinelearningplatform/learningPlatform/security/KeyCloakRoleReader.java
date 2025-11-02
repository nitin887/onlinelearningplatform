package com.onlinelearningplatform.learningPlatform.security;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;

public class KeyCloakRoleReader implements Converter<Jwt, Collection<GrantedAuthority>> {

    @Override
    @Nullable
    public Collection<GrantedAuthority> convert(Jwt source) {
        Map<String, Object> realmAccess = (Map<String, Object>) source.getClaims().get("realm_access");
        if (realmAccess == null || realmAccess.isEmpty()) {
            return List.of();
        }
        return ((List<String>) realmAccess.get("roles")).stream()
                .map(roleName -> "ROLE_" + roleName.toUpperCase()) // Prefix with ROLE_ and convert to uppercase
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());
    }
}