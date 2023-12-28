package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.TypeCredit;
import creditdirect.clientmicrocervice.services.TypeCreditService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

    @PostMapping
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
}
