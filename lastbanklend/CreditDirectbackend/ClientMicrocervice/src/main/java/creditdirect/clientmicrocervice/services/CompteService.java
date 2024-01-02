package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Compte;

public interface CompteService {
    Compte signUp(Compte compte);


    String signInByNin(String nin, String password);
}
