package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Compte;
import creditdirect.clientmicrocervice.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/banque/comptes")
public class CompteController {





    @Autowired
    private CompteService compteService;


    @GetMapping
    public List<Compte> getAllComptes() {
        return compteService.getAllComptes();
    }


    @PostMapping("/create")
    public ResponseEntity<Object> signUp(@RequestBody Compte compte) {
        try {
            Compte createdCompte = compteService.signUp(compte);
            if (createdCompte != null) {
                return new ResponseEntity<>(createdCompte, HttpStatus.CREATED);
            } else {
                return new ResponseEntity<>("Failed to create account", HttpStatus.BAD_REQUEST);
            }
        } catch (Exception e) {
            return new ResponseEntity<>("Error creating account: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/signin")
    public ResponseEntity<Map<String, Object>> signIn(@RequestBody Map<String, String> credentials) {
        String nin = credentials.get("nin");
        String password = credentials.get("password");

        String signedInToken = compteService.signInByNin(nin, password);
        Compte createdCompte = compteService.findByNin(nin); // Assuming this method retrieves the account

        if (signedInToken != null && createdCompte != null) {
            Map<String, Object> response = new HashMap<>();
            response.put("token", signedInToken);
            response.put("compte", createdCompte);
            return ResponseEntity.ok(response); // Send token and account in response body as JSON
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }



}
