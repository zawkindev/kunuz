package zawkin.asuna.kunuz.repository;

import org.springframework.data.annotation.ReadOnlyProperty;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.entity.CommentLikeEntity;
import zawkin.asuna.kunuz.enums.LikeTypeEnum;

import java.util.Optional;

@Repository
public interface CommentLikeRepository extends JpaRepository<CommentLikeEntity, Long> {

    Optional<CommentLikeEntity> findByCommentIdAndProfileId(Long commentId, Integer profileId);

    int countByCommentIdAndType(Long commentId, LikeTypeEnum type);

    int countByCommentIdAndTypeEnum(Long commentId, LikeTypeEnum likeTypeEnum);
}
