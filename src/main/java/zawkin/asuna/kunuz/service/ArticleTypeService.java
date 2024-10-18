package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.ArticleTypeDTO;
import zawkin.asuna.kunuz.exp.CustomException;
import zawkin.asuna.kunuz.repository.AricleTypeRepository;
import zawkin.asuna.kunuz.entity.ArticleTypeEntity;

import java.util.LinkedList;
import java.util.List;

@Service
public class ArticleTypeService {
    @Autowired
    private AricleTypeRepository aricleTypeRepository;

    public ArticleTypeDTO create(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = mapToEntity(dto);
        aricleTypeRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public ArticleTypeDTO update(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = aricleTypeRepository.getById(dto.getId());
        if (entity == null) {
            throw new CustomException("Article not found");
        }
        aricleTypeRepository.save(mapToEntity(dto));
        return dto;
    }

    public Boolean deleteById(Integer id) {
        aricleTypeRepository.deleteById(id);
        if (aricleTypeRepository.getById(id) == null) {
            return true;
        }
        throw new CustomException("Deletion failed: Incorrect ID");
    }

    public Page<ArticleTypeDTO> getAll(Pageable pageable) {
        Page<ArticleTypeEntity> page = aricleTypeRepository.findAll(pageable);
        Long totalCount = page.getTotalElements();
        List<ArticleTypeEntity> entityList = page.getContent();

        List<ArticleTypeDTO> dtoList = new LinkedList<>();
        for (ArticleTypeEntity entity : entityList) {
            dtoList.add(mapToDTO(entity));
        }

        return new PageImpl<ArticleTypeDTO>(dtoList, pageable, totalCount);
    }

    public static ArticleTypeEntity mapToEntity(ArticleTypeDTO dto) {
        ArticleTypeEntity entity = new ArticleTypeEntity();
        entity.setId(dto.getId());
        entity.setVisible(dto.getVisible());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setCreatedDate(dto.getCreatedDate());

        return entity;
    }

    public static ArticleTypeDTO mapToDTO(ArticleTypeEntity entity) {
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
