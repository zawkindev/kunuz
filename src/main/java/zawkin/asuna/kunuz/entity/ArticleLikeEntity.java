package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.LikeTypeEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article_like")
public class ArticleLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long articleId;

    @Column(nullable = false)
    private Long userId;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LikeTypeEnum type; // Enum to represent Like or Dislike

    private LocalDateTime createdDate = LocalDateTime.now();

    public ArticleLikeEntity(Long articleId, Long userId, LikeTypeEnum type) {
        this.articleId = articleId;
        this.userId = userId;
        this.type = type;
    }


    // Getters and Setters
}

