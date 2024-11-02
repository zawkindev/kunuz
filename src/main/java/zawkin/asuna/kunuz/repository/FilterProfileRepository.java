package zawkin.asuna.kunuz.repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.dto.profile.ProfileFilterDTO;
import zawkin.asuna.kunuz.entity.ProfileEntity;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class FilterProfileRepository {
    @Autowired
    private EntityManager entityManager;

    public Page<ProfileEntity> filter(ProfileFilterDTO filter, int page, int size) {

        StringBuilder selectQueryBuilder = new StringBuilder("SELECT p FROM ProfileEntity p ");
        StringBuilder countQueryBuilder = new StringBuilder("SELECT count(p) FROM ProfileEntity p ");

        StringBuilder builder = new StringBuilder(" where p.visible = true ");

        Map<String, Object> params = new HashMap<>();

        if (filter.getName() != null) { // condition by name
            builder.append(" and p.name=:name ");
            params.put("name", filter.getName());
        }
        if (filter.getSurname() != null) { // condition by surname with like
            builder.append(" and LOWER(p.surname) like :surname ");
            params.put("surname", "%" + filter.getSurname().toLowerCase() + "%");
        }
        if (filter.getPhone() != null) {
            builder.append(" and p.phone =:phone ");
            params.put("phone", filter.getPhone());
        }
        if (filter.getRole() != null) {
            builder.append(" and p.role =:role ");
            params.put("role", filter.getRole());
        }
        if (filter.getCreatedDateFrom() != null && filter.getCreatedDateTo() != null) {
            builder.append(" and p.createdDate between :createdDateFrom and :createdDateTo ");
            params.put("createdDateFrom", filter.getCreatedDateFrom());
            params.put("createdDateTo", filter.getCreatedDateTo());
        } else if (filter.getCreatedDateFrom() != null) {
            builder.append(" and p.createdDate > :createdDateFrom ");
            params.put("createdDateFrom", filter.getCreatedDateFrom());
        } else if (filter.getCreatedDateTo() != null) {
            builder.append(" and p.createdDate < :createdDateTo ");
            params.put("createdDateTo", filter.getCreatedDateTo());
        }

        selectQueryBuilder.append(builder);
        countQueryBuilder.append(builder);

        // select query
        Query selectQuery = entityManager.createQuery(selectQueryBuilder.toString());
        selectQuery.setFirstResult((page) * size); // 50
        selectQuery.setMaxResults(size); // 30
        params.forEach(selectQuery::setParameter);

        List<ProfileEntity> profileEntityList = selectQuery.getResultList();

        // totalCount query
        Query countQuery = entityManager.createQuery(countQueryBuilder.toString());
        params.forEach(countQuery::setParameter);

        Long totalElements = (Long) countQuery.getSingleResult();
        return new PageImpl<>(profileEntityList, PageRequest.of(page, size), totalElements);
    }
}
