package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Compte;
import creditdirect.clientmicrocervice.services.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/banque/comptes")
public class CompteController {

    @Autowired
    private CompteService compteService;

    @PostMapping("/create")
    public ResponseEntity<String> signUp(@RequestBody Compte compte) {
        Compte createdCompte = compteService.signUp(compte);
        if (createdCompte != null) {
            return new ResponseEntity<>("Account created successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Failed to create account", HttpStatus.BAD_REQUEST);
        }
    }


    @PostMapping("/signin")
    public ResponseEntity<Map<String, String>> signIn(@RequestBody Map<String, String> credentials) {
        String nin = credentials.get("nin");
        String password = credentials.get("password");

        String signedInToken = compteService.signInByNin(nin, password);
        if (signedInToken != null) {
            Map<String, String> response = new HashMap<>();
            response.put("token", signedInToken);
            return ResponseEntity.ok(response); // Send token in response body as JSON
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }


}
