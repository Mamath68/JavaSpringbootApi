package fr.uha.serfa.lpdao25.BiblioTook.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;

import java.time.LocalDate;

@Entity
public class Usager {

    @Id
    @GeneratedValue
    @Column(nullable = false)
    private Long id;
    private String prenom;
    private String nom;
    private LocalDate naissance;
    private int nbrLivresEmprunt;

    public Usager(String prenom, String nom, LocalDate naissance, int nbrLivresEmprunt) {
        this.prenom = prenom;
        this.nom = nom;
        this.naissance = naissance;
        this.nbrLivresEmprunt = nbrLivresEmprunt;
    }

    public Usager() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public LocalDate getNaissance() {
        return naissance;
    }

    public void setNaissance(LocalDate naissance) {
        this.naissance = naissance;
    }

    public int getNbrLivresEmprunt() {
        return nbrLivresEmprunt;
    }

    public void setNbrLivresEmprunt(int nbrLivresEmprunt) {
        this.nbrLivresEmprunt = nbrLivresEmprunt;
    }

    @Override
    public String toString() {
        return "Usager{" +
                "id=" + this.getId() +
                ", prenom='" + this.getPrenom() + '\'' +
                ", nom='" + this.getNom() + '\'' +
                ", naissance=" + this.getNaissance() +
                ", nbrLivresEmprunt=" + this.getNbrLivresEmprunt() +
                '}';
    }
}
