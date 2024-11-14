package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.entity.SavedArticleEntity;
import zawkin.asuna.kunuz.service.SavedArticleService;

import java.util.List;

@RestController
@RequestMapping("/saved_article")
public class SavedArticleController {
    @Autowired
    private SavedArticleService savedArticleService;

    @PostMapping("/create")
    public void saveArticle(@RequestParam String articleId, @RequestParam Integer profileId) {
        savedArticleService.saveArticle(articleId, profileId);
    }

    @DeleteMapping("/delete")
    public void deleteSavedArticle(@RequestParam String articleId, @RequestParam Integer profileId) {
        savedArticleService.deleteSavedArticle(articleId, profileId);
    }

    @GetMapping("/list")
    public List<SavedArticleEntity> getProfileSavedArticles(@RequestParam Integer profileId) {
        return savedArticleService.getProfileSavedArticles(profileId);
    }
}
