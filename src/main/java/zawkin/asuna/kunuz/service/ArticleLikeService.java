package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.entity.ArticleLikeEntity;
import zawkin.asuna.kunuz.enums.LikeTypeEnum;
import zawkin.asuna.kunuz.repository.ArticleLikeRepository;

@Service
public class ArticleLikeService {
    @Autowired
    private ArticleLikeRepository articleLikeRepository;

    public void likeArticle(Long articleId, Long userId) {
        saveOrUpdateLike(articleId, userId, LikeTypeEnum.LIKE);
    }

    public void dislikeArticle(Long articleId, Long userId) {
        saveOrUpdateLike(articleId, userId, LikeTypeEnum.DISLIKE);
    }

    public void removeReaction(Long articleId, Long userId) {
        articleLikeRepository.findByArticleIdAndUserId(articleId, userId)
                .ifPresent(articleLikeRepository::delete);
    }

    private void saveOrUpdateLike(Long articleId, Long userId, LikeTypeEnum type) {
        articleLikeRepository.findByArticleIdAndUserId(articleId, userId)
                .ifPresentOrElse(
                        like -> { like.setType(type); articleLikeRepository.save(like); },
                        () -> articleLikeRepository.save(new ArticleLikeEntity(articleId, userId, type))
                );
    }

    public int countLikes(Long articleId) {
        return articleLikeRepository.countByArticleIdAndType(articleId, LikeTypeEnum.LIKE);
    }

    public int countDislikes(Long articleId) {
        return articleLikeRepository.countByArticleIdAndType(articleId, LikeTypeEnum.DISLIKE);
    }
}
