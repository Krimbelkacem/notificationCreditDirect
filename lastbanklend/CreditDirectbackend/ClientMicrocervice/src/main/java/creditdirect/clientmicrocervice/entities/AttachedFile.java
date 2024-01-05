package creditdirect.clientmicrocervice.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class AttachedFile {



    @Column(name = "file_name",unique = true)
    private String fileName;

    @Column(name = "file_path")
    private String filePath;

    @Column(name = "file_type") // Add a column for file type
    private String fileType;

    public AttachedFile() {
        // Default constructor
    }

    public AttachedFile(String fileName, String filePath, String fileType) {
        this.fileName = fileName;
        this.filePath = filePath;
        this.fileType = fileType;
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


    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }
}
