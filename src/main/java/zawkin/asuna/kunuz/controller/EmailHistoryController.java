package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import zawkin.asuna.kunuz.dto.EmailHistoryDTO;
import zawkin.asuna.kunuz.service.EmailHistoryService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/email_history")
public class EmailHistoryController {
    @Autowired
    private EmailHistoryService service;

    @GetMapping({"", "/"})
    public ResponseEntity<List<EmailHistoryDTO>> getByEmail(@RequestParam("email") String email) {
        return ResponseEntity.ok(service.getByEmail(email));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<List<EmailHistoryDTO>> getByDate(@RequestParam("to") LocalDate to, @RequestParam("from") LocalDate from) {
        return ResponseEntity.ok(service.getByDate(from, to));
    }

    @GetMapping({"", "/"})
    public ResponseEntity<PageImpl<EmailHistoryDTO>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(service.getAllWithPagination(page, size));
    }

}
