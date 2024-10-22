package zawkin.asuna.kunuz.repository;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.entity.ArticleEntity;
import zawkin.asuna.kunuz.enums.ArticleEnum;

@Repository
public interface ArticleRepository extends CrudRepository<ArticleEntity, String> {
    @Modifying
    @Transactional
    @Query("update ArticleEntity set visible = false  where id=?1")
    int changeVisible(String id);

    @Modifying
    @Transactional
    @Query("update ArticleEntity a set a.status=:status  where a.id=:id")
    int changeStatus(@Param("id") String id, @Param("status") ArticleEnum status);

    ArticleEntity findByIdAndVisibleTrue(String id);
}
