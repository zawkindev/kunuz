package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.dto.ArticleDTO;
import zawkin.asuna.kunuz.dto.CategoryDTO;
import zawkin.asuna.kunuz.enums.ArticleEnum;
import zawkin.asuna.kunuz.service.ArticleService;

@RestController
@RequestMapping("/article")
public class ArticleController {
    @Autowired
    private ArticleService service;

    @PostMapping({"", "/"})
    public ResponseEntity<ArticleDTO> create(ArticleDTO article) {
        return ResponseEntity.ok(service.create(article));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> update(@PathVariable("id") String id, @RequestBody ArticleDTO article) {
        article.setId(id);
        return ResponseEntity.ok(service.update(id, article));
    }

    @PutMapping("/{id}/{status}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") String id, @PathVariable("status") ArticleEnum status) {
        return ResponseEntity.ok(service.changeStatus(id, status));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.delete(id));
    }

}
