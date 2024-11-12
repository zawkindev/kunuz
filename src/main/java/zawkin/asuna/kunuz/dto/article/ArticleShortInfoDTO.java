package zawkin.asuna.kunuz.dto.article;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.UUID;
import zawkin.asuna.kunuz.dto.ImageDTO;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleShortInfoDTO {
    private String id;
    private String title;
    private String description;
    private ImageDTO image;
    private LocalDateTime publishedDate;
}
