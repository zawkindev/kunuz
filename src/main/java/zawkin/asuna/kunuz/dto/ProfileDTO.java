package zawkin.asuna.kunuz.dto;

import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.ProfileRoleEnum;
import zawkin.asuna.kunuz.enums.ProfileStatus;

import java.time.LocalDateTime;

@Getter
@Setter
public class ProfileDTO {
    private Integer id;
    private String name;
    private String surname;
    private String email;
    private Integer phone;
    private String password;
    private ProfileStatus status;
    private ProfileRoleEnum role;
    private Boolean visible;
    private LocalDateTime createdDate;
    private Integer photoId;
}
