package zawkin.asuna.kunuz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.profile.ProfileFilterDTO;
import zawkin.asuna.kunuz.dto.profile.ProfileDTO;
import zawkin.asuna.kunuz.entity.ProfileEntity;
import zawkin.asuna.kunuz.exp.CustomException;
import zawkin.asuna.kunuz.repository.FilterProfileRepository;
import zawkin.asuna.kunuz.repository.ProfileRepository;

import java.time.LocalDateTime;
import java.util.LinkedList;
import java.util.List;

@Service
public class ProfileService {
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private FilterProfileRepository filterProfileRepository;

    public ProfileDTO create(ProfileDTO dto) {
        dto.setCreatedDate(LocalDateTime.now());
        dto.setVisible(true);
        ProfileEntity entity = mapToEntity(dto);
        profileRepository.save(entity);

        dto.setId(entity.getId());
        return dto;
    }

    public ProfileDTO update(ProfileDTO dto) {
        ProfileEntity entity = profileRepository.getById(dto.getId());
        if (entity == null) {
            throw new CustomException("Article not found");
        }
        profileRepository.save(mapToEntity(dto));
        return dto;
    }

    public Page<ProfileDTO> getAll(Pageable pageable) {
        Page<ProfileEntity> page = profileRepository.findAll(pageable);
        Long totalCount = page.getTotalElements();
        List<ProfileEntity> entityList = page.getContent();

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            dtoList.add(mapToDTO(entity));
        }

        return new PageImpl<ProfileDTO>(dtoList, pageable, totalCount);
    }

    public Boolean deleteById(Integer id) {
        profileRepository.deleteById(id);
        if (profileRepository.getById(id) == null) {
            return true;
        }
        throw new CustomException("Deletion failed: Incorrect ID");
    }

    public Boolean updatePhotoId(Integer id, Integer photoId) {
        ProfileEntity entity = profileRepository.getById(id);
        if (entity == null) {
            throw new CustomException("Article not found");
        }
        entity.setPhotoId(photoId);
        profileRepository.save(entity);

        return true;
    }

    public PageImpl<ProfileDTO> filter(ProfileFilterDTO filter, int page, int size) {
        Page<ProfileEntity> result = filterProfileRepository.filter(filter, page, size);

        long totalCount = result.getTotalElements();
        List<ProfileEntity> entityList = result.getContent();

        List<ProfileDTO> dtoList = new LinkedList<>();
        for (ProfileEntity entity : entityList) {
            dtoList.add(mapToDTO(entity));
        }

        PageRequest pageRequest = PageRequest.of(page, size);
        return new PageImpl<>(dtoList, pageRequest, totalCount);
    }

    public ProfileEntity mapToEntity(ProfileDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setId(dto.getId());
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPhone(dto.getPhone());
        entity.setPassword(dto.getPassword());
        entity.setStatus(dto.getStatus());
        entity.setRole(dto.getRole());
        entity.setVisible(dto.getVisible());
        entity.setCreatedDate(dto.getCreatedDate());
        entity.setPhotoId(dto.getPhotoId());

        return entity;
    }

    public ProfileDTO mapToDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setPhone(entity.getPhone());
        dto.setPassword(entity.getPassword());
        dto.setStatus(entity.getStatus());
        dto.setRole(entity.getRole());
        dto.setVisible(entity.getVisible());
        dto.setCreatedDate(entity.getCreatedDate());
        dto.setPhotoId(entity.getPhotoId());

        return dto;
    }
}
