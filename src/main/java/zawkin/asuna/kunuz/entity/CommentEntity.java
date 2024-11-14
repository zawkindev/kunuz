package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "comment")
public class CommentEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ArticleEntity article;

    @ManyToOne
    @JoinColumn(nullable = false)
    private ProfileEntity profile;

    @ManyToOne
    private CommentEntity replyTo; // Reference to the parent comment for replies

    @Column(nullable = false)
    private String content;

    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updateDate;
    private boolean visible = true;

    public CommentEntity(Long replyToId) {
        this.id = replyToId;
    }

    public CommentEntity() {

    }
}
