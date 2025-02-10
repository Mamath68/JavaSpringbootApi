package fr.uha.serfa.lpdao25.BiblioTook.model;

import java.time.LocalDate;

public class Livre {
    private String titre = "50 nuances de Grey";
    private String ISBN = "1083KZH9D";
    private LocalDate datePublication = LocalDate.of(2012,04,03);
    private Auteur auteur = new Auteur();

    public Livre() {
        this.auteur.addLivre(this);
    }

    public Livre(String titre, String ISBN, LocalDate datePublication, Auteur auteur) {
        this.titre = titre;
        this.ISBN = ISBN;
        this.datePublication = datePublication;
        this.auteur = auteur;
        this.auteur.addLivre(this);
    }

    public String getTitre() {
        return titre;
    }

    public String getISBN() {
        return ISBN;
    }
    public void setISBN(String ISBN) {
        this.ISBN = ISBN;
    }

    public LocalDate getDatePublication() {
        return datePublication;
    }

    public Auteur getAuteur() {
        return auteur;
    }


}
