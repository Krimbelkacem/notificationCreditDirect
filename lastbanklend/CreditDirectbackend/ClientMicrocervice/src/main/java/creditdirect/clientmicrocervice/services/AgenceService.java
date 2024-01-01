package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Agence;

import java.util.Optional;

public interface AgenceService {
    Optional<Agence> createAgence(Agence agence);
}
