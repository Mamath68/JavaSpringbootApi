package fr.uha.serfa.lpdao25.BiblioTook.model;

import java.time.LocalDate;

public class Livres {
    private final int id;
    private final String titre;
    private final String isbn;
    private final Author auteur;
    private final LocalDate dateParution;

    public Livres(int id, String titre, String isbn, Author auteur, LocalDate date) {
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

    public Author getAuteur() {
        return this.auteur;
    }

    public LocalDate getDateParution() {
        return this.dateParution;
    }
}
