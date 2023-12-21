package creditdirect.clientmicroservice.services;

import creditdirect.clientmicroservice.entities.DemandeCredit;
import creditdirect.clientmicroservice.repositories.DemandeCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DemandeCreditServiceImpl implements DemandeCreditService {

    private final DemandeCreditRepository demandeCreditRepository;

    @Autowired
    public DemandeCreditServiceImpl(DemandeCreditRepository demandeCreditRepository) {
        this.demandeCreditRepository = demandeCreditRepository;
    }

    @Override
    public DemandeCredit createDemandeCredit(DemandeCredit demandeCredit) {
        return demandeCreditRepository.save(demandeCredit);
    }

    @Override
    public List<DemandeCredit> getAllDemandesCredit() {
        return demandeCreditRepository.findAll();
    }

    @Override
    public DemandeCredit getDemandeCreditById(Long id) {
        return demandeCreditRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("DemandeCredit not found with id: " + id));
    }

    // Implement other methods as required
}
