package creditdirect.clientmicrocervice.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "Commune")
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "nom")
    private String nom;


    @Column(name = "code_postal", unique = true)
    private String codePostal;


    @ManyToMany
    private Set<Agence> agences = new HashSet<>();
}
