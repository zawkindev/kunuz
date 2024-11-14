package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.entity.ArticleEntity;
import zawkin.asuna.kunuz.entity.CommentEntity;
import zawkin.asuna.kunuz.entity.ProfileEntity;
import zawkin.asuna.kunuz.repository.CommentRepository;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public CommentEntity createCommentEntity(String articleId, Integer profileId, String content, Long replyToId) {
        CommentEntity comment = new CommentEntity();
        comment.setArticle(new ArticleEntity(articleId));
        comment.setProfile(new ProfileEntity(profileId));
        comment.setContent(content);
        if (replyToId != null) {
            comment.setReplyTo(new CommentEntity(replyToId));
        }
        return commentRepository.save(comment);
    }

    public CommentEntity updateCommentEntity(Long commentId, String content, Long profileId) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow();
        throw new IllegalArgumentException("Only the comment owner can update.");
    }

    public void deleteCommentEntity(Long commentId, Long profileId, boolean isAdmin) {
        CommentEntity comment = commentRepository.findById(commentId).orElseThrow();
        if (isAdmin) {
            commentRepository.delete(comment);
        } else {
            throw new IllegalArgumentException("Only the admin or comment owner can delete.");
        }
    }

    public List<CommentEntity> getArticleCommentEntitys(Long articleId) {
        return commentRepository.findByArticleIdAndReplyToIsNull(articleId);
    }

    public Page<CommentEntity> getAllCommentEntitys(Pageable pageable) {
        return commentRepository.findAll(pageable);
    }

    public Page<CommentEntity> filterCommentEntitys(LocalDateTime createdFrom, LocalDateTime createdTo,
                                                    Long profileId, Long articleId, Pageable pageable) {
        return commentRepository.findByCreatedDateBetweenAndProfileIdAndArticleId(
                createdFrom, createdTo, profileId, articleId, pageable);
    }

    public List<CommentEntity> getReplies(Long commentId) {
        return commentRepository.findByReplyToId(commentId);
    }
}
