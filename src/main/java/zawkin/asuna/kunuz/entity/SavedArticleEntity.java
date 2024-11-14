package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "saved_article")
public class SavedArticleEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "article_id", nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private ProfileEntity profile;

    private LocalDateTime savedDate = LocalDateTime.now();

    // Constructors
    public SavedArticleEntity() {
    }

    public SavedArticleEntity(ArticleEntity article, ProfileEntity profile) {
        this.article = article;
        this.profile = profile;
    }
}
