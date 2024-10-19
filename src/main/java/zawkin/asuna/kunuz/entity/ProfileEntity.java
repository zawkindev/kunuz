package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Column
    private String status;
    @Column
    private String role;
    @Column
    private Boolean visible;
    @Column
    private LocalDateTime createdDate;
    @Column
    private Integer photoId;
}
