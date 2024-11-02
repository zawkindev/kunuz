package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import jdk.jfr.ContentType;
import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.PhoneTypeEnum;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "sms_history")
public class SmsHistoryEntity {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;
    @Column
    private String phone;
    @Column
    private String message;
    @Column
    private LocalDateTime createdDate;
    @Column
    private PhoneTypeEnum type;
    @Column
    private String code;
    @Column
    private String sms;
}
