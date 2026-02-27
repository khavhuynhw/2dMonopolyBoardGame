package org.kh.monopolynotificationservice.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "notification_templates",
       uniqueConstraints = @UniqueConstraint(name = "uq_template_code", columnNames = "code"))
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class NotificationTemplate {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false, length = 50)
    private String code;

    @Column(nullable = false, length = 100)
    private String title;

    /** Body with {variable} placeholders, e.g. "{sender} sent you a friend request" */
    @Column(nullable = false, length = 255)
    private String body;
}
