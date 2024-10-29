package zawkin.asuna.kunuz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import zawkin.asuna.kunuz.entity.ProfileEntity;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity, Integer>, PagingAndSortingRepository<ProfileEntity, Integer> {
    ProfileEntity getById(Integer id);
    Optional<ProfileEntity> findByIdAndVisibleTrue(Integer id);
    Optional<ProfileEntity> findByEmailAndVisibleTrue(String email);
    Page<ProfileEntity> findAll(Pageable p);
}
