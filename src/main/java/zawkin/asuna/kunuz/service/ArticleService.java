package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.ArticleDTO;
import zawkin.asuna.kunuz.entity.ArticleEntity;
import zawkin.asuna.kunuz.enums.ArticleEnum;
import zawkin.asuna.kunuz.exp.CustomException;
import zawkin.asuna.kunuz.repository.ArticleRepository;

@Service
public class ArticleService {
    @Autowired
    private ArticleRepository articleRepository;

    public ArticleDTO create(ArticleDTO dto) {
        dto.setVisible(true);
        ArticleEntity entity = mapToEntity(dto);
        dto.setId(entity.getId());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }

    public ArticleDTO update(String id,ArticleDTO dto) {
        ArticleEntity entity = articleRepository.findByIdAndVisibleTrue(id);
        if (entity==null){
            throw new CustomException("artice not found");
        }
        entity = mapToEntity(dto);
        articleRepository.save(entity);

        return mapToDTO(entity);
    }

    public Boolean delete(String id) {
        int result = articleRepository.changeVisible(id);
        return result > 0;
    }

    public Boolean changeStatus(String id, ArticleEnum status){
        int result = articleRepository.changeStatus(id, status);
        return result > 0;
    }


    public ArticleEntity mapToEntity(ArticleDTO dto) {
        if (dto == null) {
            return null;
        }

        ArticleEntity entity = new ArticleEntity();
        entity.setId(dto.getId());
        entity.setTitle(dto.getTitle());
        entity.setSharedCount(dto.getSharedCount());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setPublishedDate(dto.getPublishedDate());
        entity.setVisible(dto.getVisible());

        return entity;
    }

    public ArticleDTO mapToDTO(ArticleEntity entity) {
        if (entity == null) {
            return null;
        }

        ArticleDTO dto = new ArticleDTO();
        dto.setId(entity.getId());
        dto.setTitle(entity.getTitle());
        dto.setDescription(entity.getDescription());
        dto.setContent(entity.getContent());
        dto.setSharedCount(entity.getSharedCount());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPublishedDate(entity.getPublishedDate());
        dto.setVisible(entity.getVisible());

        return dto;
    }


    public ArticleEntity get(String id) {
        return articleRepository.findById(id).orElseThrow(() -> {
            throw new CustomException("ArticleType Not Found");
        });
    }
}
