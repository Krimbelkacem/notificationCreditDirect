package creditdirect.clientmicrocervice.entities;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "typecredit")
public class TypeCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom_credit")
    private String nomCredit;

    @Column(name = "id_financement")
    private Long idFinancement;


    @Column(name = "prix", nullable = true)
    private Double prix;

    @Column(name = "image")
    private String image;

    public void setImagePath(String imagePath) {
        this.image = imagePath;
    }

    public String getImagePath() {
        return this.image;
    }
}
