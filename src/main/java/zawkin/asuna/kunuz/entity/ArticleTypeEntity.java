package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.LanguageEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "article_type")
public class ArticleTypeEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column
    private Integer orderNumber;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageEnum nameUz;
    @Enumerated(EnumType.STRING)
    @Column
    private LanguageEnum nameEn;
    @Enumerated(EnumType.STRING)
    @Column
    private LanguageEnum nameRu;

    @Column
    private Boolean visible;
    @Column
    private LocalDateTime createdDate;
}
