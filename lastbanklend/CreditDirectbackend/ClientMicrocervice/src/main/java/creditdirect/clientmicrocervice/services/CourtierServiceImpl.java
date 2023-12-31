package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Courtier;
import creditdirect.clientmicrocervice.repositories.CourtierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourtierServiceImpl implements CourtierService {

    private final CourtierRepository courtierRepository;

    @Autowired
    public CourtierServiceImpl(CourtierRepository courtierRepository) {
        this.courtierRepository = courtierRepository;
    }

    @Override
    public List<Courtier> getAllCourtiers() {
        return courtierRepository.findAll();
    }

    @Override
    public Courtier getCourtierById(Long id) {
        return courtierRepository.findById(id).orElse(null);
    }
    @Override
    public Courtier addCourtier(Courtier courtier) {
        return courtierRepository.save(courtier);
    }
}
