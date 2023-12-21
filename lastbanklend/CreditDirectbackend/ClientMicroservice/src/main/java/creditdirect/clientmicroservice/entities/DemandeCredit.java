package creditdirect.clientmicroservice.entities;

import javax.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "DemandeCredit")
public class DemandeCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "DemandeID")
    private Long id;

    // Other fields for DemandeDeCredit entity

    @ManyToOne
    @JoinColumn(name = "TypeCreditID")
    private TypeCredit typeCredit; // Establishing ManyToOne relationship with TypeCredit

    @ManyToOne
    @JoinColumn(name = "TypeFinancementID")
    private TypeFinancement TypeFinancement; // Establishing ManyToOne relationship with FinancingType

    @ElementCollection
    @CollectionTable(name = "CreditAttachments", joinColumns = @JoinColumn(name = "demande_id"))
    private List<File> attachedFiles; // Store file information (name and path)
}
