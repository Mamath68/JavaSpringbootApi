package fr.uha.serfa.lpdao25.BiblioTook.model;

import java.time.LocalDate;

public class Clients {

    private final String prenom;
    private final String nom;
    private final LocalDate dateNaissance;

    public Clients(String prenom, String nom, LocalDate dateNaissance) {
        this.prenom = prenom;
        this.nom = nom;
        this.dateNaissance = dateNaissance;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public String getNom() {
        return this.nom;
    }

    public LocalDate getDateNaissance() {
        return this.dateNaissance;
    }
}
