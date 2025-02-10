package fr.uha.serfa.lpdao25.BiblioTook.controller.dto;

import fr.uha.serfa.lpdao25.BiblioTook.model.Auteur;
import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AuteurSecurise {

    public String nom = "James";
    public String prenom = "Erika Leonard";
    public LocalDate dateNaissance = LocalDate.of(1963, 3, 7);
    public List<LivreSansAuteur> livres = new ArrayList<>();

    public AuteurSecurise(Auteur a) {
        this.nom = a.getNom();
        this.dateNaissance = a.getDateNaissance();
        this.prenom = a.getPrenom();
        for (Livre aTransformer : a.getLivres()) {
            LivreSansAuteur lsa = new LivreSansAuteur(aTransformer);
            this.livres.add(lsa);
        }
    }

}
