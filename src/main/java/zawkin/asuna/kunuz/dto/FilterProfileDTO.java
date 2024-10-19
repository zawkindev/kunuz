package zawkin.asuna.kunuz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class FilterProfileDTO {
    private String name;
    private String surname;
    private Integer phone;
    private String role;
    private LocalDateTime createdDateFrom;
    private LocalDateTime createdDateTo;
}
