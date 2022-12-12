package ru.vsu.csf.asashina.universitysystem.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class FacultyRequest {

    @NotBlank
    private String name;

    private Long deanSocialSecurityNumber;
}
