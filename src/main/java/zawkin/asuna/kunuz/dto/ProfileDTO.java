package zawkin.asuna.kunuz.dto;

import lombok.Getter;
import lombok.Setter;

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
    private String status;
    private String role;
    private Boolean visible;
    private LocalDateTime createdDate;
    private Integer photoId;
}
