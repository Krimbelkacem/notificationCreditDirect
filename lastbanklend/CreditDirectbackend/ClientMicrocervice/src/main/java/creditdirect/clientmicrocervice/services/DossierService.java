package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.entities.Dossier;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface DossierService {
    Dossier updateFilesForDossier(Long dossierId, MultipartFile[] files);

    //////////////
    ///////////////////////////////////////////////////////////////////////
    Long getSingleAgenceIdByParticulierId(Long particulierId);

    List<Agence> findAgencesByCommuneId(Long communeId);

    List<Dossier> getAllDossiers();
    Dossier getDossierById(Long id);
    List<Dossier> getDossiersByClientId(Long clientId);

  // Long addDossier(Long clientId, Long typeCreditId, Long typeFinancementId, MultipartFile[] files, String simulationInfo);


    Dossier assignDossierToCourtier(Long dossierId, Long courtierId);
    List<Dossier> getDossiersForCourtier(Long courtierAgenceId);

    List<Dossier> getTraiteeDossiersByCourtier(Long courtierId);

    void updateDossierStatusToTraitee(Long dossierId);

    Dossier addDossier(Dossier dossier);
}
