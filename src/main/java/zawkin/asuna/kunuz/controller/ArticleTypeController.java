package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.dto.ArticleTypeDTO;
import zawkin.asuna.kunuz.service.ArticleTypeService;

@RestController(value = "/article_type")
public class ArticleTypeController {
    @Autowired
    private ArticleTypeService articleTypeService;

    @PostMapping({"", "/"})
    public ResponseEntity<ArticleTypeDTO> create(ArticleTypeDTO articleType) {
        return ResponseEntity.ok(articleTypeService.create(articleType));
    }

    @PutMapping({"", "/"})
    public ResponseEntity<ArticleTypeDTO> update(ArticleTypeDTO articleType) {
        return ResponseEntity.ok(articleTypeService.update(articleType));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id){
        return ResponseEntity.ok(articleTypeService.deleteById(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Page<ArticleTypeDTO>> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(articleTypeService.getAll(PageRequest.of(page,size)));
    }
}
