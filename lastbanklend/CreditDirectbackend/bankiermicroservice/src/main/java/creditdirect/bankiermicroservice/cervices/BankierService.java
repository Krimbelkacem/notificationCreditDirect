package creditdirect.bankiermicroservice.cervices;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;



public interface BankierService {
    ResponseEntity<String> callClientService();
}

