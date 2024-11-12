package zawkin.asuna.kunuz.dto;

import org.hibernate.validator.constraints.UUID;

public class ImageDTO {
    private UUID id;

    private String url;
    private String altText;
    private String title;
}
