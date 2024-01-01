package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.repositories.AgenceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AgenceServiceImpl implements AgenceService {

    @Autowired
    private AgenceRepository agenceRepository;

    @Override
    public Optional<Agence> createAgence(Agence agence) {
        // Add any necessary business logic or validations before saving the Agence
        return Optional.of(agenceRepository.save(agence));
    }
}
