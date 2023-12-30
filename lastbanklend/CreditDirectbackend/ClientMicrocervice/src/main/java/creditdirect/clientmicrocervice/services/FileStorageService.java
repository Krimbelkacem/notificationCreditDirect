package creditdirect.clientmicrocervice.services;


import creditdirect.clientmicrocervice.config.FileStorageProperties;
import creditdirect.clientmicrocervice.entities.AttachedFile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Service
public class FileStorageService {

    private final Path fileStorageLocation;

    @Autowired
    public FileStorageService(FileStorageProperties fileStorageProperties) {
        this.fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();

        try {
            Files.createDirectories(this.fileStorageLocation);
        } catch (IOException ex) {
            // Handle directory creation exception
        }
    }

    public List<AttachedFile> storeFiles(MultipartFile[] files) {
        List<AttachedFile> attachedFiles = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            String fileUuid = UUID.randomUUID().toString();
            String filePath = this.fileStorageLocation.resolve(fileUuid + "-" + fileName).toString();

            try {
                Files.copy(file.getInputStream(), Paths.get(filePath));
                AttachedFile attachedFile = new AttachedFile();
                attachedFile.setFileName(fileName);
                attachedFile.setFilePath(filePath);
                attachedFiles.add(attachedFile);
            } catch (IOException ex) {
                // Handle file storage exception
            }
        }

        return attachedFiles;
    }
}
