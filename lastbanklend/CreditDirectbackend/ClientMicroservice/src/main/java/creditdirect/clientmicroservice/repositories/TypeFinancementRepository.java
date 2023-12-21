package creditdirect.clientmicroservice.repositories;


import creditdirect.clientmicroservice.entities.TypeFinancement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeFinancementRepository extends JpaRepository<TypeFinancement, Long> {
    // Add custom query methods if needed
}
