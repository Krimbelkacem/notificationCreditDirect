package creditdirect.clientmicrocervice.entities;

import jakarta.persistence.*;

@Entity
public class Particulier {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Define your fields here based on your entity structure
    private Long idClient;
    private String codePostal;
    // ... other fields ...



    // Getters and setters
}