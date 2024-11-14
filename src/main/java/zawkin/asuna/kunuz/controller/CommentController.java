package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.entity.CommentEntity;
import zawkin.asuna.kunuz.service.CommentService;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/create")
    public CommentEntity createCommentEntity(@RequestParam String articleId, @RequestParam Integer profileId,
                                 @RequestParam String content, @RequestParam(required = false) Long replyToId) {
        return commentService.createCommentEntity(articleId, profileId, content, replyToId);
    }

    @PutMapping("/{commentId}/update")
    public CommentEntity updateCommentEntity(@PathVariable Long commentId, @RequestParam Long profileId,
                                 @RequestParam String content) {
        return commentService.updateCommentEntity(commentId, content, profileId);
    }

    @DeleteMapping("/{commentId}/delete")
    public void deleteCommentEntity(@PathVariable Long commentId, @RequestParam Long profileId,
                              @RequestParam boolean isAdmin) {
        commentService.deleteCommentEntity(commentId, profileId, isAdmin);
    }

    @GetMapping("/article/{articleId}")
    public List<CommentEntity> getArticleCommentEntitys(@PathVariable Long articleId) {
        return commentService.getArticleCommentEntitys(articleId);
    }

    @GetMapping("/list")
    public Page<CommentEntity> getAllCommentEntitys(Pageable pageable) {
        return commentService.getAllCommentEntitys(pageable);
    }

    @GetMapping("/filter")
    public Page<CommentEntity> filterCommentEntitys(@RequestParam LocalDateTime createdFrom,
                                        @RequestParam LocalDateTime createdTo,
                                        @RequestParam Long profileId, @RequestParam Long articleId,
                                        Pageable pageable) {
        return commentService.filterCommentEntitys(createdFrom, createdTo, profileId, articleId, pageable);
    }

    @GetMapping("/{commentId}/replies")
    public List<CommentEntity> getReplies(@PathVariable Long commentId) {
        return commentService.getReplies(commentId);
    }
}
