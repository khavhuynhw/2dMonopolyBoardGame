package org.kh.monopolyidentityservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "roles",
       uniqueConstraints = @UniqueConstraint(name = "uq_roles_name", columnNames = "name"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 30)
    @Enumerated(EnumType.STRING)
    private RoleName name;

    public enum RoleName {
        ADMIN, MODERATOR, PLAYER
    }
}
