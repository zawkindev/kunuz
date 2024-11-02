package zawkin.asuna.kunuz.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;
import zawkin.asuna.kunuz.entity.SmsHistoryEntity;

import java.time.LocalDateTime;
import java.util.List;

public interface SmsHistoryRepository extends CrudRepository<SmsHistoryEntity, String>, PagingAndSortingRepository<SmsHistoryEntity, String> {
    List<SmsHistoryEntity> findAllByCreatedDateBetween(LocalDateTime from, LocalDateTime to);

    List<SmsHistoryEntity> findAllBySms(String email);

    Page<SmsHistoryEntity> findAll(Pageable p);

    SmsHistoryEntity findByPhone(String phone);
}

