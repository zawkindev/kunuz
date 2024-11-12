package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.article.ArticleDTO;
import zawkin.asuna.kunuz.dto.article.ArticleFilterDTO;
import zawkin.asuna.kunuz.dto.article.ArticleFullInfoDTO;
import zawkin.asuna.kunuz.dto.article.ArticleShortInfoDTO;
import zawkin.asuna.kunuz.dto.profile.ProfileDTO;
import zawkin.asuna.kunuz.entity.ArticleEntity;
import zawkin.asuna.kunuz.enums.ArticleEnum;
import zawkin.asuna.kunuz.exp.CustomException;
import zawkin.asuna.kunuz.repository.ArticleRepository;
import zawkin.asuna.kunuz.repository.CategoryRepository;
import zawkin.asuna.kunuz.repository.FilterArticleRepository;
import zawkin.asuna.kunuz.repository.RegionRepository;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;
    @Autowired
    private CategoryRepository categoryRepository; // Assuming you have a CategoryRepository
    @Autowired
    private RegionRepository regionRepository; // Assuming you have a RegionRepository

    // 1. Create Article
    public ArticleDTO create(ArticleDTO dto) {
        dto.setVisible(true);
        ArticleEntity entity = mapToEntity(dto);
        entity = articleRepository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());
        return mapToDTO(entity);
    }

    // 2. Update Article
    public ArticleDTO update(String id, ArticleDTO dto) {
        ArticleEntity entity = articleRepository.findByIdAndVisibleTrue(id)
                .orElseThrow(() -> new CustomException("Article not found"));

        entity = mapToEntity(dto);
        articleRepository.save(entity);
        return mapToDTO(entity);
    }

    // 3. Delete Article
    public Boolean delete(String id) {
        int result = articleRepository.changeVisible(id);
        return result > 0;
    }

    // 4. Change Article Status
    public Boolean changeStatus(String id, ArticleEnum status) {
        int result = articleRepository.changeStatus(id, status);
        return result > 0;
    }

    // 5. Get Last 5 Articles by Type
    public List<ArticleShortInfoDTO> getLast5ByType(List<String> articleTypes) {
        List<ArticleEntity> entities = articleRepository.findTop5ByTypeInOrderByCreatedDateDesc(articleTypes);
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 6. Get Last 3 Articles by Type
    public List<ArticleShortInfoDTO> getLast3ByType(List<String> articleTypes) {
        List<ArticleEntity> entities = articleRepository.findTop3ByTypeInOrderByCreatedDateDesc(articleTypes);
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 7. Get Last 8 Articles Excluding Given IDs
    public List<ArticleShortInfoDTO> getLast8ExcludingIds(List<String> excludedIds) {
        List<ArticleEntity> entities = articleRepository.findTop8ByIdNotInOrderByCreatedDateDesc(excludedIds);
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 8. Get Article by ID and Language
    public ArticleFullInfoDTO getArticleByIdAndLang(String id, String lang) {
        ArticleEntity entity = articleRepository.findByIdAndVisibleTrue(id)
                .orElseThrow(() -> new CustomException("Article not found"));
        return mapToFullInfoDTO(entity, lang);
    }

    // 9. Get Last 4 Articles by Type, Excluding Specific Article ID
    public List<ArticleShortInfoDTO> getLast4ByTypeAndExcludeId(List<String> articleTypes, String excludedId) {
        List<ArticleEntity> entities = articleRepository.findTop4ByTypeInAndIdNotOrderByCreatedDateDesc(articleTypes, excludedId);
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 10. Get Top 4 Most Read Articles
    public List<ArticleShortInfoDTO> getTop4ByViews() {
        List<ArticleEntity> entities = articleRepository.findTop4ByOrderBySharedCountDesc();
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 11. Get Last 4 Articles by Tag Name
    public List<ArticleShortInfoDTO> getLast4ByTagName(String tagName) {
        List<ArticleEntity> entities = articleRepository.findArticlesByTagNameLast4(tagName);
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 12. Get Last 5 Articles by Type and Region
    public List<ArticleShortInfoDTO> getLast5ByTypeAndRegion(List<String> articleTypes, String regionKey) {
        List<ArticleEntity> entities = articleRepository.findTop5ByTypeInAndRegionKeyOrderByCreatedDateDesc(articleTypes, regionKey);
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 13. Get Paginated Articles by Region
    public Page<ArticleShortInfoDTO> getByRegion(String regionKey, Pageable pageable) {
        Page<ArticleEntity> page = articleRepository.findByRegionKey(regionKey, pageable);
        return page.map(this::mapToShortInfoDTO);
    }

    // 14. Get Last 5 Articles by Category Key
    public List<ArticleShortInfoDTO> getLast5ByCategory(String categoryKey) {
        List<ArticleEntity> entities = articleRepository.findTop5ByCategoryKeyOrderByCreatedDateDesc(categoryKey);
        return entities.stream().map(this::mapToShortInfoDTO).collect(Collectors.toList());
    }

    // 15. Get Paginated Articles by Category Key
    public Page<ArticleShortInfoDTO> getByCategory(String categoryKey, Pageable pageable) {
        Page<ArticleEntity> page = articleRepository.findByCategoryKey(categoryKey, pageable);
        return page.map(this::mapToShortInfoDTO);
    }

    // 16. Increment View Count
    public Boolean incrementViewCount(String id) {
        int result = articleRepository.incrementViewCount(id);
        return result > 0;
    }

    // 17. Increment Share Count
    public Boolean incrementShareCount(String id) {
        int result = articleRepository.incrementShareCount(id);
        return result > 0;
    }

    // Map Article Entity to ShortInfo DTO
    private ArticleShortInfoDTO mapToShortInfoDTO(ArticleEntity entity) {
        ArticleShortInfoDTO dto = new ArticleShortInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setPublishedDate(entity.getPublishedDate());
        return dto;
    }

    // Map Article Entity to FullInfo DTO
    private ArticleFullInfoDTO mapToFullInfoDTO(ArticleEntity entity, String lang) {
        ArticleFullInfoDTO dto = new ArticleFullInfoDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setContent(entity.getContent());
        dto.setDescription(entity.getDescription());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setSharedCount(entity.getSharedCount());
        // Add logic to handle language-specific content if necessary
        return dto;
    }

    // Map ArticleDTO to ArticleEntity
    private ArticleEntity mapToEntity(ArticleDTO dto) {
        ArticleEntity entity = new ArticleEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setDescription(dto.getDescription());
        entity.setContent(dto.getContent());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setPublishedDate(dto.getPublishedDate());
        entity.setSharedCount(dto.getSharedCount());
        entity.setVisible(dto.getVisible());
        return entity;
    }

    // Map ArticleEntity to ArticleDTO
    private ArticleDTO mapToDTO(ArticleEntity entity) {
        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setSharedCount(entity.getSharedCount());
        dto.setVisible(entity.getVisible());
        return dto;
    }
}
