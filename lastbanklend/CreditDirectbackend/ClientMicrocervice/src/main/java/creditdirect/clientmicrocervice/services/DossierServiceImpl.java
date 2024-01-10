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
import java.util.ArrayList;
import java.util.Collections;
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

    private final AgenceCommuneService agenceService;

    @Autowired
    public DossierServiceImpl(DossierRepository dossierRepository, ClientRepository clientRepository,
                              FileStorageService fileStorageService, TypeCreditRepository typeCreditRepository,
                              TypeFinancementRepository typeFinancementRepository,FileStorageProperties fileStorageProperties,
                              CompteRepository compteRepository,ParticulierRepository particulierRepository, EntityManager entityManager,
                              AgenceRepository agenceRepository, AgenceCommuneService agenceService) {
        this.dossierRepository = dossierRepository;
        this.clientRepository = clientRepository;
        this.fileStorageService = fileStorageService;
        this.typeCreditRepository = typeCreditRepository;
        this.compteRepository = compteRepository;
        this.typeFinancementRepository = typeFinancementRepository;
        this.agenceRepository = agenceRepository;
        this.particulierRepository =particulierRepository;
        this.entityManager = entityManager;
        this.agenceService = agenceService;


        this.uploadDir = fileStorageProperties.getUploadDir();
        initializeUploadDir();

    }


    @Override
    @Transactional
    public Dossier addDossier(Dossier dossier) {
        Long clientId = dossier.getClient().getId();

        System.out.println("ajoute du dossier") ;
        System.out.println("Client ID: " + clientId);

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        dossier.setClient(client);

     /*   if (client instanceof Particulier) {
            Particulier particulier = (Particulier) client;
            System.out.println("particulier ID: " + particulier);
            // Assuming particulierId is retrieved from somewhere, it's not defined in the given code
            Particulier foundParticulier = particulierRepository.findById(particulier.getId()).orElse(null);

            if (foundParticulier != null) {
                Commune commune = foundParticulier.getCommune();

                if (commune != null) {
                    List<Agence> agences = findAgencesByCommuneId(commune.getId());
                    System.out.println("commune ID: " + commune.getId());
                    System.out.println("agences ID: " + agences);

                    if (agences.size() == 1) {
                        System.out.println("Cette commune appartient à une seule agence");
                        Agence singleAgence = agences.get(0);
                        Long agenceId = singleAgence.getId();
                        System.out.println("agenceId"+agenceId);
                        if (agenceId != null) {
                            dossier.setAssignedagence(singleAgence);
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
                            dossier.setAssigneddirectionregionnale(directionRegionale);
                        }

                        return dossierRepository.save(dossier);
                    }
                }
            }
        }*/

        return dossierRepository.save(dossier);
    }
    @Override
    public Dossier affectiondossieragence(Long dossierId) {
        Optional<Dossier> optionalDossier = dossierRepository.findById(dossierId);
        Dossier dossier = optionalDossier.orElseThrow(() -> new RuntimeException("Dossier not found"));

        Long clientId = dossier.getClient().getId();

        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new RuntimeException("Client not found"));
        dossier.setClient(client);

       if (client instanceof Particulier) {
            Particulier particulier = (Particulier) client;
            System.out.println("particulier ID: " + particulier);
            // Assuming particulierId is retrieved from somewhere, it's not defined in the given code
            Particulier foundParticulier = particulierRepository.findById(particulier.getId()).orElse(null);

            if (foundParticulier != null) {
                Commune commune = foundParticulier.getCommune();

                if (commune != null) {
                    List<Agence> agences = findAgencesByCommuneId(commune.getId());
                    System.out.println("commune ID: " + commune.getId());
                    System.out.println("agences ID: " + agences);

                    if (agences.size() == 1) {
                        System.out.println("Cette commune appartient à une seule agence");
                        Agence singleAgence = agences.get(0);
                        Long agenceId = singleAgence.getId();
                        System.out.println("agenceId"+agenceId);
                        if (agenceId != null) {
                            dossier.setAssignedagence(singleAgence);
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
                            dossier.setAssigneddirectionregionnale(directionRegionale);
                        }

                        return dossierRepository.save(dossier);
                    }
                }
            }
        }

        return dossierRepository.save(dossier);
    }



   /* @Override
    public Dossier updateFilesForDossier(Long dossierId, MultipartFile[] files) {
        Dossier dossier = dossierRepository.findById(dossierId)
                .orElseThrow(() -> new RuntimeException("Dossier not found with id: " + dossierId));

        List<AttachedFile> attachedFiles = fileStorageService.storeFilesForDossier(files, dossierId);
        dossier.setAttachedFiles(attachedFiles);

        return dossierRepository.save(dossier);
    }
*/
   // Service layer
   @Override
   public Dossier updateFilesForDossier(Long dossierId, MultipartFile[] files) {
       Dossier dossier = dossierRepository.findById(dossierId)
               .orElseThrow(() -> new RuntimeException("Dossier not found with id: " + dossierId));

       List<AttachedFile> attachedFiles = dossier.getAttachedFiles(); // Get existing attached files

       // Store the new files and retrieve AttachedFile objects
       List<AttachedFile> newAttachedFiles = fileStorageService.storeFilesForDossier(files, dossierId);

       // Add the new AttachedFile objects to the existing list
       if (attachedFiles == null) {
           attachedFiles = new ArrayList<>();
       }
       attachedFiles.addAll(newAttachedFiles);

       // Update the attached files list in the Dossier entity
       dossier.setAttachedFiles(attachedFiles);

       return dossierRepository.save(dossier);
   }

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
        return dossierRepository.findAllByAssignedagenceIdAndStatus(courtierId, DossierStatus.NON_TRAITEE);
    }


    @Override
    public List<Dossier> getDossiersencoursForCourtier(Long courtierId) {
        return dossierRepository.findAllByAssignedCourtier_IdAndStatus(courtierId, DossierStatus.TRAITEMENT_ENCOURS);
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
    @Override
    public void updateDossiersStatusToTraitee(List<Long> dossierIds) {
        List<Dossier> dossiersToUpdate = dossierRepository.findAllById(dossierIds);
        dossiersToUpdate.forEach(dossier -> dossier.setStatus(DossierStatus.TRAITEE));
        dossierRepository.saveAll(dossiersToUpdate);
    }


    ///////////////////////delete file by file name and id dossier/////////////////
public boolean deleteFileByDossierIdAndFileName(Long dossierId, String fileName) {
    Optional<Dossier> optionalDossier = dossierRepository.findById(dossierId);

    if (optionalDossier.isPresent()) {
        Dossier dossier = optionalDossier.get();
        List<AttachedFile> attachedFiles = dossier.getAttachedFiles();
        AttachedFile fileToDelete = null;

        for (AttachedFile file : attachedFiles) {
            if (file.getFileName().equals(fileName)) {
                fileToDelete = file;
                break;
            }
        }

        if (fileToDelete != null) {
            attachedFiles.remove(fileToDelete);
            dossierRepository.save(dossier);
            // Optionally, perform other operations like deleting the file from storage
            return true; // File deleted successfully
        } else {
            // Handle case: File not found in the dossier
            return false; // File not found in dossier's attached files
        }
    } else {
        // Handle case: Dossier not found
        return false; // Dossier not found with the given ID
    }
}


    /////////////////////////////////





    @Override
    public List<Dossier> getAllDossiersByAgence(Long assignedAgenceId) {
        Agence assignedAgence = agenceService.getAgenceById(assignedAgenceId); // Fetch Agence by ID

        if (assignedAgence == null) {
            // Handle the case where the Agence with the given ID is not found
            return Collections.emptyList(); // Or throw an exception as needed
        }

        return dossierRepository.findAllByAssignedagence(assignedAgence);
    }

}
