package zawkin.asuna.kunuz.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.dto.article.ArticleFilterDTO;
import zawkin.asuna.kunuz.entity.ArticleEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FilterArticleRepository {
    @Autowired
    private EntityManager entityManager;

    public Page<ArticleEntity> filter(ArticleFilterDTO filter, Pageable pageable) {
        StringBuilder selectQueryBuilder = new StringBuilder("SELECT a FROM ArticleEntity a ");
        StringBuilder countQueryBuilder = new StringBuilder("SELECT count(a) FROM ArticleEntity a ");

        StringBuilder builder = new StringBuilder(" WHERE a.visible = true ");
        Map<String, Object> params = new HashMap<>();

        // Filter by ID
        if (filter.getId() != null) {
            builder.append(" AND a.id = :id ");
            params.put("id", filter.getId());
        }

        // Filter by Title
        if (filter.getTitle() != null) {
            builder.append(" AND LOWER(a.title) LIKE :title ");
            params.put("title", "%" + filter.getTitle().toLowerCase() + "%");
        }

        // Filter by Region ID
        if (filter.getRegionId() != null) {
            builder.append(" AND a.region.id = :regionId ");
            params.put("regionId", filter.getRegionId());
        }

        // Filter by Category ID
        if (filter.getCategoryId() != null) {
            builder.append(" AND a.category.id = :categoryId ");
            params.put("categoryId", filter.getCategoryId());
        }

        // Filter by Moderator ID
        if (filter.getModeratorId() != null) {
            builder.append(" AND a.moderator.id = :moderatorId ");
            params.put("moderatorId", filter.getModeratorId());
        }

        // Filter by Publisher ID
        if (filter.getPublisherId() != null) {
            builder.append(" AND a.publisher.id = :publisherId ");
            params.put("publisherId", filter.getPublisherId());
        }

        // Filter by Status
        if (filter.getStatus() != null) {
            builder.append(" AND a.status = :status ");
            params.put("status", filter.getStatus());
        }

        // Filter by Created Date Range
        if (filter.getCreatedDateFrom() != null && filter.getCreatedDateTo() != null) {
            builder.append(" AND a.createdDate BETWEEN :createdDateFrom AND :createdDateTo ");
            params.put("createdDateFrom", filter.getCreatedDateFrom());
            params.put("createdDateTo", filter.getCreatedDateTo());
        } else if (filter.getCreatedDateFrom() != null) {
            builder.append(" AND a.createdDate >= :createdDateFrom ");
            params.put("createdDateFrom", filter.getCreatedDateFrom());
        } else if (filter.getCreatedDateTo() != null) {
            builder.append(" AND a.createdDate <= :createdDateTo ");
            params.put("createdDateTo", filter.getCreatedDateTo());
        }

        // Filter by Published Date Range
        if (filter.getPublishedDateFrom() != null && filter.getPublishedDateTo() != null) {
            builder.append(" AND a.publishedDate BETWEEN :publishedDateFrom AND :publishedDateTo ");
            params.put("publishedDateFrom", filter.getPublishedDateFrom());
            params.put("publishedDateTo", filter.getPublishedDateTo());
        } else if (filter.getPublishedDateFrom() != null) {
            builder.append(" AND a.publishedDate >= :publishedDateFrom ");
            params.put("publishedDateFrom", filter.getPublishedDateFrom());
        } else if (filter.getPublishedDateTo() != null) {
            builder.append(" AND a.publishedDate <= :publishedDateTo ");
            params.put("publishedDateTo", filter.getPublishedDateTo());
        }

        // Append dynamic filters to queries
        selectQueryBuilder.append(builder);
        countQueryBuilder.append(builder);

        // Create select query
        Query selectQuery = entityManager.createQuery(selectQueryBuilder.toString(), ArticleEntity.class);
        selectQuery.setFirstResult((int) pageable.getOffset());
        selectQuery.setMaxResults(pageable.getPageSize());
        params.forEach(selectQuery::setParameter);

        List<ArticleEntity> articleList = selectQuery.getResultList();

        // Create count query for total elements
        Query countQuery = entityManager.createQuery(countQueryBuilder.toString());
        params.forEach(countQuery::setParameter);
        Long totalElements = (Long) countQuery.getSingleResult();

        return new PageImpl<>(articleList, pageable, totalElements);
    }

}
