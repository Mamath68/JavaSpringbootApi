package fr.uha.serfa.lpdao25.BiblioTook.controller;

import fr.uha.serfa.lpdao25.BiblioTook.model.Author;
import fr.uha.serfa.lpdao25.BiblioTook.model.Bibliotheque;
import fr.uha.serfa.lpdao25.BiblioTook.model.Clients;
import fr.uha.serfa.lpdao25.BiblioTook.model.Livres;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@RestController
public class HelloWorldController {

    Author auteur1 = new Author(
            "J.R.R.",
            "Tolkien",
            LocalDate.of(1892, 1, 3)
    );
    Author auteur2 = new Author(
            "J.R.R.",
            "Martin",
            LocalDate.of(1948, 9, 21)
    );

    Livres livres1 = new Livres(
            1,
            "Le Seigneur des Anneaux",
            "978-2-234-05678-9",
            this.auteur1,
            LocalDate.of(1954, 7, 29)
    );
    Livres livres2 = new Livres(
            2,
            "Le Hobbit",
            "978-2-234-05678-8",
            this.auteur1,
            LocalDate.of(1937, 9, 20)
    );
    Livres livres3 = new Livres(
            3,
            "Le Silmarillion",
            "978-2-234-05678-7",
            this.auteur1,
            LocalDate.of(1977, 9, 15)
    );

    @GetMapping("/")
    public String index() {
        return "<h1>Hello World!</h1>";
    }

    @GetMapping("/auteurs")
    public Author[] author() {
        Author auteur1 = new Author(
                "J.R.R.",
                "Tolkien",
                LocalDate.of(1892, 1, 3)
        );
        Author auteur2 = new Author(
                "J.R.R.",
                "Martin",
                LocalDate.of(1948, 9, 21)
        );
        return new Author[]{auteur1, auteur2};
    }

    @GetMapping("/books")
    public Livres[] books() {
        Livres livres1 = new Livres(
                1,
                "Le Seigneur des Anneaux",
                "978-2-234-05678-9",
                this.auteur1,
                LocalDate.of(1954, 7, 29)
        );
        Livres livres2 = new Livres(
                2,
                "Le Hobbit",
                "978-2-234-05678-8",
                this.auteur1,
                LocalDate.of(1937, 9, 20)
        );
        Livres livres3 = new Livres(
                3,
                "Le Silmarillion",
                "978-2-234-05678-7",
                this.auteur1,
                LocalDate.of(1977, 9, 15)
        );
        return new Livres[]{livres1, livres2, livres3};
    }

    @GetMapping("/clients")
    public Clients clients() {
        return new Clients("George", "Cambodge", LocalDate.of(1987, 7, 29));
    }

    @GetMapping("/bibliotheque")
    public Bibliotheque bibliotheque() {
        List<Livres> livres = Arrays.asList(livres1, livres2, livres3);
        return new Bibliotheque("Bibliothèque Centrale", "19 Grand rue, 68100 Mulhouse", livres);
    }

}
