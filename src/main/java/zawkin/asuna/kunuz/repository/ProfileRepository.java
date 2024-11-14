package zawkin.asuna.kunuz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import zawkin.asuna.kunuz.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository extends JpaRepository<ProfileEntity, Integer>, PagingAndSortingRepository<ProfileEntity, Integer> {
    ProfileEntity getById(Integer id);

    Optional<ProfileEntity> findByIdAndVisibleTrue(Integer id);

    Page<ProfileEntity> findAll(Pageable p);

    @Query("SELECT p FROM ProfileEntity p WHERE p.email = :username OR p.phone = :username")
    Optional<ProfileEntity> findByEmailOrPhone(@Param("username") String username);

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findByPhone(String phone);

    boolean existsByEmail(String email);

    boolean existsByPhone(String phone);
}
