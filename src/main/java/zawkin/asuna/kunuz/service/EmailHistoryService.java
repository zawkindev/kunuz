package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.EmailHistoryDTO;
import zawkin.asuna.kunuz.entity.EmailHistoryEntity;
import zawkin.asuna.kunuz.repository.EmailHistoryRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class EmailHistoryService {
    @Autowired
    private EmailHistoryRepository repository;

    public EmailHistoryDTO create(EmailHistoryDTO dto) {
        EmailHistoryEntity entity = mapToEntity(dto);
        repository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public List<EmailHistoryDTO> getByDate(LocalDate from, LocalDate to) {
        return mapToDTOList(repository.findAllByCreatedDateBetween(from.atStartOfDay(), to.atTime(LocalTime.MAX)));
    }

    public List<EmailHistoryDTO> getByEmail(String email) {
        return mapToDTOList(repository.findAllByEmail(email));
    }

    public PageImpl<EmailHistoryDTO> getAllWithPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<EmailHistoryEntity> result = repository.findAll(pageRequest);
        List<EmailHistoryDTO> dtoList = mapToDTOList(result.getContent());

        return new PageImpl<>(dtoList, pageRequest, result.getTotalElements());
    }

    public EmailHistoryEntity mapToEntity(EmailHistoryDTO dto) {
        if (dto == null) {
            return null;
        }
        EmailHistoryEntity entity = new EmailHistoryEntity();
        entity.setId(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setEmail(dto.getEmail());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

    public EmailHistoryDTO mapToDto(EmailHistoryEntity entity) {
        if (entity == null) {
            return null;
        }
        EmailHistoryDTO dto = new EmailHistoryDTO();
        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setEmail(entity.getEmail());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<EmailHistoryDTO> mapToDTOList(List<EmailHistoryEntity> entityList) {
        List<EmailHistoryDTO> dtoList = new LinkedList<>();
        for (EmailHistoryEntity entity : entityList) {
            dtoList.add(mapToDto(entity));
        }

        return dtoList;
    }
}
