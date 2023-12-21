package creditdirect.clientmicroservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
@Table(name = "TypeCredit")
public class TypeCredit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeCreditID")
    private Long id;

    @Column(name = "NomType", length = 50)
    private String nomType; // For example, 'Prêt personnel', 'Crédit auto', etc.

    @ManyToOne
    @JoinColumn(name = "TypeFinancementID")
    private TypeFinancement typeFinancement; // Establishing ManyToOne relationship with TypeFinancement
}
