package creditdirect.clientmicrocervice.config;

import com.nimbusds.jose.JOSEException;
import com.nimbusds.jose.JWSAlgorithm;
import com.nimbusds.jose.JWSHeader;
import com.nimbusds.jose.Payload;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jwt.JWTClaimsSet;
import com.nimbusds.jwt.SignedJWT;
import creditdirect.clientmicrocervice.entities.RoleType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import java.time.Instant;
import java.util.Date;

@Component
public class JwtUtil {

    @Value("${jwt.secret}") // Load secret key from configuration
    private String secretKey;

    @Value("${jwt.expiration.ms}") // Load expiration time from configuration
    private long expirationTimeMs;

    public String generateToken(Long userId, RoleType role) {
        try {
            Instant now = Instant.now();
            Date issuedAt = Date.from(now);
            Date expiresAt = Date.from(now.plusMillis(expirationTimeMs));

            JWTClaimsSet claimsSet = new JWTClaimsSet.Builder()
                    .subject(Long.toString(userId))
                    .claim("role", role.toString())
                    .issueTime(issuedAt)
                    .expirationTime(expiresAt)
                    .build();

            SignedJWT signedJWT = new SignedJWT(new JWSHeader(JWSAlgorithm.HS256), claimsSet);

            MACSigner signer = new MACSigner(secretKey);
            signedJWT.sign(signer);

            return signedJWT.serialize();
        } catch (JOSEException e) {
            // Handle exception
            return null;
        }
    }

    // Getter and setter methods for secretKey and expirationTimeMs
}
