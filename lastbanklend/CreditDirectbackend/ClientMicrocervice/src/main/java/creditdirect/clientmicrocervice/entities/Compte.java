package creditdirect.clientmicrocervice.entities;
import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "comptes")
public class Compte {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nin", unique = true)
    private String nin;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    private RoleType role;

    @Column(name = "agence_id")
    private Long agenceId;
}
