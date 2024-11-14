package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import zawkin.asuna.kunuz.dto.AttachDTO;
import zawkin.asuna.kunuz.entity.AttachEntity;
import zawkin.asuna.kunuz.repository.AttachRepository;
import zawkin.asuna.kunuz.service.AttachService;

@RestController
@RequestMapping("/attach")
public class AttachController {
    @Autowired
    private AttachService attachService;

    @Autowired
    private AttachRepository attachRepository;

    @PostMapping("/upload")
    public ResponseEntity<AttachDTO> upload(@RequestParam("file") MultipartFile file) {
        AttachDTO result = attachService.upload(file);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<Resource> download(@PathVariable String id) {
        Resource file = attachService.download(id);
        AttachEntity entity = attachRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Attachment not found"));

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=\"" + entity.getOriginalName() + "\"")
                .body(file);
    }

    @GetMapping("/open/{id}")
    public ResponseEntity<byte[]> open(@PathVariable String id) {
        byte[] data = attachService.open(id);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_TYPE, MediaType.ALL_VALUE)
                .body(data);
    }

    @GetMapping("/open-general/{id}")
    public ResponseEntity<byte[]> openGeneral(@PathVariable String id) {
        return open(id);
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        attachService.delete(id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pagination")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Page<AttachDTO>> getPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        return ResponseEntity.ok(attachService.getPagination(page, size));
    }
}
