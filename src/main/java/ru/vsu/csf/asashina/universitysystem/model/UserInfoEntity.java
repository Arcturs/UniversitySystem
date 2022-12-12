package ru.vsu.csf.asashina.universitysystem.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

//TODO: diagrams -> tests
@Entity
@Table(name = "user_info")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserInfoEntity {

    @Id
    private Long socialSecurityNumber;

    @Column(nullable = false)
    private String password;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.MERGE)
    @JoinTable(name = "user_role",
            joinColumns = {@JoinColumn(name = "social_security_number")},
            inverseJoinColumns = {@JoinColumn(name = "role_id")})
    private Set<RoleEntity> roles = new HashSet<>();

    public UserInfoEntity(Long socialSecurityNumber, String password) {
        this.socialSecurityNumber = socialSecurityNumber;
        this.password = password;
    }
}
