package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.article.ArticleTypeDTO;
import zawkin.asuna.kunuz.dto.article.ArticleTypeUserResponseDTO;
import zawkin.asuna.kunuz.exp.CustomException;
import zawkin.asuna.kunuz.repository.ArticleTypeRepository;
import zawkin.asuna.kunuz.entity.ArticleTypeEntity;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleTypeService {
    @Autowired
    private ArticleTypeRepository articleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        dto.setCreatedDate(LocalDateTime.now());
        dto.setVisible(true);
        ArticleTypeEntity entity = mapToEntity(dto);
        articleTypeRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public ArticleTypeDTO update(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = articleTypeRepository.getById(dto.getId());
        if (entity == null) {
            throw new CustomException("Article not found");
        }
        articleTypeRepository.save(mapToEntity(dto));
        return dto;
    }

    public Boolean deleteById(Integer id) {
        articleTypeRepository.deleteById(id);
        if (articleTypeRepository.getById(id) == null) {
            return true;
        }
        throw new CustomException("Deletion failed: Incorrect ID");
    }

    public Page<ArticleTypeDTO> getAll(Pageable pageable) {
        Page<ArticleTypeEntity> page = articleTypeRepository.findAll(pageable);
        Long totalCount = page.getTotalElements();
        List<ArticleTypeEntity> entityList = page.getContent();

        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity entity : entityList) {
            dtoList.add(mapToDTO(entity));
        }

        return new PageImpl<ArticleTypeDTO>(dtoList, pageable, totalCount);
    }

    public ArticleTypeUserResponseDTO getByLang(Integer id, String name) {
        ArticleTypeEntity entity = articleTypeRepository.getById(id);
        if (entity == null) {
            throw new CustomException("Article not found!");
        }
        ArticleTypeUserResponseDTO dto = new ArticleTypeUserResponseDTO(entity.getId(), entity.getOrderNumber(), entity.getCreatedDate());
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

    public ArticleTypeEntity mapToEntity(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setId(dto.getId());
        entity.setVisible(dto.getVisible());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setCreatedDate(dto.getCreatedDate());

        return entity;
    }

    public ArticleTypeDTO mapToDTO(ArticleTypeEntity entity) {
        ArticleTypeDTO dto = new ArticleTypeDTO();
        dto.setId(entity.getId());
        dto.setVisible(entity.getVisible());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
}
