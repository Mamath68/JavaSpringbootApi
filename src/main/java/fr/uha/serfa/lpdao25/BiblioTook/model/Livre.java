package fr.uha.serfa.lpdao25.BiblioTook.model;

import java.time.LocalDate;

public class Livre {
    private final int id;
    private final String titre;
    private final String isbn;
    private final String auteur;
    private final LocalDate dateParution;

    public Livre(int id, String titre, String isbn, String auteur, LocalDate date) {
        this.id = id;
        this.titre = titre;
        this.isbn = isbn;
        this.auteur = auteur;
        this.dateParution = date;
    }

    public int getId() {
        return this.id;
    }
    public String getTitre() {
        return this.titre;
    }

    public String getIsbn() {
        return this.isbn;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public LocalDate getDateParution() {
        return this.dateParution;
    }
}
