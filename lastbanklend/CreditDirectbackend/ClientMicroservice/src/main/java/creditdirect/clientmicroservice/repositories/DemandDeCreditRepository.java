package creditdirect.clientmicroservice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import creditdirect.clientmicroservice.entities.DemandeDeCredit;

@Repository
public interface DemandDeCreditRepository extends JpaRepository<DemandeDeCredit, Long> {
    // You can add custom query methods here if needed
}
