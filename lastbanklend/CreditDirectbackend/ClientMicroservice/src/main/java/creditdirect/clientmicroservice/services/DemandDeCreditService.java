package creditdirect.clientmicroservice.services;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import creditdirect.clientmicroservice.entities.DemandeDeCredit;
import creditdirect.clientmicroservice.repositories.DemandDeCreditRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DemandDeCreditService {

    private final DemandDeCreditRepository demandDeCreditRepository;

    @Autowired
    public DemandDeCreditService(DemandDeCreditRepository demandDeCreditRepository) {
        this.demandDeCreditRepository = demandDeCreditRepository;
    }

    public List<DemandeDeCredit> getAllDemandesDeCredit() {
        return demandDeCreditRepository.findAll();
    }

    public Optional<DemandeDeCredit> getDemandeDeCreditById(Long id) {
        return demandDeCreditRepository.findById(id);
    }

    public DemandeDeCredit saveDemandeDeCredit(DemandeDeCredit demandeDeCredit) {
        return demandDeCreditRepository.save(demandeDeCredit);
    }

    public void deleteDemandeDeCredit(Long id) {
        demandDeCreditRepository.deleteById(id);
    }

    // Other methods as needed
}
