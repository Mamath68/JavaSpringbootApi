package fr.uha.serfa.lpdao25.BiblioTook.model;

import org.springframework.cglib.core.Local;

import java.time.LocalDate;

public class Author {

    private final String prenom;
    private final String nom;
    private final LocalDate naissance;

    public Author(String prenom, String nom, LocalDate naissance) {
        this.prenom = prenom;
        this.nom = nom;
        this.naissance = naissance;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public LocalDate getNaissance() {
        return naissance;
    }
}
