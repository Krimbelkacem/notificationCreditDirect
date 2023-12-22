package creditdirect.bankiermicroservice.repositories;

import creditdirect.bankiermicroservice.entities.Bankier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BankierRepository extends JpaRepository<Bankier, Long> {
    // Define custom query methods if needed
}
