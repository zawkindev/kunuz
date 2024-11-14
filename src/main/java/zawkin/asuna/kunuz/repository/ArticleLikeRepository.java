package zawkin.asuna.kunuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import zawkin.asuna.kunuz.entity.ArticleLikeEntity;
import zawkin.asuna.kunuz.enums.LikeTypeEnum;

import java.util.Optional;

public interface ArticleLikeRepository extends JpaRepository<ArticleLikeEntity, Long> {
    Optional<ArticleLikeEntity> findByArticleIdAndUserId(Long articleId, Long userId);

    int countByArticleIdAndType(Long articleId, LikeTypeEnum type);
}
