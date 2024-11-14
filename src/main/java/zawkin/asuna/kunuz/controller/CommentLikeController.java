package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.service.CommentLikeService;

@RestController
@RequestMapping("comment_like")
public class CommentLikeController {
    @Autowired
    private CommentLikeService commentLikeService;

    @PostMapping("/{commentId}/like")
    public void likeComment(@PathVariable Long commentId, @RequestParam Integer profileId) {
        commentLikeService.likeComment(commentId, profileId);
    }

    @PostMapping("/{commentId}/dislike")
    public void dislikeComment(@PathVariable Long commentId, @RequestParam Integer profileId) {
        commentLikeService.dislikeComment(commentId, profileId);
    }

    @DeleteMapping("/{commentId}/remove")
    public void removeReaction(@PathVariable Long commentId, @RequestParam Integer profileId) {
        commentLikeService.removeReaction(commentId, profileId);
    }
}
