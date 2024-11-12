package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.ArticleEnum;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(columnDefinition = "TEXT")
    private String content;

    @Column(nullable = false)
    private Long sharedCount = 0L;

    @Column(nullable = false)
    private Long viewCount = 0L;

    @Column(nullable = false)
    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime publishedDate;

    @Column(nullable = false)
    private Boolean visible = true;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ArticleEnum status; // Enum for article status (e.g., "PUBLISHED", "NOT_PUBLISHED")

    @ManyToOne
    @JoinColumn(name = "image_id")
    private ImageEntity image; // Image associated with the article

    @ManyToOne
    @JoinColumn(name = "region_id")
    private RegionEntity region; // Region associated with the article

    @ManyToOne
    @JoinColumn(name = "category_id")
    private CategoryEntity category; // Category associated with the article

    @ElementCollection
    @CollectionTable(name = "article_types", joinColumns = @JoinColumn(name = "article_id"))
    @Column(name = "type")
    private List<String> articleTypes; // List of article types

    @ManyToMany
    @JoinTable(
            name = "article_tags",
            joinColumns = @JoinColumn(name = "article_id"),
            inverseJoinColumns = @JoinColumn(name = "tag_id")
    )
    private List<TagEntity> tags; // Tags associated with the article
}
