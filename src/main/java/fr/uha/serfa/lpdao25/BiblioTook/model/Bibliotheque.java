package fr.uha.serfa.lpdao25.BiblioTook.model;

import java.util.ArrayList;
import java.util.List;

public class Bibliotheque {

    private final String nom;
    private final String addresse;
    private List<Livres> livres = new ArrayList<>();

    public Bibliotheque(String nom, String addresse, List<Livres> livres) {
        this.nom = nom;
        this.addresse = addresse;
        this.livres = livres;
    }

    public String getNom() {
        return this.nom;
    }

    public List<Livres> getLivres() {
        return this.livres;
    }

    public String getAddresse() {
        return this.addresse;
    }
}
