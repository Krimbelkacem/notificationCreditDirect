package creditdirect.clientmicroservice.repositories;

import creditdirect.clientmicroservice.entities.Dossier;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DossierRepository extends JpaRepository<Dossier, Long> {


    List<Dossier> findByClientId(Long clientId);
}
