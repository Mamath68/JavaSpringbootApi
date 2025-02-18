package fr.uha.serfa.lpdao25.BiblioTook.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)

public abstract class Utilisateurs {
    @Id
    @GeneratedValue
    @Column(nullable = false)
    protected Long id;

    protected String nom = "James";
    protected String prenom = "Erika Leonard";
    protected LocalDate naissance = LocalDate.of(1963, 3, 7);

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public LocalDate getNaissance() {
        return this.naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }
}
