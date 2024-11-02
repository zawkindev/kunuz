package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.dto.region.RegionDTO;
import zawkin.asuna.kunuz.dto.region.RegionUserResponseDTO;
import zawkin.asuna.kunuz.service.RegionService;

@RestController
@RequestMapping("/region")
public class RegionController {
    @Autowired
    private RegionService regionService;


    @PostMapping({"", "/"})
    public ResponseEntity<RegionDTO> create(RegionDTO region) {
        return ResponseEntity.ok(regionService.create(region));
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionDTO> update(@PathVariable("id") Integer id, RegionDTO region) {
        region.setId(id);
        return ResponseEntity.ok(regionService.update(region));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(regionService.deleteById(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Page<RegionDTO>> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(regionService.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{name}/{id}")
    public ResponseEntity<RegionUserResponseDTO> getByLang(@PathVariable("name") String name, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(regionService.getByLang(id, name));
    }
}
