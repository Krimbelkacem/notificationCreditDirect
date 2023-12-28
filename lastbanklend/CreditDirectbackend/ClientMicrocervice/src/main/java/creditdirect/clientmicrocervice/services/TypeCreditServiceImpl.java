package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.TypeCredit;
import creditdirect.clientmicrocervice.repositories.TypeCreditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TypeCreditServiceImpl implements TypeCreditService {

    private final TypeCreditRepository typeCreditRepository;

    @Autowired
    public TypeCreditServiceImpl(TypeCreditRepository typeCreditRepository) {
        this.typeCreditRepository = typeCreditRepository;
    }

    @Override
    public List<TypeCredit> getAllTypeCredits() {
        return typeCreditRepository.findAll();
    }

    @Override
    public TypeCredit getTypeCreditById(Long id) {
        return typeCreditRepository.findById(id).orElse(null);
    }

    @Override
    public TypeCredit createTypeCredit(TypeCredit typeCredit) {
        return typeCreditRepository.save(typeCredit);
    }

    @Override
    public TypeCredit updateTypeCredit(Long id, TypeCredit typeCredit) {
        if (typeCreditRepository.existsById(id)) {
            typeCredit.setId(id);
            return typeCreditRepository.save(typeCredit);
        }
        return null; // Or handle as per requirement
    }

    @Override
    public void deleteTypeCredit(Long id) {
        typeCreditRepository.deleteById(id);
    }
}
