package zawkin.asuna.kunuz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.entity.ArticleTypeEntity;

@Repository
public interface ArticleTypeRepository extends CrudRepository<ArticleTypeEntity, Integer>, PagingAndSortingRepository<ArticleTypeEntity, Integer> {
    ArticleTypeEntity getById(Integer id);

    Page<ArticleTypeEntity> findAll(Pageable p);
}
