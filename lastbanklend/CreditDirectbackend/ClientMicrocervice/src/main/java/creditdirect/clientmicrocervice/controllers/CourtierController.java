package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Courtier;
import creditdirect.clientmicrocervice.services.CourtierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/courtiers")
public class CourtierController {

    private final CourtierService courtierService;

    @Autowired
    public CourtierController(CourtierService courtierService) {
        this.courtierService = courtierService;
    }

    // Existing endpoints...

    @PostMapping("/add")
    public ResponseEntity<Courtier> addCourtier(@RequestBody Courtier courtier) throws URISyntaxException {
        Courtier savedCourtier = courtierService.addCourtier(courtier);
        return ResponseEntity.created(new URI("/courtiers/" + savedCourtier.getId())).body(savedCourtier);
    }
}
