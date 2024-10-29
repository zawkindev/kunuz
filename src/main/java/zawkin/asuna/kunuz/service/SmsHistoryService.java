package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import zawkin.asuna.kunuz.dto.SmsHistoryDTO;
import zawkin.asuna.kunuz.entity.SmsHistoryEntity;
import zawkin.asuna.kunuz.repository.SmsHistoryRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.LinkedList;
import java.util.List;

public class SmsHistoryService {
    @Autowired
    private SmsHistoryRepository repository;

    public SmsHistoryDTO create(SmsHistoryDTO dto) {
        SmsHistoryEntity entity = mapToEntity(dto);
        repository.save(entity);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public List<SmsHistoryDTO> getByDate(LocalDate from, LocalDate to) {
        return mapToDTOList(repository.findAllByCreatedDateBetween(from.atStartOfDay(), to.atTime(LocalTime.MAX)));
    }

    public List<SmsHistoryDTO> getBySms(String email) {
        return mapToDTOList(repository.findAllBySms(email));
    }

    public PageImpl<SmsHistoryDTO> getAllWithPagination(int page, int size) {
        PageRequest pageRequest = PageRequest.of(page, size);
        Page<SmsHistoryEntity> result = repository.findAll(pageRequest);
        List<SmsHistoryDTO> dtoList = mapToDTOList(result.getContent());

        return new PageImpl<>(dtoList, pageRequest, result.getTotalElements());
    }

    public SmsHistoryEntity mapToEntity(SmsHistoryDTO dto) {
        if (dto == null) {
            return null;
        }
        SmsHistoryEntity entity = new SmsHistoryEntity();
        entity.setId(dto.getId());
        entity.setMessage(dto.getMessage());
        entity.setCreatedDate(dto.getCreatedDate());
        return entity;
    }

    public SmsHistoryDTO mapToDto(SmsHistoryEntity entity) {
        if (entity == null) {
            return null;
        }
        SmsHistoryDTO dto = new SmsHistoryDTO();
        dto.setId(entity.getId());
        dto.setMessage(entity.getMessage());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }

    public List<SmsHistoryDTO> mapToDTOList(List<SmsHistoryEntity> entityList) {
        List<SmsHistoryDTO> dtoList = new LinkedList<>();
        for (SmsHistoryEntity entity : entityList) {
            dtoList.add(mapToDto(entity));
        }

        return dtoList;
    }
}
