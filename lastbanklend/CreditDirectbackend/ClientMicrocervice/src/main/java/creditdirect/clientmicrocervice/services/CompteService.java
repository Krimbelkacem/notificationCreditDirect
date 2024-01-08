package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Compte;

import java.util.List;

public interface CompteService {
    Compte signUp(Compte compte);


    String signInByNin(String nin, String password);

    Compte findByNin(String nin);

    List<Compte> getAllComptes();
}
