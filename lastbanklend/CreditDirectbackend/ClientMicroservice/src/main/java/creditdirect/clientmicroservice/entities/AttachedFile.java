package creditdirect.clientmicroservice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AttachedFile {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    public void setFileName(String fileName) {
    }

    public void setFilePath(String string) {
    }

    // Getters and setters for fileName and filePath
    // Constructors as needed
}
