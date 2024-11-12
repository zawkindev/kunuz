package zawkin.asuna.kunuz.dto.article;

import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.dto.EmailHistoryDTO;
import zawkin.asuna.kunuz.dto.category.CategoryDTO;
import zawkin.asuna.kunuz.dto.region.RegionDTO;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
public class ArticleFullInfoDTO {
    private String id;
    private String title;
    private String description;
    private String content;
    private Long sharedCount;
    private RegionDTO region;
    private CategoryDTO category;
    private LocalDateTime publishedDate;
    private int viewCount;
    private int likeCount;
    private List<EmailHistoryDTO.TagDTO> tagList;
    private String imageUrl; // or `imageId` for display
    private LocalDateTime createdDate;
}
