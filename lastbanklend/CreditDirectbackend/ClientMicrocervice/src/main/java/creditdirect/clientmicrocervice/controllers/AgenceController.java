package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Agence;
import creditdirect.clientmicrocervice.services.AgenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/agences")
public class AgenceController {

    @Autowired
    private AgenceService agenceService;

    @PostMapping
    public ResponseEntity<Agence> createAgence(@RequestBody Agence agence) {
        Optional<Agence> createdAgence = agenceService.createAgence(agence);
        return createdAgence.map(value -> new ResponseEntity<>(value, HttpStatus.CREATED))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.BAD_REQUEST));
    }
}
