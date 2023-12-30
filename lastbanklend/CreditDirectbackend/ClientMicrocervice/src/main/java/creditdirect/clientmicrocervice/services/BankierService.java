package creditdirect.clientmicrocervice.services;

import java.util.List;
import creditdirect.clientmicrocervice.entities.Bankier;

public interface BankierService {
    List<Bankier> getAllBankiers();
    Bankier getBankierById(Long id);
    Bankier saveBankier(Bankier bankier);
    void deleteBankier(Long id);
}
