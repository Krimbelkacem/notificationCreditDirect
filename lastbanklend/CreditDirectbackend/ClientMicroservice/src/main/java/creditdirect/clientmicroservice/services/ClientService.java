package creditdirect.clientmicroservice.services;

import creditdirect.clientmicroservice.entities.Client;

public interface ClientService {
    Client registerClient(Client client);
    String login(String email, String password);
    String subscribe(String email, String phoneNumber);
}
