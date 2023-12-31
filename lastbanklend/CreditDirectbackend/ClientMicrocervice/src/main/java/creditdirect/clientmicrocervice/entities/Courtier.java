package creditdirect.clientmicrocervice.entities;



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

    @ManyToOne
    @JoinColumn(name = "agence_id") // Nom de la colonne pour la clé étrangère
    private Agence agence;



}