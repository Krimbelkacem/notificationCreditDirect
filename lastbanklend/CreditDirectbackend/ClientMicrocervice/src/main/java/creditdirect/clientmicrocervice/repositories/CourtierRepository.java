package creditdirect.clientmicrocervice.repositories;

import creditdirect.clientmicrocervice.entities.Courtier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CourtierRepository extends JpaRepository<Courtier, Long> {
    // Additional custom queries can be added here if needed
}
