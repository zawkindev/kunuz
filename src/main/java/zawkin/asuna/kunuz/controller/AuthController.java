package zawkin.asuna.kunuz.controller;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import zawkin.asuna.kunuz.dto.AuthDTO;
import zawkin.asuna.kunuz.dto.ProfileDTO;
import zawkin.asuna.kunuz.dto.RegistrationDTO;
import zawkin.asuna.kunuz.service.AuthService;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping("/registration")
    public ResponseEntity<String> registration(@RequestBody RegistrationDTO dto) {
        return ResponseEntity.ok(authService.registration(dto));
    }

    @GetMapping("/registration/confirm/{id}")
    public ResponseEntity<String> registration(@PathVariable Integer id) {
        return ResponseEntity.ok(authService.registrationConfirm(id));
    }

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody @Valid AuthDTO dto) {
        return ResponseEntity.ok(authService.login(dto));
    }


}
