package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.LikeTypeEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment_like")
public class CommentLikeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "comment_id", nullable = false)
    private CommentEntity comment;

    @ManyToOne
    @JoinColumn(name = "profile_id", nullable = false)
    private ProfileEntity profile;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LikeTypeEnum type; // Enum to represent Like or Dislike

    private LocalDateTime createdDate = LocalDateTime.now();

    public CommentLikeEntity(CommentEntity commentEntity, ProfileEntity profileEntity, LikeTypeEnum type) {
        this.comment = commentEntity;
        this.profile = profileEntity;
        this.type = type;
    }
}
