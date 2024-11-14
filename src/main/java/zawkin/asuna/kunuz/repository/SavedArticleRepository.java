package zawkin.asuna.kunuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.entity.SavedArticleEntity;

import java.util.List;

@Repository
public interface SavedArticleRepository extends JpaRepository<SavedArticleEntity, Long> {
    void deleteByArticleIdAndProfileId(String articleId, Integer profileId);

    List<SavedArticleEntity> findByProfileId(Integer profileId);

    boolean existsByArticleIdAndProfileId(String  articleId, Integer profileId);
}
