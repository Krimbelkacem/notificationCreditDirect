package creditdirect.clientmicrocervice.entities;
import lombok.Data;
import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
@Entity
@Table(name = "DirectionRegionale")
public class DirectionRegionale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    // Other relevant fields

    @OneToMany(mappedBy = "directionRegionale")
    private Set<Agence> agences = new HashSet<>();

    // Getters and setters
}
