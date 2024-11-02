package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.category.CategoryDTO;
import zawkin.asuna.kunuz.dto.category.CategoryUserResponseDTO;
import zawkin.asuna.kunuz.entity.CategoryEntity;
import zawkin.asuna.kunuz.exp.CustomException;
import zawkin.asuna.kunuz.repository.CategoryRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryDTO create(CategoryDTO dto) {
        dto.setCreatedDate(LocalDateTime.now());
        dto.setVisible(true);
        CategoryEntity entity = mapToEntity(dto);
        categoryRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public CategoryDTO update(CategoryDTO dto) {
        CategoryEntity entity = categoryRepository.getById(dto.getId());
        if (entity == null) {
            throw new CustomException("Article not found");
        }
        categoryRepository.save(mapToEntity(dto));
        return dto;
    }

    public Boolean deleteById(Integer id) {
        categoryRepository.deleteById(id);
        if (categoryRepository.getById(id) == null) {
            return true;
        }
        throw new CustomException("Deletion failed: Incorrect ID");
    }

    public Page<CategoryDTO> getAll(Pageable pageable) {
        Page<CategoryEntity> page = categoryRepository.findAll(pageable);
        Long totalCount = page.getTotalElements();
        List<CategoryEntity> entityList = page.getContent();

        List<CategoryDTO> dtoList = new LinkedList<>();
        for (CategoryEntity entity : entityList) {
            dtoList.add(mapToDTO(entity));
        }

        return new PageImpl<CategoryDTO>(dtoList, pageable, totalCount);
    }

    public CategoryUserResponseDTO getByLang(Integer id, String name) {
        CategoryEntity entity = categoryRepository.getById(id);
        if (entity == null) {
            throw new CustomException("Article not found!");
        }
        CategoryUserResponseDTO dto = new CategoryUserResponseDTO(entity.getId(), entity.getOrderNumber(), entity.getCreatedDate());
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

    public CategoryEntity mapToEntity(CategoryDTO dto) {
        CategoryEntity entity = new CategoryEntity();
        entity.setId(dto.getId());
        entity.setNameUz(dto.getNameUz());
        entity.setNameEn(dto.getNameEn());
        entity.setNameRu(dto.getNameRu());
        entity.setCreatedDate(dto.getCreatedDate());

        return entity;
    }

    public CategoryDTO mapToDTO(CategoryEntity entity) {
        CategoryDTO dto = new CategoryDTO();
        dto.setId(entity.getId());
        dto.setNameUz(entity.getNameUz());
        dto.setNameEn(entity.getNameEn());
        dto.setNameRu(entity.getNameRu());
        dto.setCreatedDate(entity.getCreatedDate());

        return dto;
    }
}
