package creditdirect.bankiermicroservice.cervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import creditdirect.bankiermicroservice.repositories.BankierRepository;
import org.springframework.http.ResponseEntity;

// Other imports...

@Service
public class BankierServiceImpl implements BankierService {

    private final RestTemplate restTemplate;

    @Autowired
    public BankierServiceImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @Override
    public ResponseEntity<String> callClientService() {
        String clientServiceUrl = "http://localhost:8080"; // Replace with the actual URL
        String clientEndpoint = "/dossiers"; // Replace with the endpoint

        // Make a GET request to the client service's endpoint
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(clientServiceUrl + clientEndpoint, String.class);

        // Log the response from the client service
        String responseBody = responseEntity.getBody();
        System.out.println("Response from client service: " + responseBody);

        // Return the response received from the client service
        return ResponseEntity.status(responseEntity.getStatusCode()).body(responseBody);
    }
}