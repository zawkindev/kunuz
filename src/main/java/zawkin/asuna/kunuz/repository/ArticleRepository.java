package zawkin.asuna.kunuz.repository;

import jakarta.transaction.Transactional;
import org.hibernate.validator.constraints.UUID;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.dto.article.ArticleFilterDTO;
import zawkin.asuna.kunuz.entity.ArticleEntity;
import zawkin.asuna.kunuz.enums.ArticleEnum;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
    // 1. CREATE: Automatically handled by save method from CrudRepository

    // 2. UPDATE: Manually implement update method in service; use save after fetching and modifying the entity.

    // 3. DELETE: Automatically handled by deleteById from CrudRepository

    // 4. Change status by id (PUBLISHER)
    @Modifying
    @Transactional
    @Query("update ArticleEntity a set a.status = :status where a.id = :id")
    int changeStatus(@Param("id") String id, @Param("status") ArticleEnum status);

    // 5. Get last 5 published articles by types, ordered by created date
    @Query(value = "SELECT * FROM article_entity WHERE type IN :articleTypes ORDER BY created_date DESC LIMIT 5", nativeQuery = true)
    List<ArticleEntity> findTop5ByTypeInOrderByCreatedDateDesc(@Param("articleTypes") List<String> articleTypes);

    // 6. Get last 3 published articles by types, ordered by created date
    @Query(value = "SELECT * FROM article_entity WHERE type IN :articleTypes ORDER BY created_date DESC LIMIT 3", nativeQuery = true)
    List<ArticleEntity> findTop3ByTypeInOrderByCreatedDateDesc(@Param("articleTypes") List<String> articleTypes);

    // 7. Get last 8 articles excluding given IDs, ordered by created date
    @Query(value = "SELECT * FROM article_entity WHERE id NOT IN :excludedIds ORDER BY created_date DESC LIMIT 8", nativeQuery = true)
    List<ArticleEntity> findTop8ByIdNotInOrderByCreatedDateDesc(@Param("excludedIds") List<String> excludedIds);


    // 8. Get article by ID and language (use ArticleFullInfo)
    Optional<ArticleEntity> findByIdAndVisibleTrue(String id);

    // 9. Get last 4 articles by types, excluding a specific article ID
    @Query("SELECT a FROM ArticleEntity a WHERE a.type IN :articleTypes AND a.id != :excludedId ORDER BY a.createdDate DESC")
    List<ArticleEntity> findTop4ByTypeInAndIdNotOrderByCreatedDateDesc(
            @Param("articleTypes") List<String> articleTypes,
            @Param("excludedId") String excludedId
    );

    // 10. Get 4 most read articles
    @Query(value = "SELECT * FROM article_entity ORDER BY shared_count DESC LIMIT 4", nativeQuery = true)
    List<ArticleEntity> findTop4ByOrderBySharedCountDesc();

    // 11. Get last 4 articles by tag name, ordered by created date
    @Query(value = "SELECT * FROM article_entity a JOIN article_tags at ON a.id = at.article_id JOIN tags t ON at.tag_id = t.id WHERE t.name = :tagName", nativeQuery = true)
    List<ArticleEntity> findArticlesByTagNameLast4(@Param("tagName") String tagName);

    // 12. Get last 5 articles by types and region key
    @Query(value = "SELECT * FROM article_entity a WHERE a.type IN :articleTypes AND a.region_key = :regionKey ORDER BY a.created_date DESC LIMIT 5", nativeQuery = true)
    List<ArticleEntity> findTop5ByTypeInAndRegionKeyOrderByCreatedDateDesc(@Param("articleTypes") List<String> articleTypes, @Param("regionKey") String regionKey);


    // 13. Get article list by region key with pagination
    @Query("SELECT a FROM ArticleEntity a WHERE a.regionKey = :regionKey")
    Page<ArticleEntity> findByRegionKey(@Param("regionKey") String regionKey, Pageable pageable);


    // 14. Get last 5 articles by category key
    @Query("SELECT a FROM ArticleEntity a WHERE a.category_id.id = :categoryId ORDER BY a.createdDate DESC")
    List<ArticleEntity> findTop5ByCategoryKeyOrderByCreatedDateDesc(@Param("categoryId") String categoryId);

    // 15. Get article list by category key with pagination
    Page<ArticleEntity> findByCategoryKeyAndVisibleTrue(String categoryKey, Pageable pageable);

    // 16. Increase article view count by article ID
    @Modifying
    @Transactional
    @Query("UPDATE ArticleEntity a SET a.viewCount = a.viewCount + 1 WHERE a.id = :id")
    int incrementViewCount(@Param("id") String articleId);

    // 17. Increase share count by article ID
    @Modifying
    @Transactional
    @Query("UPDATE ArticleEntity a SET a.sharedCount = a.sharedCount + 1 WHERE a.id = :id")
    int incrementSharedCount(@Param("id") UUID articleId);

    @Modifying
    @Query("UPDATE ArticleEntity a SET a.sharedCount = a.sharedCount + 1 WHERE a.id = :id")
    int incrementShareCount(@Param("id") String id);

    Page<ArticleEntity> findByCategoryKey(String categoryKey, Pageable pageable);

    @Modifying
    @Query("UPDATE ArticleEntity a SET a.visible = false WHERE a.id = :id")
    int changeVisible(@Param("id") String id);
}
