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
public class CourseRequest {

    @NotBlank
    @Size(max = 255)
    private String name;

    @NotNull
    private Float weeklyDuration;
}
