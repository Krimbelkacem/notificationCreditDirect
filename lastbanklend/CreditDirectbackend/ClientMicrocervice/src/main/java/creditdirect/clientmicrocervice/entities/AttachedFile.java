package creditdirect.clientmicrocervice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AttachedFile {

    @Column(name = "file_name")
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    public AttachedFile() {
        // Default constructor
    }

    public AttachedFile(String fileName, String filePath) {
        this.fileName = fileName;
        this.filePath = filePath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }
}
