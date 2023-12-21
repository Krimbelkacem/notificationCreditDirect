package creditdirect.clientmicroservice.repositories;

import creditdirect.clientmicroservice.entities.TypeCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCreditRepository extends JpaRepository<TypeCredit, Long> {
    // Add custom query methods if needed
}
