package fr.uha.serfa.lpdao25.BiblioTook.controller;

import fr.uha.serfa.lpdao25.BiblioTook.controller.dto.AuteurSecurise;
import fr.uha.serfa.lpdao25.BiblioTook.model.Auteur;
import fr.uha.serfa.lpdao25.BiblioTook.model.Bibliotheque;
import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;
import fr.uha.serfa.lpdao25.BiblioTook.utils.BibliothequeFactory;
import net.datafaker.Faker;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class BibliotookController {

    public BibliotookController() {
        Faker f = new Faker();
        System.out.println(f.backToTheFuture().quote());
        System.out.println(f.artist().name());
        System.out.println(f.timeAndDate().birthday(20, 500));

        BibliothequeFactory.addRandomBooksToBibliotheque(100);
    }

    @GetMapping("/bibliotook/auteur")
    public Auteur basicAuteur() {
        return new Auteur();
    }

    @PostMapping("/bibliotook/auteur")
    public Auteur ajoutAuteurAleatoire() {
        Faker faker = new Faker();
        String nom = faker.onePiece().character();
        String prenom = faker.tron().character();
        LocalDate naissance = faker.timeAndDate().birthday(19, 500);
        Auteur a = new Auteur(nom, prenom, naissance);

        String titre = faker.book().title();
        String isbn = faker.code().isbn10();
        LocalDate datePublication = faker.timeAndDate().birthday(19, 500);
        Livre l = new Livre(titre, isbn, datePublication, a);

        Bibliotheque b = BibliothequeFactory.getBigBibliotheque();
        b.getLivres().add(l);

        return a;
    }

    @GetMapping("/bibliotook/auteurLivre")
    public AuteurSecurise auteurAvecLivre() {
        Auteur a = new Auteur();
        Livre l = new Livre("encore 50 nuances de gray", "OASJ", LocalDate.now(), a);
        a.addLivre(l);
        return new AuteurSecurise(a);
    }

    @GetMapping("/bibliotook/auteur/{name}")
    public List<AuteurSecurise> getAuteurByName(@PathVariable(value = "name") String nomRecherche) {
        Bibliotheque b = BibliothequeFactory.getBigBibliotheque();
        Set<Auteur> auteurs = b.auteurParNom(nomRecherche);

        List<AuteurSecurise> auteurSecurises = new ArrayList<>();
        for (Auteur a : auteurs) {
            auteurSecurises.add(new AuteurSecurise(a));
        }
        return auteurSecurises;
    }

    @PostMapping("/bibliotook/livre")
    public Bibliotheque ajouterLivre(@RequestBody Livre l) {
        Bibliotheque b = BibliothequeFactory.getBigBibliotheque();
        b.getLivres().add(l);
        return b;
    }

    @PostMapping("/bibliotook/livre/{name}")
    public ResponseEntity<Bibliotheque> ajouterLivreCorrectement(@RequestBody Livre l, @PathVariable(value = "name") String nomAuteur) {
        Bibliotheque b = BibliothequeFactory.getBigBibliotheque();
        Set<Auteur> auteurs = b.auteurParNom(nomAuteur);

        for (Auteur a : auteurs) {
            if (a.getNom().equals(nomAuteur)) {
                b.getLivres().add(l);
                l.setAuteur(a);
                return new ResponseEntity(b, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/bibliotook/livre")
    public Livre basicLivre() {
        return new Livre();
    }

    @GetMapping("/bibliotook/bibliotheque")
    public Bibliotheque basicBibliotheque() {
        return new Bibliotheque();
    }

    @GetMapping("/bibliotook/bibliothequeBig")
    public Bibliotheque bigBibliotheque() {
        return BibliothequeFactory.getBigBibliotheque();
    }


}
