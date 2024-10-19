package zawkin.asuna.kunuz.dto;

import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.LanguageEnum;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryDTO {
    private Integer id;
    private Integer orderNumber;
    private LanguageEnum nameUz;
    private LanguageEnum nameEn;
    private LanguageEnum nameRu;
    private Boolean visible;
    private LocalDateTime createdDate;
}
