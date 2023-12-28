package creditdirect.clientmicrocervice.repositories;


import creditdirect.clientmicrocervice.entities.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {


    List<Dossier> findByClientId(Long clientId);
}
