package creditdirect.clientmicrocervice.services;


import creditdirect.clientmicrocervice.config.FileStorageProperties;
import creditdirect.clientmicrocervice.entities.*;
import creditdirect.clientmicrocervice.repositories.*;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

import jakarta.persistence.EntityManager;



@Service
public class DossierServiceImpl implements DossierService {

    private final DossierRepository dossierRepository;
    private final ClientRepository clientRepository;
    private final FileStorageService fileStorageService;

    private final ParticulierRepository particulierRepository;
    private final TypeCreditRepository typeCreditRepository;
    private final TypeFinancementRepository typeFinancementRepository;
    private final AgenceRepository agenceRepository;
    private final CompteRepository compteRepository;

    private final String uploadDir; // Injecting the upload directory
    private final EntityManager entityManager;
    @Autowired
    public DossierServiceImpl(DossierRepository dossierRepository, ClientRepository clientRepository,
                              FileStorageService fileStorageService, TypeCreditRepository typeCreditRepository,
                              TypeFinancementRepository typeFinancementRepository,FileStorageProperties fileStorageProperties,
                              CompteRepository compteRepository,ParticulierRepository particulierRepository, EntityManager entityManager,
                              AgenceRepository agenceRepository) {
        this.dossierRepository = dossierRepository;
        this.clientRepository = clientRepository;
        this.fileStorageService = fileStorageService;
        this.typeCreditRepository = typeCreditRepository;
        this.compteRepository = compteRepository;
        this.typeFinancementRepository = typeFinancementRepository;
        this.agenceRepository = agenceRepository;
        this.particulierRepository =particulierRepository;
        this.entityManager = entityManager;


        this.uploadDir = fileStorageProperties.getUploadDir();
        initializeUploadDir();

    }


    @Override
    @Transactional
    public Dossier addDossier(Dossier dossier) {
        Long clientId = dossier.getClient().getId();
        System.out.println("Client ID: " + clientId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        dossier.setClient(client);

        if (client instanceof Particulier) {
            Particulier particulier = (Particulier) client;

            // Assuming particulierId is retrieved from somewhere, it's not defined in the given code
            Particulier foundParticulier = particulierRepository.findById(particulier.getId()).orElse(null);

            if (foundParticulier != null) {
                Commune commune = foundParticulier.getCommune();

                if (commune != null) {
                    List<Agence> agences = findAgencesByCommuneId(commune.getId());

                    if (agences.size() == 1) {
                        System.out.println("Cette commune appartient à une seule agence");
                        Agence singleAgence = agences.get(0);
                        Long agenceId = singleAgence.getId();
                        System.out.println("agenceId"+agenceId);
                        if (agenceId != null) {
                            dossier.setAgenceId(agenceId);
                        }

                        return dossierRepository.save(dossier);
                    } else if (agences.size() > 1) {
                        System.out.println("Cette commune appartient à plusieurs agences");
                        Agence firstAgence = agences.get(0);
                        System.out.println("firstAgence"+firstAgence);
                        DirectionRegionale directionRegionale = firstAgence.getDirectionRegionale();

                        if (directionRegionale != null) {
                            Long directionRegionaleId = directionRegionale.getId();
                            System.out.println("directionRegionaleId"+directionRegionaleId);
                            dossier.setDirection_regionaleId(directionRegionaleId);
                        }

                        return dossierRepository.save(dossier);
                    }
                }
            }
        }

        return dossierRepository.save(dossier);
    }




/*
    @Override
    @Transactional
    public Dossier addDossier(Dossier dossier) {
        Long clientId = dossier.getClient().getId();
        System.out.println("Client ID: " + clientId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        dossier.setClient(client);

        if (client instanceof Particulier) {
            Particulier particulier = (Particulier) client;
            Long agenceId = getSingleAgenceIdByParticulierId(particulier.getId());

            if (agenceId != null) {
                dossier.setAgenceId(agenceId);
            } else {
                Long directionRegionaleId = findAgenceRegionaleIdByParticulierId(particulier.getId());
                System.out.println("id direction: " +directionRegionaleId  );
                dossier.setDirection_regionaleId(directionRegionaleId);
            }
        }

        return dossierRepository.save(dossier);
    }*/









   /* @Override
    public Dossier addDossier(Dossier dossier) {
        Long clientId = dossier.getClient().getId();
        System.out.println("id cient"+clientId);
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        dossier.setClient(client);


        if (client instanceof Particulier) {
            Long particulierId = ((Particulier) client).getId();
            Long agenceId = getSingleAgenceIdByParticulierId(particulierId);

            // Set the retrieved Agence ID to the Dossier if available
            if (agenceId != null) {
                Agence agence = agenceRepository.findById(agenceId)
                        .orElseThrow(() -> new RuntimeException("Agence not found"));
                dossier.setAgenceId(agenceId);
            }else {

                Long direction_regionaleId= findAgenceRegionaleIdByParticulierId( particulierId);
                dossier.setDirection_regionaleId(direction_regionaleId);

            }
        }

        return dossierRepository.save(dossier);
    }
*/
    @Override
    public Dossier updateFilesForDossier(Long dossierId, MultipartFile[] files) {
        Dossier dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new RuntimeException("Dossier not found with id: " + dossierId));

        List<AttachedFile> attachedFiles = fileStorageService.storeFilesForDossier(files, dossierId);
        dossier.setAttachedFiles(attachedFiles);

        return dossierRepository.save(dossier);
    }
    /*
   @Override
    public Long addDossier(Long clientId, Long typeCreditId, Long typeFinancementId, MultipartFile[] files, String simulationInfo) {
        Dossier dossier = new Dossier();

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        dossier.setClient(client);


        // Retrieve the single Agence ID associated with the Particulier's Commune
       Long agenceId = getSingleAgenceIdByParticulierId(clientId);

        // Set the retrieved Agence ID to the Dossier if available
        if (agenceId != null) {
            Agence agence = agenceRepository.findById(agenceId)
                    .orElseThrow(() -> new RuntimeException("Agence not found"));
            dossier.setAgenceId(agenceId);
        }



        Dossier savedDossier = dossierRepository.save(dossier);

        // Store uploaded files for the specific Dossier
        //List<AttachedFile> attachedFiles = fileStorageService.storeFilesForDossier(files, savedDossier.getId());
      ///  savedDossier.setAttachedFiles(attachedFiles);

        // Update the saved Dossier with attached files
        savedDossier = dossierRepository.save(savedDossier);

        return savedDossier.getId();
    }*/

//////////////
//////////////////////////////////////asign dossier to agence/////////////////////////////////
/*@Override
public Long getSingleAgenceIdByParticulierId(Long particulierId) {
    Particulier particulier = particulierRepository.findById(particulierId).orElse(null);
    System.out.println("id client"+particulierId);
    if (particulier != null) {
        Commune commune = particulier.getCommune();
        System.out.println("comune"+commune);
        if (commune != null) {
            Set<Agence> agences = commune.getAgences();

            System.out.println("agence"+agences);

            if (agences.size() == 1) {
                Agence singleAgence = agences.iterator().next();
                System.out.println("is single   idagence"+singleAgence.getId());
                return singleAgence.getId();
            }
        }
    }

    return null;
}*/



