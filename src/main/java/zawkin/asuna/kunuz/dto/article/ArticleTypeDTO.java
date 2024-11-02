package zawkin.asuna.kunuz.dto.article;

import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.LanguageEnum;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleTypeDTO {
    private Integer id;
    private Integer orderNumber;
    private LanguageEnum nameUz;
    private LanguageEnum nameEn;
    private LanguageEnum nameRu;
    private Boolean visible;
    private LocalDateTime createdDate;
}
