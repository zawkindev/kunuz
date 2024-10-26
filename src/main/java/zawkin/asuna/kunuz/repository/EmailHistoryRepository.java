package zawkin.asuna.kunuz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import zawkin.asuna.kunuz.entity.EmailHistoryEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface EmailHistoryRepository extends CrudRepository<EmailHistoryEntity, String>, PagingAndSortingRepository<EmailHistoryEntity, String> {
    List<EmailHistoryEntity> findAllByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

    List<EmailHistoryEntity> findAllByEmail(String email);

    Page<EmailHistoryEntity> findAll(Pageable p);
}
