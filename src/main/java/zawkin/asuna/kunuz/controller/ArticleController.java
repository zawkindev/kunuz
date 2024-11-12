package zawkin.asuna.kunuz.controller;

import org.hibernate.validator.constraints.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.dto.article.ArticleDTO;
import zawkin.asuna.kunuz.dto.article.ArticleFullInfoDTO;
import zawkin.asuna.kunuz.dto.article.ArticleShortInfoDTO;
import zawkin.asuna.kunuz.enums.ArticleEnum;
import zawkin.asuna.kunuz.service.ArticleService;

import java.util.List;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    private ArticleService service;

    // 1. Create article
    @PostMapping({"", "/"})
    public ResponseEntity<ArticleDTO> create(@RequestBody ArticleDTO article) {
        return ResponseEntity.ok(service.create(article));
    }

    // 2. Update article
    @PutMapping("/{id}")
    public ResponseEntity<ArticleDTO> update(@PathVariable("id") String id, @RequestBody ArticleDTO article) {
        article.setId(id);
        return ResponseEntity.ok(service.update(id, article));
    }

    // 3. Delete article
    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> delete(@PathVariable("id") String id) {
        return ResponseEntity.ok(service.delete(id));
    }

    // 4. Change article status
    @PutMapping("/{id}/status/{status}")
    public ResponseEntity<Boolean> changeStatus(@PathVariable("id") String id, @PathVariable("status") ArticleEnum status) {
        return ResponseEntity.ok(service.changeStatus(id, status));
    }

    // 5. Get last 5 articles by types, ordered by creation date
    @GetMapping("/last5bytype")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLast5ByType(@RequestParam List<String> articleTypes) {
        return ResponseEntity.ok(service.getLast5ByType(articleTypes));
    }

    // 6. Get last 3 articles by types, ordered by creation date
    @GetMapping("/last3bytype")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLast3ByType(@RequestParam List<String> articleTypes) {
        return ResponseEntity.ok(service.getLast3ByType(articleTypes));
    }

    // 7. Get last 8 articles excluding given IDs
    @GetMapping("/last8excluding")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLast8ExcludingIds(@RequestParam List<String> excludedIds) {
        return ResponseEntity.ok(service.getLast8ExcludingIds(excludedIds));
    }

    // 8. Get article by ID and language
    @GetMapping("/{id}/lang/{lang}")
    public ResponseEntity<ArticleFullInfoDTO> getArticleByIdAndLang(@PathVariable String id, @PathVariable String lang) {
        return ResponseEntity.ok(service.getArticleByIdAndLang(id, lang));
    }

    // 9. Get last 4 articles by types, excluding a specific article ID
    @GetMapping("/last4bytype")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLast4ByTypeAndExcludeId(@RequestParam List<String> articleTypes, @RequestParam String excludedId) {
        return ResponseEntity.ok(service.getLast4ByTypeAndExcludeId(articleTypes, excludedId));
    }

    // 10. Get 4 most read articles
    @GetMapping("/top4byviews")
    public ResponseEntity<List<ArticleShortInfoDTO>> getTop4ByViews() {
        return ResponseEntity.ok(service.getTop4ByViews());
    }

    // 11. Get last 4 articles by tag name
    @GetMapping("/last4bytag")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLast4ByTagName(@RequestParam String tagName) {
        return ResponseEntity.ok(service.getLast4ByTagName(tagName));
    }

    // 12. Get last 5 articles by types and region key
    @GetMapping("/last5bytypeandregion")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLast5ByTypeAndRegion(@RequestParam List<String> articleTypes, @RequestParam String regionKey) {
        return ResponseEntity.ok(service.getLast5ByTypeAndRegion(articleTypes, regionKey));
    }

    // 13. Get paginated article list by region key
    @GetMapping("/byregion")
    public ResponseEntity<Page<ArticleShortInfoDTO>> getByRegion(@RequestParam String regionKey, Pageable pageable) {
        return ResponseEntity.ok(service.getByRegion(regionKey, pageable));
    }

    // 14. Get last 5 articles by category key
    @GetMapping("/last5bycategory")
    public ResponseEntity<List<ArticleShortInfoDTO>> getLast5ByCategory(@RequestParam String categoryKey) {
        return ResponseEntity.ok(service.getLast5ByCategory(categoryKey));
    }

    // 15. Get paginated article list by category key
    @GetMapping("/bycategory")
    public ResponseEntity<Page<ArticleShortInfoDTO>> getByCategory(@RequestParam String categoryKey, Pageable pageable) {
        return ResponseEntity.ok(service.getByCategory(categoryKey, pageable));
    }

    // 16. Increase article view count
    @PutMapping("/{id}/incrementView")
    public ResponseEntity<Boolean> incrementViewCount(@PathVariable String id) {
        return ResponseEntity.ok(service.incrementViewCount(id));
    }

    // 17. Increase article share count
    @PutMapping("/{id}/incrementShare")
    public ResponseEntity<Boolean> incrementShareCount(@PathVariable String id) {
        return ResponseEntity.ok(service.incrementShareCount(id));
    }
}
