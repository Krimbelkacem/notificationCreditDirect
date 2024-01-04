package creditdirect.clientmicrocervice.entities;

import lombok.Data;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "Agence")
public class Agence {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;

    @Column(name = "adresse")
    private String adresse;

    @ManyToMany
    @JoinTable(
            name = "Agence_Commune",
            joinColumns = @JoinColumn(name = "agence_id"),
            inverseJoinColumns = @JoinColumn(name = "commune_id")
    )
    private Set<Commune> communes = new HashSet<>();

}

