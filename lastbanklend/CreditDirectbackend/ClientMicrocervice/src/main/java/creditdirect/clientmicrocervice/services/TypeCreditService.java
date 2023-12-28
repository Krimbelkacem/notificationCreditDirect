package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.TypeCredit;

import java.util.List;

public interface TypeCreditService {
    List<TypeCredit> getAllTypeCredits();
    TypeCredit getTypeCreditById(Long id);
    TypeCredit createTypeCredit(TypeCredit typeCredit);
    TypeCredit updateTypeCredit(Long id, TypeCredit typeCredit);
    void deleteTypeCredit(Long id);
}
