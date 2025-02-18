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

/**
 * illustration d'un controlleur "utile" pour un projet
 */
@RestController
public class BibliotookController {

    public BibliotookController() {
        Faker f = new Faker();
        System.out.println(f.backToTheFuture().quote());
        System.out.println(f.artist().name());
        System.out.println(f.timeAndDate().birthday(20, 500));

        BibliothequeFactory.addRandomBooksToBibliotheque(100);
    }

    /**
     * renvoi une nouvelle instance d'auteur <br/>
     * mappé sur la route /bibliotook/auteur
     *
     * @return Auteur()
     */
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

    /**
     * renvoit un auteur sécurisé ; un auteur sans password et dont le livre n'affiche pas son auteur (evite la récursivité) <br/>
     * explicite l'usage de DTO pour masquer des informations <br/>
     * mappé sur la route /bibliotook/auteurLivre
     *
     * @return AuteurSecurise()
     */
    @GetMapping("/bibliotook/auteurLivre")
    public AuteurSecurise auteurAvecLivre() {
        Auteur a = new Auteur();
        Livre l = new Livre("encore 50 nuances de gray", "OASJ", LocalDate.now(), a);
        a.addLivre(l);
        return new AuteurSecurise(a);
    }

    /**
     * permet de récupérer la liste de tous les auteurs dont le nom match avec le paramettre d'URL "name" <br/>
     * mappé sur la route /bibliotook/auteur/name
     *
     * @param nomRecherche - mappé avec "name"
     * @return les auteurs récupérés sont des auteurs sécurisés (pas de password, livres n'affichent pas leurs auteurs
     */
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

    /**
     * premier post réalisé <br/>
     * receptionne un livre sous forme de JSON et l'ajoute à la bibliothèque <br/>
     * mappé sur /bibliotook/livre
     *
     * @param l - un livre transformé par jackson depuis le corp de la requete
     * @return la bibliotèque avec le livre ajouté
     */
    @PostMapping("/bibliotook/livre")
    public Bibliotheque ajouterLivre(@RequestBody Livre l) {
        Bibliotheque b = BibliothequeFactory.getBigBibliotheque();
        b.getLivres().add(l);
        return b;
    }


    /**
     * une route qui permet d'ajouter un livre à la bibliothèque <br/>
     * l'auteur du livre est passé en argument de la route <br/>
     * si l'auteur n'existe pas dans la bibliothèque le livre n'est pas ajouté <br/>
     * la route ajouter "ajouter livre a auteur existant" de postman permet de tester cette route <br/>
     * mappée sur /bibliotook/livre/auteurNom
     *
     * @return ResponseEntity
     */
    @PostMapping("/bibliotook/livre/{name}")
    public ResponseEntity<Bibliotheque> ajouterLivreCorrectement(@RequestBody Livre l, @PathVariable(value = "name") String nomAuteur) {
        Bibliotheque b = BibliothequeFactory.getBigBibliotheque();
        Set<Auteur> auteurs = b.auteurParNom(nomAuteur);

        for (Auteur a : auteurs) {
            if (a.getNom().equals(nomAuteur)) {
                b.getLivres().add(l);
                l.setAuteur(a);
                return new ResponseEntity<>(b, HttpStatus.CREATED);
            }
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    /**
     * renvoi une nouvelle instance de livre <br/>
     * mappé sur la route /bibliotook/livre
     *
     * @return Livre()
     */
    @GetMapping("/bibliotook/livre")
    public Livre basicLivre() {
        return new Livre();
    }

    /**
     * renvoi une nouvelle instance de bibliothèque <br/>
     * mappé sur la route /bibliotook/bibliotheque
     *
     * @return Bibliotheque
     */
    @GetMapping("/bibliotook/bibliotheque")
    public Bibliotheque basicBibliotheque() {
        return new Bibliotheque();
    }

    /**
     * renvoi la bibliothèque singleton existant dans le serveur. <br/>
     * mappé sur la route /bibliotook/bibliothequeBig
     *
     * @return BibliothequeFactory()
     */
    @GetMapping("/bibliotook/bibliothequeBig")
    public Bibliotheque bigBibliotheque() {
        return BibliothequeFactory.getBigBibliotheque();
    }


}
