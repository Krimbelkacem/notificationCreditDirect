package creditdirect.clientmicrocervice.entities;



import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "courtier_id")
public class Courtier extends Bankier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "agence_id") // Colonne pour stocker l'ID de l'agence
    private Long agenceId;


}