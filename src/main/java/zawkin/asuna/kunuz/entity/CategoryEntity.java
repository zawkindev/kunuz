package zawkin.asuna.kunuz.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.LanguageEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "category")
public class CategoryEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Integer id;
    @Column
    private Integer orderNumber;
    @Column
    private Boolean visible;
    @Column
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column
    private LanguageEnum nameUz;
    @Enumerated(EnumType.STRING)
    @Column
    private LanguageEnum nameEn;
    @Enumerated(EnumType.STRING)
    @Column
    private LanguageEnum nameRu;
}
