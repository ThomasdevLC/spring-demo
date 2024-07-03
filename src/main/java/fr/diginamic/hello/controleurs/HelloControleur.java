package fr.diginamic.hello.controleurs;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import fr.diginamic.hello.services.HelloService;

@RestController
@RequestMapping("/hello")
public class HelloControleur {

    // Injection via le constructeur
    @Autowired
    private  HelloService helloService;

    @GetMapping
    public String direHello() {
        return helloService.salutations();
    }
}
