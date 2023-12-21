package creditdirect.clientmicroservice.services;

import creditdirect.clientmicroservice.entities.Dossier;
import java.util.List;

public interface DossierService {
    List<Dossier> getAllDossiers();
    Dossier getDossierById(Long id);
    List<Dossier> getDossiersByClientId(Long clientId);
    Dossier addDossierForClient(Long clientId, Dossier dossier);
}
