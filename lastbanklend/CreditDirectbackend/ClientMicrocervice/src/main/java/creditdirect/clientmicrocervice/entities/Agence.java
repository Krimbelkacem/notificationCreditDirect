package creditdirect.clientmicrocervice.entities;

import lombok.Data;
import jakarta.persistence.*;
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

    // Autres attributs de l'agence, constructeurs, getters et setters
}

