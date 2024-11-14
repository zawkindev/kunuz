package zawkin.asuna.kunuz.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import zawkin.asuna.kunuz.entity.AttachEntity;

@Repository
public interface AttachRepository extends JpaRepository<AttachEntity, String> {
}
