package zawkin.asuna.kunuz.dto.article;

import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.ArticleEnum;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleFilterDTO {
    private String id;                // Filter by article ID
    private String title;             // Filter by article title
    private String regionId;          // Filter by region ID
    private String categoryId;        // Filter by category ID
    private String moderatorId;       // Filter by moderator ID
    private String publisherId;       // Filter by publisher ID
    private ArticleEnum status;       // Filter by article status (published, not published)
    private LocalDateTime createdDateFrom; // Filter by article creation date range (from)
    private LocalDateTime createdDateTo;   // Filter by article creation date range (to)
    private LocalDateTime publishedDateFrom; // Filter by published date range (from)
    private LocalDateTime publishedDateTo;   // Filter by published date range (to)
}
