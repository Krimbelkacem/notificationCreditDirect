package creditdirect.clientmicroservice.controllers;

import creditdirect.clientmicroservice.entities.DemandeCredit;
import creditdirect.clientmicroservice.services.DemandeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/demandescredit")
public class DemandeCreditController {

    private final DemandeCreditService demandeCreditService;

    @Autowired
    public DemandeCreditController(DemandeCreditService demandeCreditService) {
        this.demandeCreditService = demandeCreditService;
    }

    @PostMapping("/create")
    public DemandeCredit createDemandeCredit(@RequestBody DemandeCredit demandeCredit) {
        return demandeCreditService.createDemandeCredit(demandeCredit);
    }

    @GetMapping("/all")
    public List<DemandeCredit> getAllDemandesCredit() {
        return demandeCreditService.getAllDemandesCredit();
    }

    @GetMapping("/{id}")
    public DemandeCredit getDemandeCreditById(@PathVariable Long id) {
        return demandeCreditService.getDemandeCreditById(id);
    }

    // Other endpoints as needed
}
