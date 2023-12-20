package creditdirect.clientmicroservice.controllers;

import creditdirect.clientmicroservice.entities.Client;
import creditdirect.clientmicroservice.repositories.ClientRepository;
import creditdirect.clientmicroservice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping("/register")
    public ResponseEntity<Client> registerClient(@RequestBody Client client) {
        Client savedClient = clientService.registerClient(client);
        return new ResponseEntity<>(savedClient, HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");

        if (email == null || password == null) {
            return new ResponseEntity<>("Email or password missing", HttpStatus.BAD_REQUEST);
        }

        String token = clientService.login(email, password);

        if ("Authentication failed".equals(token)) {
            return new ResponseEntity<>("Authentication failed", HttpStatus.UNAUTHORIZED);
        } else {
            // Create a JSON object to send the token in the response
            Map<String, String> response = new HashMap<>();
            response.put("token", token);
            return ResponseEntity.ok(response);
        }
    }


    @PostMapping("/subscribe")
    public ResponseEntity<String> subscribe(@RequestParam String email, @RequestParam String phoneNumber) {
        String result = clientService.subscribe(email, phoneNumber);
        if (result.equals("User not found")) {
            return new ResponseEntity<>("User not found", HttpStatus.NOT_FOUND);
        } else {
            return new ResponseEntity<>(result, HttpStatus.OK);
        }
    }
}
