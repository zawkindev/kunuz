package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.entity.ArticleEntity;
import zawkin.asuna.kunuz.entity.ProfileEntity;
import zawkin.asuna.kunuz.entity.SavedArticleEntity;
import zawkin.asuna.kunuz.repository.SavedArticleRepository;

import java.util.List;

@Service
public class SavedArticleService {
    @Autowired
    private SavedArticleRepository savedArticleRepository;

    public void saveArticle(String articleId, Integer profileId) {
        if (!savedArticleRepository.existsByArticleIdAndProfileId(articleId, profileId)) {
            ArticleEntity article = new ArticleEntity(articleId);
            ProfileEntity profile = new ProfileEntity(profileId);
            savedArticleRepository.save(new SavedArticleEntity(article, profile));
        }
    }

    public void deleteSavedArticle(String articleId, Integer profileId) {
        savedArticleRepository.deleteByArticleIdAndProfileId(articleId, profileId);
    }

    public List<SavedArticleEntity> getProfileSavedArticles(Integer profileId) {
        return savedArticleRepository.findByProfileId(profileId);
    }
}
