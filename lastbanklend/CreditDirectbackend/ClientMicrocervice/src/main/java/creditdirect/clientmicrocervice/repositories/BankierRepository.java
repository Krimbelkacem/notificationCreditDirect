package creditdirect.clientmicrocervice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import creditdirect.clientmicrocervice.entities.Bankier;

@Repository
public interface BankierRepository extends JpaRepository<Bankier, Long> {

}
