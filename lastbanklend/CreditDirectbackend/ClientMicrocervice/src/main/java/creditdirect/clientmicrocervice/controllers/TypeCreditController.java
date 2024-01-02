package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.TypeCredit;
import creditdirect.clientmicrocervice.services.TypeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/type-credits")
public class TypeCreditController {

    private final TypeCreditService typeCreditService;

    @Autowired
    public TypeCreditController(TypeCreditService typeCreditService) {
        this.typeCreditService = typeCreditService;
    }

    @GetMapping
    public ResponseEntity<List<TypeCredit>> getAllTypeCredits() {
        List<TypeCredit> typeCredits = typeCreditService.getAllTypeCredits();
        return new ResponseEntity<>(typeCredits, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TypeCredit> getTypeCreditById(@PathVariable("id") Long id) {
        TypeCredit typeCredit = typeCreditService.getTypeCreditById(id);
        return typeCredit != null ? new ResponseEntity<>(typeCredit, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/ad")
    public ResponseEntity<TypeCredit> createTypeCredit(@RequestBody TypeCredit typeCredit) {
        TypeCredit createdTypeCredit = typeCreditService.createTypeCredit(typeCredit);
        return new ResponseEntity<>(createdTypeCredit, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TypeCredit> updateTypeCredit(@PathVariable("id") Long id,
                                                       @RequestBody TypeCredit typeCredit) {
        TypeCredit updatedTypeCredit = typeCreditService.updateTypeCredit(id, typeCredit);
        return updatedTypeCredit != null ? new ResponseEntity<>(updatedTypeCredit, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTypeCredit(@PathVariable("id") Long id) {
        typeCreditService.deleteTypeCredit(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping("/add")
    public ResponseEntity<TypeCredit> createTypeCredit(@RequestParam("nomCredit") String nomCredit,
                                                       @RequestParam("idFinancement") Long idFinancement,
                                                       @RequestParam(value = "prix", required = false) Double prix,
                                                       @RequestParam(value = "imageFile", required = false) MultipartFile imageFile) {
        TypeCredit typeCredit = new TypeCredit();
        typeCredit.setNomCredit(nomCredit);
        typeCredit.setIdFinancement(idFinancement);
        typeCredit.setPrix(prix);

        TypeCredit savedTypeCredit = typeCreditService.saveTypeCredit(typeCredit, imageFile);
        if (savedTypeCredit != null) {
            return ResponseEntity.status(HttpStatus.CREATED).body(savedTypeCredit);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}
