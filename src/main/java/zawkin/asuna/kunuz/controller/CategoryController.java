package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.dto.CategoryDTO;
import zawkin.asuna.kunuz.dto.CategoryUserResponseDTO;
import zawkin.asuna.kunuz.service.CategoryService;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;


    @PostMapping({"", "/"})
    public ResponseEntity<CategoryDTO> create(CategoryDTO category) {
        return ResponseEntity.ok(categoryService.create(category));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryDTO> update(@PathVariable("id") Integer id, CategoryDTO category) {
        category.setId(id);
        return ResponseEntity.ok(categoryService.update(category));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.deleteById(id));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<Page<CategoryDTO>> getAll(@RequestParam Integer page, @RequestParam Integer size) {
        return ResponseEntity.ok(categoryService.getAll(PageRequest.of(page, size)));
    }

    @GetMapping("/{name}/{id}")
    public ResponseEntity<CategoryUserResponseDTO> getByLang(@PathVariable("name") String name, @PathVariable("id") Integer id) {
        return ResponseEntity.ok(categoryService.getByLang(id, name));
    }
}