    @Override
    public Long getSingleAgenceIdByParticulierId(Long particulierId) {
        Particulier particulier = particulierRepository.findById(particulierId).orElse(null);

        if (particulier != null) {
            Commune commune = particulier.getCommune();

            if (commune != null) {
                List<Agence> agences = findAgencesByCommuneId(commune.getId());

                if (agences.size() == 1) {
                    System.out.println("cette commune aprtient a une seul agence");
                    Agence singleAgence = agences.get(0);
                    return singleAgence.getId();
                }
            }
        }

        return null;
}

///////////////////queries
    @Override
    public List<Agence> findAgencesByCommuneId(Long communeId) {
        String jpql = "SELECT a FROM Agence a JOIN a.communes c WHERE c.id = :communeId";
        TypedQuery<Agence> query = entityManager.createQuery(jpql, Agence.class);
        query.setParameter("communeId", communeId);
        return query.getResultList();
    }




    @Override

    public Long findAgenceRegionaleIdByParticulierId(Long idParticulier) {

        System.out.println("idParticulier ID: " + idParticulier);

        String jpql = "SELECT a.id FROM Particulier p " +
                "JOIN p.commune c " +
                "JOIN c.agences a " +

                "WHERE p.id = :idParticulier "
               ; // Ordonne par l'ID de l'Agence pour obtenir la première


        Query query = entityManager.createQuery(jpql);
        query.setParameter("idParticulier", idParticulier);

        try {
            return (Long) query.getSingleResult();

        } catch (Exception e) {
            return null; // or handle the exception as needed
        }
    }





    ///////////////////////////////////////////////////

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

///////////////////////delete file by file name and id dossier/////////////////
    @Override
    public void deleteFileByDossierIdAndFileName(Long dossierId, String fileName) {
        Optional<Dossier> dossierOptional = dossierRepository.findById(dossierId);

        dossierOptional.ifPresent(dossier -> {
            List<AttachedFile> attachedFiles = dossier.getAttachedFiles();
            attachedFiles.removeIf(attachedFile -> attachedFile.getFileName().equals(fileName));
            dossierRepository.save(dossier);
        });
    }

}
