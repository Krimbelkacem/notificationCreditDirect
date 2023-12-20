package creditdirect.clientmicroservice.services;

import com.nimbusds.jose.*;
import creditdirect.clientmicroservice.entities.Client;
import creditdirect.clientmicroservice.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;

import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
@Service
public class ClientServiceImpl implements ClientService {
    private static final String SECRET_KEY = "ThisIsASecureSecretKeyWithAtLeast256BitsLength123456789012345678901234567890";
    // Replace with your actual secret key
    private static final long EXPIRATION_TIME = 864_000_000; // 10 days in milliseconds
    private static final Logger logger = LoggerFactory.getLogger(ClientServiceImpl.class);

    private final ClientRepository clientRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Autowired
    public ClientServiceImpl(ClientRepository clientRepository, BCryptPasswordEncoder passwordEncoder) {
        this.clientRepository = clientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Client registerClient(Client client) {
        // Encrypt the password before saving
        client.setPassword(passwordEncoder.encode(client.getPassword()));
        return clientRepository.save(client);
    }

    @Override
    public String login(String email, String password) {
        Client client = clientRepository.findByEmail(email);
        if (client != null && passwordEncoder.matches(password, client.getPassword())) {
            // Authentication successful, generate JWT token
            return generateToken(email);
        } else {
            return "Authentication failed";
        }
    }
    private String generateToken(String email) {
        try {
            JWTClaimsSet claims = new JWTClaimsSet.Builder()
                    .subject(email)
                    .issueTime(new Date())
                    .expirationTime(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                    .build();

            JWSHeader header = new JWSHeader.Builder(JWSAlgorithm.HS256)
                    .type(JOSEObjectType.JWT)
                    .build();

            SignedJWT signedJWT = new SignedJWT(header, claims);

            MACSigner signer = new MACSigner(SECRET_KEY);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            logger.error("Error generating JWT token for email: {}", email, e);
            // Add additional handling here if necessary
            return null;
        }
    }



    @Override
    public String subscribe(String email, String phoneNumber) {
        Client client = clientRepository.findByEmail(email);
        if (client != null) {
            client.setPhoneNumber(phoneNumber);
            clientRepository.save(client);
            return "Subscription successful";
        } else {
            return "User not found"; // Or handle accordingly if the user is not found
        }
    }
}
