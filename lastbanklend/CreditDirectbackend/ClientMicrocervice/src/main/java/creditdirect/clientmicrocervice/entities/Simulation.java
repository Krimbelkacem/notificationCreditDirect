package creditdirect.clientmicrocervice.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;


import java.util.List;
@Data
@Entity
@Table(name = "Simulation")
public class Simulation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "simulation_id") // Specify the column name in SQL
    private Long id;

    @Column(name = "montant_habitation") // Specify the column name in SQL
    private double montantHabitation;

    @Column(name = "revenue_emprunteur") // Specify the column name in SQL
    private double revenueEmprunteur;

    @Column(name = "age_emprunteur") // Specify the column name in SQL
    private int ageEmprunteur;



    @Column(name = "duree_financement") // Specify the column name in SQL
    private int dureeFinancement;

    @Column(name = "a_co_emprunteur") // Specify the column name in SQL
    private boolean aCoEmprunteur;

    @Column(name = "revenue_co_emprunteur") // Specify the column name in SQL
    private double revenueCoEmprunteur;

    @Column(name = "age_co_emprunteur") // Specify the column name in SQL
    private int ageCoEmprunteur;

    @Column(name = "a_revenue_immobilier") // Specify the column name in SQL
    private boolean aRevenueImmobilier;

    @Column(name = "montant_revenue_immobilier") // Specify the column name in SQL
    private double montantRevenueImmobilier;

    @Column(name = "a_autre_revenue") // Specify the column name in SQL
    private boolean aAutreRevenue;

    @Column(name = "montant_autre_revenue") // Specify the column name in SQL
    private double montantAutreRevenue;

    @Column(name = "a_autre_financement_en_cours") // Specify the column name in SQL
    private boolean aAutreFinancementEnCours;

    @Column(name = "montant_autre_financement_en_cours") // Specify the column name in SQL
    private double montantAutreFinancementEnCours;

    @OneToOne
    @JoinColumn(name = "dossier_id")
    @JsonIgnoreProperties("simulation")
    private Dossier dossier;
}
