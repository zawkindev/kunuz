package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.region.RegionDTO;
import zawkin.asuna.kunuz.dto.region.RegionUserResponseDTO;
import zawkin.asuna.kunuz.entity.RegionEntity;
import zawkin.asuna.kunuz.exp.CustomException;
import zawkin.asuna.kunuz.repository.RegionRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class RegionService {
    @Autowired
    private RegionRepository regionRepository;

    public RegionDTO create(RegionDTO dto) {
        dto.setCreatedDate(LocalDateTime.now());
        dto.setVisible(true);
        RegionEntity entity = mapToEntity(dto);
        regionRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public RegionDTO update(RegionDTO dto) {
        RegionEntity entity = regionRepository.getById(dto.getId());
        if (entity == null) {
            throw new CustomException("Article not found");
        }
        regionRepository.save(mapToEntity(dto));
        return dto;
    }

    public Boolean deleteById(Integer id) {
        regionRepository.deleteById(id);
        if (regionRepository.getById(id) == null) {
            return true;
        }
        throw new CustomException("Deletion failed: Incorrect ID");
    }

    public Page<RegionDTO> getAll(Pageable pageable) {
        Page<RegionEntity> page = regionRepository.findAll(pageable);
        Long totalCount = page.getTotalElements();
        List<RegionEntity> entityList = page.getContent();

        List<RegionDTO> dtoList = new LinkedList<>();
        for (RegionEntity entity : entityList) {
            dtoList.add(mapToDTO(entity));
        }

        return new PageImpl<RegionDTO>(dtoList, pageable, totalCount);
    }

    public RegionUserResponseDTO getByLang(Integer id, String name) {
        RegionEntity entity = regionRepository.getById(id);
        if (entity == null) {
            throw new CustomException("Article not found!");
        }
        RegionUserResponseDTO dto = new RegionUserResponseDTO(entity.getId(), entity.getOrderNumber(), entity.getCreatedDate());
        switch (name) {
            case "uz":
                dto.setName(entity.getNameUz());
                break;

            case "en":
                dto.setName(entity.getNameEn());
                break;

            case "ru":
                dto.setName(entity.getNameRu());
                break;
        }
        return dto;
    }

    public RegionEntity mapToEntity(RegionDTO dto) {
        RegionEntity entity = new RegionEntity();
        entity.setId(dto.getId());
        entity.setVisible(dto.getVisible());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setCreatedDate(dto.getCreatedDate());

        return entity;
    }

    public RegionDTO mapToDTO(RegionEntity entity) {
        RegionDTO dto = new RegionDTO();
        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
}
