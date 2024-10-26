package zawkin.asuna.kunuz.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "email_history")
public class EmailHistoryEntity {
    @GeneratedValue(strategy = GenerationType.UUID)
    @Id
    private String id;
    @Column
    private String message;
    @Column
    private String email;
    @Column
    private LocalDateTime createdDate;
}
