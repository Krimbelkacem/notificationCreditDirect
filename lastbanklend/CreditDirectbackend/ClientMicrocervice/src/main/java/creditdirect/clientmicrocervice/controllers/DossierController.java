package creditdirect.clientmicrocervice.controllers;



import creditdirect.clientmicrocervice.entities.Dossier;
import creditdirect.clientmicrocervice.kafka.KafkaProducer;
import creditdirect.clientmicrocervice.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/dossiers")
public class DossierController {
    private final KafkaProducer kafkaProducer;
    private final DossierService dossierService;

    @Autowired
    public DossierController(DossierService dossierService,KafkaProducer kafkaProducer) {
        this.dossierService = dossierService;
        this.kafkaProducer = kafkaProducer;
    }

    @GetMapping("/all")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Dossier>> getAllDossiers() {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public Dossier getDossierById(@PathVariable Long id) {
        return dossierService.getDossierById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Dossier> getDossiersByClientId(@PathVariable Long clientId) {
        return dossierService.getDossiersByClientId(clientId);
    }

    @PostMapping("/adddossier")
    public ResponseEntity<Dossier> addDossier(@RequestBody Dossier dossier) {
        Dossier addedDossier = dossierService.addDossier(dossier);
        return ResponseEntity.ok(addedDossier);
    }
   /*@PostMapping("/add")
    public Long addDossier(
            @RequestParam("client_id") Long clientId,
            @RequestParam("type_credit_id") Long typeCreditId,
            @RequestParam("type_financement_id") Long typeFinancementId,
            @RequestParam("attachedFiles") MultipartFile[] files,
            @RequestParam("simulationInfo") String simulationInfo
    ) {
        // Create dossier and save locally
        Long dossierId = dossierService.addDossier(clientId, typeCreditId, typeFinancementId, files, simulationInfo);

        // Send dossier ID to Kafka topic
      //  kafkaProducer.sendDossierToKafka("dossier-topic", String.valueOf(dossierId));

        return dossierId;
    }*/



/*@PostMapping("/add")
public Long addDossier(@RequestBody Map<String, Object> requestBody) {
    Long clientId = Long.parseLong(requestBody.get("client_id").toString());
    Long typeCreditId = Long.parseLong(requestBody.get("type_credit_id").toString());
    Long typeFinancementId = Long.parseLong(requestBody.get("type_financement_id").toString());
    MultipartFile[] files = (MultipartFile[]) requestBody.get("attachedFiles");
    String simulationInfo = (String) requestBody.get("simulationInfo");

    // Create dossier and save locally
    Long dossierId = dossierService.addDossier(clientId, typeCreditId, typeFinancementId, files, simulationInfo);

    return dossierId;
}
*/

    @PostMapping("/assign-dossier/{dossierId}/to-courtier/{courtierId}")
    public ResponseEntity<Dossier> assignDossierToCourtier(@PathVariable Long dossierId, @PathVariable Long courtierId) {
        Dossier assignedDossier = dossierService.assignDossierToCourtier(dossierId, courtierId);
        return ResponseEntity.ok(assignedDossier);
    }


    @GetMapping("/{courtierAgenceId}/dossiersnotassigned")
    public List<Dossier> getDossiersForCourtier(@PathVariable Long courtierAgenceId) {
        return dossierService.getDossiersForCourtier(courtierAgenceId);
    }




/// dosiieers traitte par le courtier
    @GetMapping("/courtier/{courtierId}/traitee")
    public List<Dossier> getTraiteeDossiersByCourtierId(@PathVariable Long courtierId) {
        return dossierService.getTraiteeDossiersByCourtier(courtierId);
    }




    @PutMapping("/{dossierId}/mark-as-traitee")
    public void markDossierAsTraitee(@PathVariable Long dossierId) {
        dossierService.updateDossierStatusToTraitee(dossierId);
    }
}
