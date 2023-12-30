package creditdirect.clientmicrocervice.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import creditdirect.clientmicrocervice.entities.Bankier;
import creditdirect.clientmicrocervice.repositories.BankierRepository;
import java.util.List;
import java.util.Optional;

@Service
public class BankierServiceImpl implements BankierService {

    private final BankierRepository bankierRepository;

    @Autowired
    public BankierServiceImpl(BankierRepository bankierRepository) {
        this.bankierRepository = bankierRepository;
    }

    @Override
    public List<Bankier> getAllBankiers() {
        return bankierRepository.findAll();
    }

    @Override
    public Bankier getBankierById(Long id) {
        Optional<Bankier> optionalBankier = bankierRepository.findById(id);
        return optionalBankier.orElse(null); // Handle if Bankier is not found
    }

    @Override
    public Bankier saveBankier(Bankier bankier) {
        return bankierRepository.save(bankier);
    }

    @Override
    public void deleteBankier(Long id) {
        bankierRepository.deleteById(id);
    }
}
