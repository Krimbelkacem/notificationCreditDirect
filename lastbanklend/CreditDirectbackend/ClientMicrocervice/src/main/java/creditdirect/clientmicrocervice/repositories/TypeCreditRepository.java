package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.TypeCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TypeCreditRepository extends JpaRepository<TypeCredit, Long> {
    // Additional custom queries can be added here if needed
}
