package fr.uha.serfa.lpdao25.BiblioTook.controller;

import fr.uha.serfa.lpdao25.BiblioTook.controller.dto.AuteurSecurise;
import fr.uha.serfa.lpdao25.BiblioTook.model.Auteur;
import fr.uha.serfa.lpdao25.BiblioTook.model.Bibliotheque;
import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;
import fr.uha.serfa.lpdao25.BiblioTook.utils.BibliothequeFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Illustration d'un controlleur "utile" pour un projet
 */
@RestController
public class BibliotookController {


    /**
     * Renvoi une nouvelle instance d'auteur.
     * Mappé sur la route /bibliotook/auteur
     *
     * @return Auteur
     */
    @GetMapping("/bibliotook/auteur")
    public Auteur basicAuteur() {
        return new Auteur();
    }

    /**
     * Renvoi un auteur sécurisé, un auteur sans password et dont le livre n’affiche pas son auteur (evite la récursivité)
     * explicite l'usage de DTO pour masquer des informations
     * mappé sur la route /bibliotook/auteurLivre
     *
     * @return AuteurSecurise
     */
    @GetMapping("/bibliotook/auteurLivre")
    public AuteurSecurise auteurAvecLivre() {
        Auteur a = new Auteur();
        Livre l = new Livre("encore 50 nuances de gray", "OASJ", LocalDate.now(), a);
        a.addLivre(l);
        return new AuteurSecurise(a);
    }

    /**
     * Permets de récupérer la liste de tous les auteurs dont le nom match avec le paramettre d'URL "name"
     * mappé sur la route /bibliotook/auteur/name
     *
     * @param nomRecherche - mappé avec "name"
     * @return les auteurs récupérés sont des auteurs sécurisés (pas de password, livres n’affichent pas leurs auteurs
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
     * Premier post réalisé
     * receptionne un livre sous forme de JSON et l'ajoute à la bibliothèque
     * mappée sur /bibliotook/livre
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
     * Une route qui permet d'ajouter un livre à la bibliothèque
     * l'auteur du livre est passé en argument de la route
     * si l'auteur n’existe pas dans la bibliothèque le livre n’est pas ajouté
     * la route ajouter "ajouter un livre à auteur existant" de postman permet de tester cette route
     * mappée sur /bibliotook/livre/auteurNom.
     *
     * @return ResponseEntity<Bibliotheque>
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
     * Renvoi une nouvelle instance de livre.
     * Mappé sur la route /bibliotook/livre
     *
     * @return Livre
     */
    @GetMapping("/bibliotook/livre")
    public Livre basicLivre() {
        return new Livre();
    }

    /**
     * Renvoi une nouvelle instance de bibliothèque.
     * Mappé sur la route /bibliotook/bibliotheque
     *
     * @return Bibliotheque
     */
    @GetMapping("/bibliotook/bibliotheque")
    public Bibliotheque basicBibliotheque() {
        return new Bibliotheque();
    }

    /**
     * Renvoi la bibliothèque singleton existant dans le serveur.
     * Mappé sur la route /bibliotook/bibliothequeBig
     *
     * @return Bibliotheque
     */
    @GetMapping("/bibliotook/bibliothequeBig")
    public Bibliotheque bigBibliotheque() {
        return BibliothequeFactory.getBigBibliotheque();
    }
}
