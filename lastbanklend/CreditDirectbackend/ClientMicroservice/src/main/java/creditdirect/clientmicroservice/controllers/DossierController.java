package creditdirect.clientmicroservice.controllers;

import creditdirect.clientmicroservice.entities.Dossier;
import creditdirect.clientmicroservice.services.DossierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/dossiers")
public class DossierController {

    private final DossierService dossierService;

    @Autowired
    public DossierController(DossierService dossierService) {
        this.dossierService = dossierService;
    }

    @GetMapping
    public List<Dossier> getAllDossiers() {
        return dossierService.getAllDossiers();
    }

    @GetMapping("/{id}")
    public Dossier getDossierById(@PathVariable Long id) {
        return dossierService.getDossierById(id);
    }

    @GetMapping("/client/{clientId}")
    public List<Dossier> getDossiersByClientId(@PathVariable Long clientId) {
        return dossierService.getDossiersByClientId(clientId);
    }

    @PostMapping("/client/{clientId}")
    public Dossier addDossierForClient(@PathVariable Long clientId, @RequestBody Dossier dossier) {
        return dossierService.addDossierForClient(clientId, dossier);
    }
    @PostMapping("/client/{clientId}/upload")
    public Dossier addDossierForClientWithFiles(
            @PathVariable Long clientId,
            @RequestBody Dossier dossier,
            @RequestParam("files") List<MultipartFile> files
    ) {
        return dossierService.addDossierForClientWithFiles(clientId, dossier, files);
    }
    @PostMapping("/add")
    public Long addDossier(
            @RequestParam("client_id") Long clientId,
            @RequestParam("typeCreditID") Long typeCreditId,
            @RequestParam("typeFinancementID") Long typeFinancementId,
            @RequestParam("attachedFiles") MultipartFile[] files
    ) {
        // Assuming you'll handle file upload and save logic in DossierService
        return dossierService.addDossier(clientId, typeCreditId, typeFinancementId, files);
    }
}
