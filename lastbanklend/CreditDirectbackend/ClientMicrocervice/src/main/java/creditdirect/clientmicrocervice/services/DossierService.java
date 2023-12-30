package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Dossier;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DossierService {
    List<Dossier> getAllDossiers();
    Dossier getDossierById(Long id);
    List<Dossier> getDossiersByClientId(Long clientId);
   /* Dossier addDossierForClient(Long clientId, Dossier dossier);
    Dossier addDossierForClientWithFiles(Long clientId, Dossier dossier, List<MultipartFile> files);
*/
   Long addDossier(Long clientId, Long typeCreditId, Long typeFinancementId, MultipartFile[] files, String simulationInfo);


    Dossier assignDossierToCourtier(Long dossierId, Long courtierId);
}
