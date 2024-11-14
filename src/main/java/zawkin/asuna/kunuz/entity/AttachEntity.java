package zawkin.asuna.kunuz.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "attach")
public class AttachEntity {
    @Id
    private String id; // UUID

    @Column(name = "original_name")
    private String originalName;

    private String path;
    private Long size;
    private String extension;

    @Column(name = "created_date")
    private LocalDateTime createdDate;
}
