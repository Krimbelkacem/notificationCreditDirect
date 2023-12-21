package creditdirect.clientmicroservice.controllers;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import creditdirect.clientmicroservice.entities.DemandeDeCredit;
import creditdirect.clientmicroservice.services.DemandDeCreditService;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/demandes-de-credit")
public class DemandDeCreditController {

    private final DemandDeCreditService demandDeCreditService;

    @Autowired
    public DemandDeCreditController(DemandDeCreditService demandDeCreditService) {
        this.demandDeCreditService = demandDeCreditService;
    }

    @GetMapping
    public ResponseEntity<List<DemandeDeCredit>> getAllDemandesDeCredit() {
        List<DemandeDeCredit> demandesDeCredit = demandDeCreditService.getAllDemandesDeCredit();
        return new ResponseEntity<>(demandesDeCredit, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DemandeDeCredit> getDemandeDeCreditById(@PathVariable("id") Long id) {
        Optional<DemandeDeCredit> demandeDeCredit = demandDeCreditService.getDemandeDeCreditById(id);
        return demandeDeCredit.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping
    public ResponseEntity<DemandeDeCredit> createDemandeDeCredit(@RequestBody DemandeDeCredit demandeDeCredit) {
        DemandeDeCredit savedDemandeDeCredit = demandDeCreditService.saveDemandeDeCredit(demandeDeCredit);
        return new ResponseEntity<>(savedDemandeDeCredit, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDemandeDeCredit(@PathVariable("id") Long id) {
        demandDeCreditService.deleteDemandeDeCredit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    // Other endpoints and methods as needed
}
