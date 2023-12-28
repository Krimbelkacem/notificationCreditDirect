package creditdirect.clientmicrocervice.entities;

import lombok.Data;
import jakarta.persistence.*;

@Data
@Entity
@Table(name = "client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mot_pass")
    private String password;

    @Column(name = "email")
    private String email;

    @Enumerated(EnumType.STRING) // Using EnumType.STRING for role, assuming it's an enum
    @Column(name = "role")
    private RoleType role; // Define an enum RoleType for 'particulier' and 'entreprise'

    // Getters and setters

}