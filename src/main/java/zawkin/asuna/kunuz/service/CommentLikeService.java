package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.entity.CommentEntity;
import zawkin.asuna.kunuz.entity.CommentLikeEntity;
import zawkin.asuna.kunuz.entity.ProfileEntity;
import zawkin.asuna.kunuz.enums.LikeTypeEnum;
import zawkin.asuna.kunuz.repository.CommentLikeRepository;

@Service
public class CommentLikeService {
    @Autowired
    private CommentLikeRepository commentLikeRepository;

    public void likeComment(Long commentId, Integer profileId) {
        saveOrUpdateLike(commentId, profileId, LikeTypeEnum.LIKE);
    }

    public void dislikeComment(Long commentId, Integer profileId) {
        saveOrUpdateLike(commentId, profileId, LikeTypeEnum.DISLIKE);
    }

    public void removeReaction(Long commentId, Integer profileId) {
        commentLikeRepository.findByCommentIdAndProfileId(commentId, profileId).ifPresent(commentLikeRepository::delete);
    }

    private void saveOrUpdateLike(Long commentId, Integer profileId, LikeTypeEnum type) {
        commentLikeRepository.findByCommentIdAndProfileId(commentId, profileId).ifPresentOrElse(like -> {
            like.setType(type);
            commentLikeRepository.save(like);
        }, () -> commentLikeRepository.save(new CommentLikeEntity(new CommentEntity(commentId), new ProfileEntity(profileId), type)));
    }

    public int countLikes(Long commentId) {
        return commentLikeRepository.countByCommentIdAndTypeEnum(commentId, LikeTypeEnum.LIKE);
    }

    public int countDislikes(Long commentId) {
        return commentLikeRepository.countByCommentIdAndTypeEnum(commentId, LikeTypeEnum.DISLIKE);
    }
}
