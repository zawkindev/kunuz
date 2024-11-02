package zawkin.asuna.kunuz.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdateProfileDetailDTO {
    @NotBlank
    private String name;
    @NotBlank
    private String surname;
}
