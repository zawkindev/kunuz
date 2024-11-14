package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.service.ArticleLikeService;

@RestController
@RequestMapping("/article_like")
public class ArticleLikeController {
    @Autowired
    private ArticleLikeService articleLikeService;

    @PostMapping("/{articleId}/like")
    public void likeArticle(@PathVariable Long articleId, @RequestParam Long userId) {
        articleLikeService.likeArticle(articleId, userId);
    }

    @PostMapping("/{articleId}/dislike")
    public void dislikeArticle(@PathVariable Long articleId, @RequestParam Long userId) {
        articleLikeService.dislikeArticle(articleId, userId);
    }

    @DeleteMapping("/{articleId}/remove")
    public void removeReaction(@PathVariable Long articleId, @RequestParam Long userId) {
        articleLikeService.removeReaction(articleId, userId);
    }
}
