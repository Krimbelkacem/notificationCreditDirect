package creditdirect.clientmicrocervice.entities;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "client_type", discriminatorType = DiscriminatorType.STRING)
@Table(name = "bankier")
public class Bankier {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "nom_utilisateur")
    private String username;

    @Column(name = "mot_pass")
    private String password;

}