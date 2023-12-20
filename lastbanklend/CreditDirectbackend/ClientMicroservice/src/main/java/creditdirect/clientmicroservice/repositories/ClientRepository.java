package creditdirect.clientmicroservice.repositories;

import creditdirect.clientmicroservice.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    Client findByEmail(String email);
    // Add custom query methods if needed
}
