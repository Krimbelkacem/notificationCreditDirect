package creditdirect.clientmicrocervice.controllers;

import creditdirect.clientmicrocervice.entities.Client;
import creditdirect.clientmicrocervice.entities.Particulier;
import creditdirect.clientmicrocervice.repositories.ClientRepository;
import creditdirect.clientmicrocervice.services.ClientService;
import creditdirect.clientmicrocervice.services.EmailService;
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
    private final EmailService emailService;

    @Autowired
    public ClientController(ClientService clientService, EmailService emailService) {
        this.clientService = clientService;
        this.emailService=emailService;
    }
/////////////////get all client////////////////////////////
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
/////////////////////////delete client///////////////////////
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteClient(@PathVariable("id") Long id) {
        clientService.deleteClient(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

////////////////////////client login/////////////////////////
@PostMapping("/login")
public ResponseEntity<?> loginWithClientInfo(@RequestBody Map<String, String> credentials) {
    String email = credentials.get("email");
    String password = credentials.get("password");

    if (email == null || password == null) {
        return new ResponseEntity<>("Email or password missing", HttpStatus.BAD_REQUEST);
    }

    Map<String, Object> authenticationResult = clientService.loginWithClientInfo(email, password);

    if (authenticationResult.containsKey("error")) {
        return new ResponseEntity<>(authenticationResult.get("error"), HttpStatus.UNAUTHORIZED);
    } else {
        // Return client info and token in the response
        return ResponseEntity.ok(authenticationResult);
    }
}
    ////////////encien loginnn
    @PostMapping("/login/encienne")
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



//////////////////inscription particulier///////////////////////////
    @PostMapping("/subscribe/particulier")
    public ResponseEntity<Particulier> subscribeParticulier(@RequestBody Particulier particulier) {
        Particulier subscribedParticulier = clientService.subscribeParticulier(particulier);
        return new ResponseEntity<>(subscribedParticulier, HttpStatus.CREATED);
    }


    ///////////////////updaate client password/////////////////////////
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
   /* @GetMapping("/activate")
    public ResponseEntity<String> activateClientByEmail(@RequestParam("email") String email) {
        clientService.activateClientByEmail(email);

        String htmlResponse = "<html>"
                + "<head><title>Activation Successful</title></head>"
                + "<body>"
                + "<h1>Client Activation</h1>"
                + "<p>Client with email " + email + " has been activated.</p>"
                + "</body>"
                + "</html>";

        return ResponseEntity.status(HttpStatus.OK).body(htmlResponse);
    }*/




    /////////////////////////activer compte client via email////////////////////////////
    @GetMapping("/activate")
    public ResponseEntity<String> activateClientByEmail(@RequestParam("email") String email) {
        clientService.activateClientByEmail(email);

        String htmlResponse = "<html>"
                + "<head>"
                + "<title>Activation Successful</title>"
                + "<link rel=\"stylesheet\" href=\"https://fonts.googleapis.com/css?family=Poppins:100,100i,200,200i,300,300i,400,400i,500,500i,600,600i,700,700i,800,800i,900,900i&subset=devanagari,latin-ext\">"
                + "<style>"
                + "* {"
                + "  padding: 0;"
                + "  margin: 0;"
                + "  box-sizing: border-box;"
                + "  font-family: 'Poppins', sans-serif;"
                + "}"
                + "body {"
                + "  background-color: #343a40;"
                + "  display: flex;"
                + "  justify-content: center;"
                + "  align-items: center;"
                + "  min-height: 100vh;"
                + "  user-select: none;"
                + "}"
                + ".card {"
                + "  border-radius: 10px;"
                + "  filter: drop-shadow(0 5px 10px 0 #ffffff);"
                + "  width: 400px;"
                + "  height: 180px;"
                + "  background-color: #ffffff;"
                + "  padding: 20px;"
                + "  position: relative;"
                + "  z-index: 0;"
                + "  overflow: hidden;"
                + "  transition: 0.6s ease-in;"
                + "}"
                + ".card::before {"
                + "  content: '';"
                + "  position: absolute;"
                + "  z-index: -1;"
                + "  top: -15px;"
                + "  right: -15px;"
                + "  background: #7952b3;"
                + "  height: 220px;"
                + "  width: 25px;"
                + "  border-radius: 32px;"
                + "  transform: scale(1);"
                + "  transform-origin: 50% 50%;"
                + "  transition: transform 0.25s ease-out;"
                + "}"
                + ".card:hover::before {"
                + "  transition-delay: 0.2s;"
                + "  transform: scale(40);"
                + "}"
                + ".card:hover {"
                + "  color: #ffffff;"
                + "}"
                + ".card p {"
                + "  padding: 10px 0;"
                + "}"
                + "</style>"
                + "</head>"
                + "<body>"
                + "<div class=\"row\">"
                + "<div class=\"card\">"
                + "<h4>Client Activation</h4>"
                + "<p>Client with email " + email + " has been activated.</p>"
                + "</div>"
                + "</div>"
                + "</body>"
                + "</html>";

        return ResponseEntity.status(HttpStatus.OK).body(htmlResponse);
    }


///////////////envoyer un email de confirmation///////////////////////

    @PostMapping("/send-confirmation-email")
    public String sendConfirmationEmail(@RequestParam("email") String recipientEmail) {
        emailService.sendConfirmationEmail(recipientEmail);
        return "Confirmation email sent to " + recipientEmail;
    }
}
