package zawkin.asuna.kunuz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import zawkin.asuna.kunuz.entity.CommentEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface CommentRepository extends JpaRepository<CommentEntity, Long> {

    List<CommentEntity> findByArticleIdAndReplyToIsNull(Long articleId); // Fetch top-level commentEntitys by article

    List<CommentEntity> findByReplyToId(Long commentEntityId); // Fetch replies to a specific commentEntity

    Page<CommentEntity> findByArticleId(Long articleId, Pageable pageable); // Paginated commentEntity list by article

    Page<CommentEntity> findByCreatedDateBetweenAndProfileIdAndArticleId(
            LocalDateTime createdFrom, LocalDateTime createdTo, Long profileId, Long articleId, Pageable pageable
    );
}

