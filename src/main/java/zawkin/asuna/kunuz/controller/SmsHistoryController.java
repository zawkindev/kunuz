package zawkin.asuna.kunuz.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import zawkin.asuna.kunuz.dto.SmsHistoryDTO;
import zawkin.asuna.kunuz.service.SmsHistoryService;

import java.time.LocalDate;
import java.util.List;

public class SmsHistoryController {
    @Autowired
    private SmsHistoryService service;

    @GetMapping("/by-sms")
    public ResponseEntity<List<SmsHistoryDTO>> getBySms(@RequestParam("email") String email) {
        return ResponseEntity.ok(service.getBySms(email));
    }

    @GetMapping("/by-email")
    public ResponseEntity<List<SmsHistoryDTO>> getByDate(@RequestParam("to") LocalDate to, @RequestParam("from") LocalDate from) {
        return ResponseEntity.ok(service.getByDate(from, to));
    }

    @GetMapping("/all")
    public ResponseEntity<PageImpl<SmsHistoryDTO>> getAll(@RequestParam("page") int page, @RequestParam("size") int size) {
        return ResponseEntity.ok(service.getAllWithPagination(page, size));
    }
}
