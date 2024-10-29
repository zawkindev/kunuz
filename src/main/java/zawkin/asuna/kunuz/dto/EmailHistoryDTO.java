package zawkin.asuna.kunuz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class EmailHistoryDTO {
    private String id;
    @NotBlank
    private String message;
    @Email
    private String email;
    @NotNull
    private LocalDateTime createdDate;
}
