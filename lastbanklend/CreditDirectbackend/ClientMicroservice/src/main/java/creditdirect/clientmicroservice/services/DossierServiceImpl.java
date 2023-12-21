package creditdirect.clientmicroservice.services;

import creditdirect.clientmicroservice.entities.Client;
import creditdirect.clientmicroservice.entities.Dossier;
import creditdirect.clientmicroservice.repositories.DossierRepository;
import creditdirect.clientmicroservice.repositories.ClientRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class DossierServiceImpl implements DossierService {

    private final DossierRepository dossierRepository;
    private final ClientRepository clientRepository; // Assuming you have a ClientRepository

    @Autowired
    public DossierServiceImpl(DossierRepository dossierRepository, ClientRepository clientRepository) {
        this.dossierRepository = dossierRepository;
        this.clientRepository = clientRepository;
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

    // Implement other methods as needed
}
