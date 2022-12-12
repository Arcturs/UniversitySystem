package ru.vsu.csf.asashina.universitysystem.controller;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.vsu.csf.asashina.universitysystem.model.request.LoginForm;
import ru.vsu.csf.asashina.universitysystem.model.request.RegistrationForm;
import ru.vsu.csf.asashina.universitysystem.service.UserService;

import java.util.Map;

@RestController
@RequestMapping("/user")
@AllArgsConstructor
public class UserController {

    public static final String ACCESS_TOKEN = "accessToken";

    private final UserService service;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody @Valid RegistrationForm form) {
        return ResponseEntity.ok(Map.of(ACCESS_TOKEN, service.registerUser(form)));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody @Valid LoginForm form) {
        return ResponseEntity.ok(Map.of(ACCESS_TOKEN, service.login(form)));
    }
}
