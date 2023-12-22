package creditdirect.clientmicroservice.services;

import creditdirect.clientmicroservice.config.FileStorageProperties;
import creditdirect.clientmicroservice.entities.Client;
import creditdirect.clientmicroservice.entities.Dossier;
import creditdirect.clientmicroservice.repositories.DossierRepository;
import creditdirect.clientmicroservice.repositories.ClientRepository;
import creditdirect.clientmicroservice.entities.AttachedFile;
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
    private final ClientRepository clientRepository; // Assuming you have a ClientRepository
    private final String uploadDir; // Injecting the upload directory
    private final FileStorageService fileStorageService;

    @Autowired
    public DossierServiceImpl(DossierRepository dossierRepository, ClientRepository clientRepository, FileStorageProperties fileStorageProperties, FileStorageService fileStorageService) {
        this.dossierRepository = dossierRepository;
        this.clientRepository = clientRepository;
        this.uploadDir = fileStorageProperties.getUploadDir();
        initializeUploadDir();
        this.fileStorageService = fileStorageService;
    }

    @Override
    public Dossier addDossierForClientWithFiles(Long clientId, Dossier dossier, List<MultipartFile> files) {
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            Dossier savedDossier = dossierRepository.save(dossier);
            if (savedDossier != null) {
                savedDossier.setClient(clientOptional.get());
                saveFilesForDossier(savedDossier, files);
                return dossierRepository.save(savedDossier);
            }
        }
        return null; // Or handle the case where the client or dossier saving fails
    }

    private void saveFilesForDossier(Dossier dossier, List<MultipartFile> files) {
        List<AttachedFile> fileEntities = new ArrayList<>();
        for (MultipartFile file : files) {
            String fileName = StringUtils.cleanPath(file.getOriginalFilename());
            Path filePath = Paths.get(uploadDir + File.separator + fileName);
            try {
                Files.copy(file.getInputStream(), filePath);
                AttachedFile fileEntity = new AttachedFile();
                fileEntity.setFileName(fileName);
                fileEntity.setFilePath(filePath.toString());
                fileEntities.add(fileEntity);
            } catch (IOException e) {
                e.printStackTrace(); // Handle the exception properly
            }
        }
        dossier.getAttachedFiles().addAll(fileEntities);
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
    public Dossier addDossierForClient(Long clientId, Dossier dossier) {
        // You may want to check if the client exists before associating the dossier
        Optional<Client> clientOptional = clientRepository.findById(clientId);
        if (clientOptional.isPresent()) {
            dossier.setClient(clientOptional.get());
            return dossierRepository.save(dossier);
        }
        return null; // Or handle the case where the client doesn't exist
    }

    public Long addDossier(Long clientId, Long typeCreditId, Long typeFinancementId, MultipartFile[] files) {
        Dossier dossier = new Dossier();
        // Set dossier details like clientId, typeCreditId, typeFinancementId

        // Process and save attached files

        List<AttachedFile> attachedFiles = fileStorageService.storeFiles(files);
        dossier.setAttachedFiles(attachedFiles);

        Dossier savedDossier = dossierRepository.save(dossier);
        return savedDossier.getId();
    }
    // Implement other methods as needed
}
