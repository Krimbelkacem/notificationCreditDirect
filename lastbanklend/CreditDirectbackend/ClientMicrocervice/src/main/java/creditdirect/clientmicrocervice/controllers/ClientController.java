package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Client;
import creditdirect.clientmicrocervice.entities.Particulier;
import creditdirect.clientmicrocervice.repositories.ClientRepository;
import creditdirect.clientmicrocervice.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;



@RestController
@RequestMapping("/clients")
public class ClientController {

    private final ClientService clientService;

    @Autowired
    public ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        List<Client> clients = clientService.getAllClients();
        return new ResponseEntity<>(clients, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Client> getClientById(@PathVariable("id") Long id) {
        Client client = clientService.getClientById(id);
        return client != null ? new ResponseEntity<>(client, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }



    @PutMapping("/{id}")
    public ResponseEntity<Client> updateClient(@PathVariable("id") Long id, @RequestBody Client client) {
        Client updatedClient = clientService.updateClient(id, client);
        return updatedClient != null ? new ResponseEntity<>(updatedClient, HttpStatus.OK) :
                new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
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


    @PostMapping("/subscribe/particulier")
    public ResponseEntity<Particulier> subscribeParticulier(@RequestBody Particulier particulier) {
        Particulier subscribedParticulier = clientService.subscribeParticulier(particulier);
        return new ResponseEntity<>(subscribedParticulier, HttpStatus.CREATED);
    }
    @PutMapping("/addpassword")
    public ResponseEntity<String> updateClientPassword(@RequestParam Long id, @RequestBody Map<String, String> requestBody) {
        String password = requestBody.get("password");

        if (id == null || password == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid request format");
        }

        System.out.println("Received request to update password for Client ID: " + id);

        Client updatedClient = clientService.updateClientPassword(id, password);

        if (updatedClient != null) {
            return ResponseEntity.ok("Password updated successfully for Client ID: " + id);
        } else {
            System.out.println("Client with ID " + id + " not found");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client with ID " + id + " not found");
        }
    }

}
