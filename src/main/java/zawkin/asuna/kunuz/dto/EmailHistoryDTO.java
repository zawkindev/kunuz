package zawkin.asuna.kunuz.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.NonNull;
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
    @NonNull
    private LocalDateTime createdDate;
}
