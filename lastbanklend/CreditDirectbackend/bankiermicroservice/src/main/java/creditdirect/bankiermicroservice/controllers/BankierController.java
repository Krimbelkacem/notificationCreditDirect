package creditdirect.bankiermicroservice.controllers;

import creditdirect.bankiermicroservice.cervices.BankierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/bankier")
public class BankierController {

    private final BankierService bankierService;

    @Autowired
    public BankierController(BankierService bankierService) {
        this.bankierService = bankierService;
    }

    @GetMapping("/callClientService")
    public void callClientService() {
        bankierService.callClientService();
    }
}
