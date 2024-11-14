package zawkin.asuna.kunuz.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import zawkin.asuna.kunuz.dto.AttachDTO;
import zawkin.asuna.kunuz.entity.AttachEntity;
import zawkin.asuna.kunuz.repository.AttachRepository;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Calendar;
import java.util.UUID;

@Service
@Slf4j
public class AttachService {
    @Value("${upload.folder}")
    private String uploadFolder;

    @Autowired
    private AttachRepository attachRepository;

    public AttachDTO upload(MultipartFile file) {
        try {
            String pathFolder = getYmDString();
            File folder = new File(uploadFolder + pathFolder);
            if (!folder.exists()) {
                folder.mkdirs();
            }

            String extension = getExtension(file.getOriginalFilename());
            String fileName = UUID.randomUUID().toString();
            String path = pathFolder + "/" + fileName + "." + extension;

            File serverFile = new File(uploadFolder + path);
            Files.copy(file.getInputStream(), serverFile.toPath());

            AttachEntity entity = new AttachEntity();
            entity.setId(fileName);
            entity.setOriginalName(file.getOriginalFilename());
            entity.setPath(path);
            entity.setSize(file.getSize());
            entity.setExtension(extension);
            entity.setCreatedDate(LocalDateTime.now());

            attachRepository.save(entity);

            return toDTO(entity);
        } catch (IOException e) {
            log.error("Error while uploading file", e);
            throw new RuntimeException("Could not upload file");
        }
    }

    public Resource download(String id) {
        AttachEntity entity = getEntity(id);
        try {
            Path file = Paths.get(uploadFolder + entity.getPath());
            Resource resource = new UrlResource(file.toUri());

            if (resource.exists() || resource.isReadable()) {
                return resource;
            } else {
                throw new RuntimeException("Could not read the file!");
            }
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public byte[] open(String id) {
        AttachEntity entity = getEntity(id);
        try {
            Path file = Paths.get(uploadFolder + entity.getPath());
            return Files.readAllBytes(file);
        } catch (IOException e) {
            log.error("Error while opening file", e);
            throw new RuntimeException("Could not open file");
        }
    }

    public void delete(String id) {
        AttachEntity entity = getEntity(id);
        File file = new File(uploadFolder + entity.getPath());
        if (file.exists()) {
            file.delete();
        }
        attachRepository.deleteById(id);
    }

    public Page<AttachDTO> getPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<AttachEntity> entityPage = attachRepository.findAll(pageable);
        return entityPage.map(this::toDTO);
    }

    private AttachEntity getEntity(String id) {
        return attachRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));
    }

    private String getYmDString() {
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int month = Calendar.getInstance().get(Calendar.MONTH) + 1;
        int day = Calendar.getInstance().get(Calendar.DATE);
        return String.format("/%d/%d/%d", year, month, day);
    }

    private String getExtension(String fileName) {
        if (fileName == null) return "";
        int lastIndex = fileName.lastIndexOf(".");
        return lastIndex > 0 ? fileName.substring(lastIndex + 1) : "";
    }

    private AttachDTO toDTO(AttachEntity entity) {
        AttachDTO dto = new AttachDTO();
        dto.setId(entity.getId());
        dto.setOriginalName(entity.getOriginalName());
        dto.setPath(entity.getPath());
        dto.setSize(entity.getSize());
        dto.setExtension(entity.getExtension());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
