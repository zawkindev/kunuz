package zawkin.asuna.kunuz.dto.category;

import lombok.Getter;
import lombok.Setter;
import zawkin.asuna.kunuz.enums.LanguageEnum;

import java.time.LocalDateTime;

@Getter
@Setter
public class CategoryUserResponseDTO {
    private Integer id;
    private Integer orderNumber;
    private LanguageEnum name;
    private LocalDateTime createdDate;

    public CategoryUserResponseDTO(Integer id, Integer orderNumber, LocalDateTime createdDate) {
        this.id = id;
        this.orderNumber = orderNumber;
        this.createdDate = createdDate;
    }
}
