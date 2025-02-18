package fr.uha.serfa.lpdao25.BiblioTook.model;

import jakarta.persistence.*;

import java.time.LocalDate;

/**
 * Représente un livre. <br/>
 * Un livre est rédigé par UN auteur.
 */
@Entity
public class Livre {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;

    private String titre = "50 nuances de Grey";
    private String ISBN = "1083KZH9D";
    private LocalDate datePublication = LocalDate.of(2012, 4, 3);

    @ManyToOne
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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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

    public void setDatePublication(LocalDate datePublication) {
        this.datePublication = datePublication;
    }

    public Auteur getAuteur() {
        return auteur;
    }

    public void setAuteur(Auteur a) {
        this.auteur = a;
        a.addLivre(this);
    }
}
