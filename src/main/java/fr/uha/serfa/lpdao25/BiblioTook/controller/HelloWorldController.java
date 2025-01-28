package fr.uha.serfa.lpdao25.BiblioTook.controller;

import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
public class HelloWorldController {

    public HelloWorldController() {
        System.err.println("HELLOWORLDCONTROLLER A DEMARRER");
    }

    @GetMapping("/books")
    public Livre[] books() {
        Livre livre1 = new Livre(
                1,
                "Le Seigneur des Anneaux",
                "978-2-234-05678-9",
                "J.R.R. Tolkien",
                LocalDate.of(1954, 7, 29)
        );
        Livre livre2 = new Livre(
                2,
                "Le Hobbit",
                "978-2-234-05678-8",
                "J.R.R. Tolkien",
                LocalDate.of(1937, 9, 21)
        );
        Livre livre3 = new Livre(
                3,
                "Le Silmarillion",
                "978-2-234-05678-7",
                "J.R.R. Tolkien",
                LocalDate.of(1977, 9, 15)
        );
        return new Livre[]{livre1, livre2, livre3};
    }

    @GetMapping("/clients")
    public LocalDate clients() {
        return LocalDate.of(2012, 10, 29);
    }
}
