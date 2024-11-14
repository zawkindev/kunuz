package zawkin.asuna.kunuz.config;

import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
public class AttachConfig {

    @Value("${upload.folder}")
    private String uploadFolder;

    @PostConstruct
    public void init() throws IOException {
        Path uploadPath;

        // If path is absolute, use it as is
        if (uploadFolder.startsWith("/") || uploadFolder.contains(":")) {
            uploadPath = Paths.get(uploadFolder);
        } else {
            // If relative path, create it relative to project root
            uploadPath = Paths.get(System.getProperty("user.dir"), uploadFolder);
        }

        // Create directories if they don't exist
        Files.createDirectories(uploadPath);

        // Log the absolute path being used
        System.out.println("Upload directory initialized at: " + uploadPath.toAbsolutePath());
    }
}

// Modified AttachService.java - update the upload method
