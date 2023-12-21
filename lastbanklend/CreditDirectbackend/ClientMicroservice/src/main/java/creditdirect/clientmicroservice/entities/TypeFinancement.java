package creditdirect.clientmicroservice.entities;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TypeFinancement")
public class TypeFinancement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "TypeFinancementID")
    private Long id;


    @Column(name = "type_name", nullable = false, unique = true)
    private String typeName;


}
