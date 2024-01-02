package creditdirect.clientmicrocervice.services;


import creditdirect.clientmicrocervice.config.FileStorageProperties;
import creditdirect.clientmicrocervice.entities.*;
import creditdirect.clientmicrocervice.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class DossierServiceImpl implements DossierService {

    private final DossierRepository dossierRepository;
    private final ClientRepository clientRepository;
    private final FileStorageService fileStorageService;
    private final TypeCreditRepository typeCreditRepository;
    private final TypeFinancementRepository typeFinancementRepository;
    private final CompteRepository compteRepository;
    private final String uploadDir; // Injecting the upload directory
    @Autowired
    public DossierServiceImpl(DossierRepository dossierRepository, ClientRepository clientRepository,
                              FileStorageService fileStorageService, TypeCreditRepository typeCreditRepository,
                              TypeFinancementRepository typeFinancementRepository,FileStorageProperties fileStorageProperties,CompteRepository compteRepository) {
        this.dossierRepository = dossierRepository;
        this.clientRepository = clientRepository;
        this.fileStorageService = fileStorageService;
        this.typeCreditRepository = typeCreditRepository;
        this.compteRepository = compteRepository;
        this.typeFinancementRepository = typeFinancementRepository;
        this.uploadDir = fileStorageProperties.getUploadDir();
        initializeUploadDir();

    }
    @Override
    public Long addDossier(Long clientId, Long typeCreditId, Long typeFinancementId, MultipartFile[] files, String simulationInfo) {
        Dossier dossier = new Dossier();

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        dossier.setClient(client);

        TypeCredit typeCredit = typeCreditRepository.findById(typeCreditId)
                .orElseThrow(() -> new RuntimeException("TypeCredit not found"));
        dossier.setTypeCredit(typeCredit);

        TypeFinancement typeFinancement = typeFinancementRepository.findById(typeFinancementId)
                .orElseThrow(() -> new RuntimeException("TypeFinancement not found"));
        dossier.setTypeFinancement(typeFinancement);

        dossier.setSimulationInfo(simulationInfo);

        Dossier savedDossier = dossierRepository.save(dossier);

        // Store uploaded files for the specific Dossier
        List<AttachedFile> attachedFiles = fileStorageService.storeFilesForDossier(files, savedDossier.getId());
        savedDossier.setAttachedFiles(attachedFiles);

        // Update the saved Dossier with attached files
        savedDossier = dossierRepository.save(savedDossier);

        return savedDossier.getId();
    }




    private void initializeUploadDir() {
        Path path = Paths.get(uploadDir);
        if (!Files.exists(path)) {
            try {
                Files.createDirectories(path);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception properly
            }
        }
    }
    @Override
    public List<Dossier> getAllDossiers() {
        return dossierRepository.findAll();
    }


    @Override
    public Dossier getDossierById(Long id) {
        return dossierRepository.findById(id).orElse(null);
    }

    @Override
    public List<Dossier> getDossiersByClientId(Long clientId) {
        // Assuming you have a method in DossierRepository to find dossiers by client ID
        return dossierRepository.findByClientId(clientId);
    }
    @Override
    public Dossier assignDossierToCourtier(Long dossierId, Long courtierId) {
        Dossier dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new EntityNotFoundException("Dossier not found with id: " + dossierId));


        Compte courtier = compteRepository.findById(courtierId)
                .orElseThrow(() -> new EntityNotFoundException("Courtier not found with id: " + courtierId));


        dossier.setAssignedCourtier(courtier);
        dossier.setStatus(DossierStatus.TRAITEMENT_ENCOURS); // Assuming a new assignment resets status

        return dossierRepository.save(dossier);
    }


    // courtier avoir les dossiers non traiter
    @Override
    public List<Dossier> getDossiersForCourtier(Long courtierId) {
        return dossierRepository.findAllByAgenceIdAndStatus(courtierId, DossierStatus.NON_TRAITEE);
    }






    // dossiers traiter par le courtier
    @Override
    public List<Dossier> getTraiteeDossiersByCourtier(Long courtierId) {
        return dossierRepository.findAllByAssignedCourtier_IdAndStatus(courtierId, DossierStatus.TRAITEE);
    }



    @Override
    public void updateDossierStatusToTraitee(Long dossierId) {
        Optional<Dossier> dossierOptional = dossierRepository.findById(dossierId);
        dossierOptional.ifPresent(dossier -> {
            dossier.setStatus(DossierStatus.TRAITEE);
            dossierRepository.save(dossier);
        });
    }

}
