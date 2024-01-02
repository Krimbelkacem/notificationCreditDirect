package creditdirect.clientmicrocervice.entities;
import lombok.Data;
import jakarta.persistence.*;

@Entity
@Data
@PrimaryKeyJoinColumn(name = "particulier_id")
public class Particulier extends Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    @Column(name = "civilite")
    private String civilite;

    @Column(name = "nom")
    private String nom;

    @Column(name = "prenom")
    private String prenom;

    @Column(name = "telephone")
    private String telephone;

    @Column(name = "nationalite")
    private String nationalite;

    @Column(name = "adresse")
    private String adresse;

    @Column(name = "ville")
    private String ville;

    @Column(name = "code_postal")
    private String codePostal;

    @Column(name = "resides_in_algeria")
    private boolean residesInAlgeria;



}