package zawkin.asuna.kunuz.service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import zawkin.asuna.kunuz.dto.RegistrationDTO;
import zawkin.asuna.kunuz.entity.ProfileEntity;
import zawkin.asuna.kunuz.enums.ProfileStatus;
import zawkin.asuna.kunuz.repository.ProfileRepository;
import zawkin.asuna.kunuz.util.MD5Util;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class AuthService {
    @Value("jumaniyazov.bobur@mail.ru")
    private String fromAccount;
    @Autowired
    private ProfileRepository profileRepository;
    @Autowired
    private MailSendingService mailSendingService;
    @Autowired
    private JavaMailSender javaMailSender;

    public String registration(RegistrationDTO dto) {
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MD5Util.md5(dto.getPassword()));
        entity.setSurname(dto.getSurname());
        entity.setStatus(ProfileStatus.IN_REGISTRATION);
        entity.setVisible(Boolean.TRUE);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);

        StringBuilder sb = new StringBuilder();
        sb.append("<h1 style=\"text-align: center\"> Complete Registration</h1>");
        sb.append("<br>");
        sb.append("<p>Click the link below to complete registration</p>\n");
        sb.append("<p><a style=\"padding: 5px; background-color: indianred; color: white\"  href=\"http://localhost:8081/auth/registration/confirm/")
                .append(entity.getId()).append("\" target=\"_blank\">Click Th</a></p>\n");

//        StringBuilder sb = new StringBuilder();
//        sb.append("Click the link below to complete registration.\n");
//        sb.append("http://localhost:8081/auth/registration/confirm/").append(entity.getId()).append("\n");


        return sendMimeMessage(dto.getEmail(), "Verificate your email", sb.toString());
    }


    public String sendMimeMessage(String to, String subject, String text) {
        try {
            MimeMessage msg = javaMailSender.createMimeMessage();
            msg.setFrom(fromAccount);
            MimeMessageHelper helper = new MimeMessageHelper(msg, true);

            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(text, true);
            javaMailSender.send(msg);
        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }

        return "Mail was send";
    }

    public String sendSimpleMessage(String to, String subject, String text) {
        SimpleMailMessage msg = new SimpleMailMessage();
        msg.setFrom(fromAccount);
        msg.setTo(to);
        msg.setSubject(subject);
        msg.setText(text);
        javaMailSender.send(msg);

        return "Mail was send";
    }

    public String registrationConfirm(Integer id) {
        Optional<ProfileEntity> optional = profileRepository.findByIdAndVisibleTrue(id);
        if (optional.isEmpty()) {
            return "Not Completed";
        }
        ProfileEntity entity = optional.get();
        if (!entity.getStatus().equals(ProfileStatus.IN_REGISTRATION)) {
            return "Not Completed";
        }

        LocalDateTime now = LocalDateTime.now();
        Duration threshold = Duration.ofMinutes(5);
        Duration duration = Duration.between(now, entity.getCreatedDate());
        if (entity.getStatus().equals(ProfileStatus.IN_REGISTRATION) && duration.compareTo(threshold) > 0) {
            return "Not Completed";
        }
        entity.setStatus(ProfileStatus.ACTIVE);
        profileRepository.save(entity);
        return "Completed";
    }
}
