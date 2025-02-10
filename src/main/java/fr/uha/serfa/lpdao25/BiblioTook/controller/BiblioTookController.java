package fr.uha.serfa.lpdao25.BiblioTook.controller;

import fr.uha.serfa.lpdao25.BiblioTook.controller.dto.AuteurSecurise;
import fr.uha.serfa.lpdao25.BiblioTook.model.Auteur;
import fr.uha.serfa.lpdao25.BiblioTook.model.Bibliotheque;
import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;
import fr.uha.serfa.lpdao25.BiblioTook.utils.BibliothequeFactory;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@RestController
public class BiblioTookController {

    @GetMapping("/bibliotook/auteur")
    public Auteur basicAuteur() {
        return new Auteur();
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
