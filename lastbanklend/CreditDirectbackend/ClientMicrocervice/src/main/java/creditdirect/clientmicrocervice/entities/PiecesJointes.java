package creditdirect.clientmicrocervice.entities;


import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class PiecesJointes {

    @Column(name = "Piece_name")
    private String fileName;

    @Column(name = "Piece_path")
    private String filePath;

    public void setFileName(String fileName) {
    }

    public void setFilePath(String string) {
    }

    // Getters and setters for fileName and filePath
    // Constructors as needed
}
