package ru.vsu.csf.asashina.universitysystem.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.model.UserInfoEntity;

import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TokenService {

    @Value("${security.jwt.secretKey}")
    private String jwtKey;

    @Value("${security.jwt.expiresMs.accessToken}")
    private Integer accessTokenExpireTime;

    @Transactional
    public String createAccessToken(UserInfoEntity user) {
        var algorithm = Algorithm.HMAC256(jwtKey.getBytes());
        return JWT.create()
                .withSubject(user.getSocialSecurityNumber().toString())
                .withExpiresAt(new Date(System.currentTimeMillis() + accessTokenExpireTime))
                .withIssuer("university-system-service")
                .withClaim("roles", user.getRoles().stream()
                        .map(GrantedAuthority::getAuthority)
                        .collect(Collectors.toList()))
                .sign(algorithm);
    }
}
