package creditdirect.clientmicroservice.repositories;

import creditdirect.clientmicroservice.entities.DemandeCredit;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DemandeCreditRepository extends JpaRepository<DemandeCredit, Long> {
    // You can add specific query methods here if needed
}
