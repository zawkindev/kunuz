package zawkin.asuna.kunuz.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class ArticleTypeUserResponseDTO {
    private Integer id;
    private Integer orderNumber;
    private String name;
    private LocalDateTime createdDate;

    public ArticleTypeUserResponseDTO(Integer id, Integer orderNumber, LocalDateTime createdDate) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.createdDate = createdDate;
    }
}
