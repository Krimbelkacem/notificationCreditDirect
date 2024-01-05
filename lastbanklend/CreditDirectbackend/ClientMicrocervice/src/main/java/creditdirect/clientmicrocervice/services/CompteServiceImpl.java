package creditdirect.clientmicrocervice.services;

import creditdirect.clientmicrocervice.config.JwtUtil;
import creditdirect.clientmicrocervice.entities.Compte;
import creditdirect.clientmicrocervice.repositories.CompteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Service
public class CompteServiceImpl implements CompteService {
    @Autowired
    private JwtUtil jwtUtil;
    @Autowired
    private CompteRepository compteRepository;

    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Override
    public Compte signUp(Compte compte) {
        // Check if the NIN already exists
        Compte existingCompte = compteRepository.findByNin(compte.getNin());
        if (existingCompte != null) {
            // Handle duplication error or return null to indicate failure
            return null;
        }

        // Hash the password
        String hashedPassword = passwordEncoder.encode(compte.getPassword());
        compte.setPassword(hashedPassword);

        return compteRepository.save(compte);
    }
  /*  @Override
    public Compte signInByNin(String nin, String password) {
        Compte compte = compteRepository.findByNin(nin);
        if (compte != null && passwordEncoder.matches(password, compte.getPassword())) {
            return compte;
        }
        return null;
    }*/
  @Override
  public String signInByNin(String nin, String password) {
      Compte compte = compteRepository.findByNin(nin);
      if (compte != null && passwordEncoder.matches(password, compte.getPassword())) {
          String token = jwtUtil.generateToken(compte.getId(), compte.getRole());
          return token;
      }
      return null;
  }
    @Override
    public Compte findByNin(String nin) {
        return compteRepository.findByNin(nin);
    }

}
