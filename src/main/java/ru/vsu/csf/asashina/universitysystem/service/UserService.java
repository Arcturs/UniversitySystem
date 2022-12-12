package ru.vsu.csf.asashina.universitysystem.service;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vsu.csf.asashina.universitysystem.model.UserInfoEntity;
import ru.vsu.csf.asashina.universitysystem.model.request.LoginForm;
import ru.vsu.csf.asashina.universitysystem.model.request.RegistrationForm;
import ru.vsu.csf.asashina.universitysystem.repository.UserRepository;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository repository;

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    private final TokenService tokenService;

    @Transactional
    public String registerUser(RegistrationForm form) {
        if (!form.getPassword().equals(form.getRepeatPassword())) {
            throw new IllegalArgumentException("Passwords do not match");
        }
        if (repository.existsUserInfoEntityBySocialSecurityNumber(form.getSecuritySocialNumber())) {
            throw new IllegalArgumentException("User already exists");
        }
        UserInfoEntity user = repository.save(new UserInfoEntity(form.getSecuritySocialNumber(),
                bCryptPasswordEncoder.encode(form.getPassword())));
        return tokenService.createAccessToken(user);
    }

    @SneakyThrows
    public UserInfoEntity findUserById(Long id) {
        return repository.findById(id).orElseThrow(ClassNotFoundException::new);
    }

    public String login(LoginForm form) {
        UserInfoEntity user = findUserById(form.getSecuritySocialNumber());
        if (!user.getPassword().equals(form.getPassword())) {
            throw new IllegalArgumentException("Wrong password");
        }
        return tokenService.createAccessToken(user);
    }
}
