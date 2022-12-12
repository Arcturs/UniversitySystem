package ru.vsu.csf.asashina.universitysystem.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class RegistrationForm {

    @NotNull
    private Long securitySocialNumber;

    @NotBlank
    @Size(min = 8, max = 10)
    private String password;

    @NotBlank
    @Size(min = 8, max = 10)
    private String repeatPassword;
}
