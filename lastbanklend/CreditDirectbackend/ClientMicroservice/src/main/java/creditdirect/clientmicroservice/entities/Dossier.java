package creditdirect.clientmicroservice.entities;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "dossiers")
public class Dossier {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Other attributes specific to the dossier entity

    // Relationship with Client
    @ManyToOne
    @JoinColumn(name = "client_id", nullable = false)
    private Client client;

    // Getters and Setters
}