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

    @Column(name = "id_financement") // Foreign key column
    private Long idFinancement;
}
