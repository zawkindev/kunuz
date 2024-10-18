package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

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
    @Column
    private String nameUz;
    @Column
    private String nameEn;
    @Column
    private String nameRu;
    @Column
    private Boolean visible;
    @Column
    private LocalDateTime createdDate;
}
