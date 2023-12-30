package creditdirect.clientmicrocervice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
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


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_credit_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TypeCredit typeCredit;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_financement_id")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private TypeFinancement typeFinancement;

    @ElementCollection
    @CollectionTable(name = "CreditAttachments", joinColumns = @JoinColumn(name = "Dossier_id"))
    private List<AttachedFile> attachedFiles;

    @Column(columnDefinition = "json")
    private String simulationInfo;

    @ManyToOne
    @JoinColumn(name = "courtier_id") // Assuming this column holds the reference to the Courtier
    private Courtier assignedCourtier;
    @Enumerated(EnumType.STRING)
    private DossierStatus status;

    // Constructors, getters, and setters can be added here if needed
}

