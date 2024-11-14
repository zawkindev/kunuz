package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.ProfileRoleEnum;
import zawkin.asuna.kunuz.enums.ProfileStatus;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(unique = true)
    private String email;

    @Column(unique = true)
    private String phone;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProfileRoleEnum role;

    @Column(name = "visible", nullable = false)
    private Boolean visible = Boolean.TRUE;

    @Column(name = "created_date", nullable = false, updatable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    @Column(name = "photo_id")
    private Integer photoId;
}
