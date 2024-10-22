package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.ArticleEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article")
public class ArticleEntity {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;
    @Column
    private String title;
    @Column
    private String description;
    @Column
    private String content;
    @Column
    private Long sharedCount;
    @Column
    private LocalDateTime createdDate;
    @Column
    private LocalDateTime publishedDate;
    @Column
    private Boolean visible;
    @Enumerated(EnumType.STRING)
    @Column
    private ArticleEnum status;
}
