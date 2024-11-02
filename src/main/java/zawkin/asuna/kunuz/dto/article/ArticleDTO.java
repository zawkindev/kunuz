package zawkin.asuna.kunuz.dto.article;

import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.ArticleEnum;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private LocalDateTime createdDate;
    private LocalDateTime publishedDate;
    private Boolean visible;
    private Long sharedCount;
    private ArticleEnum status;
}
