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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column
    private String name;
    @Column
    private String surname;
    @Column
    private String email;
    @Column
    private Integer phone;
    @Column
    private String password;

    @Enumerated(EnumType.STRING)
    @Column
    private ProfileStatus status;
    @Enumerated(EnumType.STRING)
    @Column
    private ProfileRoleEnum role;

    @Column
    private Boolean visible;
    @Column
    private LocalDateTime createdDate;
    @Column
    private Integer photoId;
}
