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
///////////////get all dossiers////////////////////
    @GetMapping("/all")
    @PreAuthorize("hasRole('admin')")
    public ResponseEntity<List<Dossier>> getAllDossiers() {
        List<Dossier> dossiers = dossierService.getAllDossiers();
        return new ResponseEntity<>(dossiers, HttpStatus.OK);
    }
    ////////////////get dosssier by id dossiers /////////////////////////
    @GetMapping("/{id}")
    public Dossier getDossierById(@PathVariable Long id) {
        return dossierService.getDossierById(id);
    }


    //////////////////get dossiiers by id client/////////////////

    @GetMapping("/client/{clientId}")
    public List<Dossier> getDossiersByClientId(@PathVariable Long clientId) {
        return dossierService.getDossiersByClientId(clientId);
    }

    ///////////////////add dossier /////////////////
    @PostMapping("/adddossier")
    public ResponseEntity<Dossier> addDossier(@RequestBody Dossier dossier) {
        Dossier addedDossier = dossierService.addDossier(dossier);
        return ResponseEntity.ok(addedDossier);
    }
/////////////////////update dossier add files/////////////////////////
    @PostMapping("/{dossierId}/files")
    public ResponseEntity<Dossier> updateFilesForDossier(
            @PathVariable Long dossierId,
            @RequestParam("files") MultipartFile[] files
    ) {
        Dossier updatedDossier = dossierService.updateFilesForDossier(dossierId, files);
        return ResponseEntity.ok(updatedDossier);
    }



//////////////////////////////// asign dossiers to courtier///////////////////
    @PostMapping("/assign-dossier/{dossierId}/to-courtier/{courtierId}")
    public ResponseEntity<Dossier> assignDossierToCourtier(@PathVariable Long dossierId, @PathVariable Long courtierId) {
        Dossier assignedDossier = dossierService.assignDossierToCourtier(dossierId, courtierId);
        return ResponseEntity.ok(assignedDossier);
    }

/////////////dossier agence non asignd
    @GetMapping("/{courtierAgenceId}/dossiersnotassigned")
    public List<Dossier> getDossiersForCourtier(@PathVariable Long courtierAgenceId) {
        return dossierService.getDossiersForCourtier(courtierAgenceId);
    }


///////////// dossier en cours for courtiers
    @GetMapping("/courtier/{courtierId}/Encours")
    public List<Dossier> getDossiersencoursForCourtier(@PathVariable Long courtierId) {
        return dossierService.getDossiersencoursForCourtier(courtierId);
    }

/// dosiieers traitte par le courtier
    @GetMapping("/courtier/{courtierId}/traitee")
    public List<Dossier> getTraiteeDossiersByCourtierId(@PathVariable Long courtierId) {
        return dossierService.getTraiteeDossiersByCourtier(courtierId);
    }



////////////////
    @PutMapping("/{dossierId}/mark-as-traitee")
    public void markDossierAsTraitee(@PathVariable Long dossierId) {
        dossierService.updateDossierStatusToTraitee(dossierId);
    }

/////////once the client validate dossiers asign it to agence or diretion re

    @PostMapping("/assign-agency/{dossierId}")
    public ResponseEntity<Dossier> assignAgencyToDossier(@PathVariable Long dossierId) {
        Dossier updatedDossier = dossierService.affectiondossieragence(dossierId);
        return ResponseEntity.ok(updatedDossier);
    }






    //////////////////////delete file


    @DeleteMapping("/{dossierId}/files/{fileName}")
    public ResponseEntity<String> deleteFileFromDossier(
            @PathVariable Long dossierId,
            @PathVariable String fileName) {

        boolean isDeleted = dossierService.deleteFileByDossierIdAndFileName(dossierId, fileName);

        if (isDeleted) {
            return ResponseEntity.ok("File deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("File not found or dossier not found.");
        }
    }
}
