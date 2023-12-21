package creditdirect.clientmicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;

@SpringBootApplication
@Configuration
@EntityScan(basePackages = "creditdirect.clientmicroservice.entities")
public class ClientMicroserviceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ClientMicroserviceApplication.class, args);
    }

}
