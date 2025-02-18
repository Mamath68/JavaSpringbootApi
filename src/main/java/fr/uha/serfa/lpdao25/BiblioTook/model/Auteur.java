package fr.uha.serfa.lpdao25.BiblioTook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * Représente un auteur de livre. <br/>
 * Un auteur a rédigé des livres
 */
@Entity
public class Auteur {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    private String nom = "James";
    private String prenom = "Erika Leonard";
    private LocalDate naissance = LocalDate.of(1963, 3, 7);
    @OneToMany
    private final List<Livre> livres = new ArrayList<>();
    public String password = "monPass";

    public Auteur() {
    }

    public Auteur(String nom, String prenom, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public LocalDate getDateNaissance() {
        return this.naissance;
    }

    @JsonIgnore
    public List<Livre> getLivres() {
        return this.livres;
    }

    public void addLivre(Livre l) {
        this.livres.add(l);
    }
}
