package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Courtier;

import java.util.List;

public interface CourtierService {
    List<Courtier> getAllCourtiers();
    Courtier getCourtierById(Long id);
    // Other service methods if needed
    Courtier addCourtier(Courtier courtier);
}
