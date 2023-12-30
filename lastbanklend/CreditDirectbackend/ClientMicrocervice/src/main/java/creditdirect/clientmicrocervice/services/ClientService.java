package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.entities.Client;
import creditdirect.clientmicrocervice.entities.Particulier;

import java.util.List;

public interface ClientService {
    List<Client> getAllClients();
    Client getClientById(Long id);
    Client createClient(Client client);
    Client updateClient(Long id, Client client);
    void deleteClient(Long id);



    Client registerClient(Client client);
    String login(String email, String password);


    Particulier subscribeParticulier(Particulier particulier);

    Client updateClientPassword(Long clientId, String newPassword);
}
