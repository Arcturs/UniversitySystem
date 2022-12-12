package ru.vsu.csf.asashina.universitysystem.model.request;

import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ParticipationRequest {

    @NotNull
    private Long participantSecuritySocialNumber;

    @NotNull
    private Integer hours;
}
