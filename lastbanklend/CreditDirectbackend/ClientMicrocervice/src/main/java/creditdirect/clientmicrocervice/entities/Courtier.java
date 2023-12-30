package creditdirect.clientmicrocervice.entities;



import lombok.Data;
import jakarta.persistence.*;

import java.util.List;

@Data
@Entity
@PrimaryKeyJoinColumn(name = "courtier_id")
public class Courtier extends Bankier {

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @OneToMany(mappedBy = "assignedCourtier") // Assuming a mappedBy relationship exists in Dossier class
    private List<Dossier> dossiers;


}