package zawkin.asuna.kunuz.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.dto.FilterProfileDTO;
import zawkin.asuna.kunuz.dto.ProfileDTO;
import zawkin.asuna.kunuz.service.ProfileService;

@RestController
@RequestMapping("/profile")
public class ProfileController {
    @Autowired
    private ProfileService profileService;

    @PostMapping({"", "/"})
    public ResponseEntity<ProfileDTO> create(ProfileDTO profile) {
        return ResponseEntity.ok(profileService.create(profile));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProfileDTO> update(@PathVariable("id") Integer id, ProfileDTO profile) {
        profile.setId(id);
        return ResponseEntity.ok(profileService.update(profile));
    }

    @PutMapping("/{id}/{photoId}")
    public ResponseEntity<Boolean> updatePhotoId(@PathVariable("id") Integer id, @PathVariable("photoId") Integer photoId) {
        return ResponseEntity.ok(profileService.updatePhotoId(id, photoId));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(profileService.deleteById(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Page<ProfileDTO>> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(profileService.getAll(PageRequest.of(page, size)));
    }

    @PostMapping("/filter")
    public ResponseEntity<PageImpl<ProfileDTO>> filter(@RequestParam(value = "page", defaultValue = "1") int page, @RequestParam(value = "size", defaultValue = "30") int size, @RequestBody FilterProfileDTO filter) {
        PageImpl<ProfileDTO> result = profileService.filter(filter, page - 1, size);
        return ResponseEntity.ok(result);
    }
}
