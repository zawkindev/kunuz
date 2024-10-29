package zawkin.asuna.kunuz.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.PhoneTypeEnum;

import java.time.LocalDateTime;

@Getter
@Setter
public class SmsHistoryDTO {
    @NotNull
    private String id;
    @NotBlank
    @Size(min = 12, max = 12)
    private String phone;
    @NotBlank
    private String message;
    @NotNull
    private LocalDateTime createdDate;
    @NotNull
    private PhoneTypeEnum type;
    @NotNull
    private String code;
}
