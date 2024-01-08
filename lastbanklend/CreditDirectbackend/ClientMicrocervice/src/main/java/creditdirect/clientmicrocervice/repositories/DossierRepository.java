package creditdirect.clientmicrocervice.repositories;


import creditdirect.clientmicrocervice.entities.Dossier;
import creditdirect.clientmicrocervice.entities.DossierStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {


    List<Dossier> findByClientId(Long clientId);


    //List<Dossier> findAllByAgenceIdAndStatus(Long courtierAgenceId, DossierStatus dossierStatus);

    List<Dossier> findAllByAssignedCourtier_IdAndStatus(Long courtierId, DossierStatus dossierStatus);

    List<Dossier> findAllByAssignedagenceIdAndStatus(Long courtierId, DossierStatus dossierStatus);
}
