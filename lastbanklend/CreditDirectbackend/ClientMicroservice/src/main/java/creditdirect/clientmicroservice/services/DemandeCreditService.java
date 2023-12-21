package creditdirect.clientmicroservice.services;

import creditdirect.clientmicroservice.entities.DemandeCredit;
import java.util.List;

public interface DemandeCreditService {
    DemandeCredit createDemandeCredit(DemandeCredit demandeCredit);
    List<DemandeCredit> getAllDemandesCredit();
    DemandeCredit getDemandeCreditById(Long id);
    // Other methods as required
}
