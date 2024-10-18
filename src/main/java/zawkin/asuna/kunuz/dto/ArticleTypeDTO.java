package zawkin.asuna.kunuz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleTypeDTO {
    private Integer id;
    private Integer orderNumber;
    private String nameUz;
    private String nameEn;
    private String nameRu;
    private Boolean visible;
    private LocalDateTime createdDate;
}
