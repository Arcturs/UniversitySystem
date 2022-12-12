package ru.vsu.csf.asashina.universitysystem.filter;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import ru.vsu.csf.asashina.universitysystem.model.UserInfoEntity;
import ru.vsu.csf.asashina.universitysystem.service.UserService;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static ru.vsu.csf.asashina.universitysystem.filter.AuthorizationFilter.AUTH_HEADER;

@Component
@RequiredArgsConstructor
public class TokenFilter {

    private final UserService userService;

    private final ObjectMapper objectMapper;

    @Value("${security.jwt.secretKey}")
    private String jwtKey;

    public void authenticate(String authHeader) {
        var decodedJWT = decodeJWT(authHeader);
        UserInfoEntity user = userService.findUserById(Long.parseLong(decodedJWT.getSubject()));
        setAuthenticationToken(user, decodedJWT);
    }

    public DecodedJWT decodeJWT(String authHeader) {
        String token = authHeader;
        if (authHeader.contains(AUTH_HEADER)) {
            token = authHeader.substring(AUTH_HEADER.length());
        }
        var algorithm = Algorithm.HMAC256(jwtKey.getBytes());
        return JWT.require(algorithm).build().verify(token);
    }

    private void setAuthenticationToken(UserInfoEntity user, DecodedJWT decodedJWT) {
        Long username = user.getSocialSecurityNumber();
        String password = user.getPassword();
        String[] roles = decodedJWT.getClaim("roles").asArray(String.class);
        List<SimpleGrantedAuthority> authorities = stream(roles)
                .map(SimpleGrantedAuthority::new)
                .collect(Collectors.toList());

        var authenticationToken = new UsernamePasswordAuthenticationToken(username, password, authorities);
        SecurityContextHolder.getContext().setAuthentication(authenticationToken);
    }

    public void sendErrorMessage(HttpServletResponse response, String message) throws IOException {
        response.setStatus(403);
        PrintWriter out = response.getWriter();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        out.print(objectMapper.writeValueAsString(message));
        out.flush();
    }
}
