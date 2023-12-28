package creditdirect.clientmicrocervice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "Dossier")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DossierID")
    private Long id;

    // Other fields for DemandeDeCredit entity
    @ManyToOne
    @JoinColumn(name = "client_id") // Name of the foreign key column in DemandeCredit table
    private Client client; // Represents a single Client for each DemandeCredit

    @ManyToOne
    @JoinColumn(name = "TypeCreditID")
    private TypeCredit typeCredit; // Establishing ManyToOne relationship with TypeCredit

    @ManyToOne
    @JoinColumn(name = "TypeFinancementID")
    private TypeFinancement typeFinancement; // Establishing ManyToOne relationship with FinancingType

    @ElementCollection
    @CollectionTable(name = "PiecesJointes", joinColumns = @JoinColumn(name = "Dossier_id"))
    private List<PiecesJointes> PiecesJointes;



}