package fr.uha.serfa.lpdao25.BiblioTook.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 * représente un auteur de livre <br/>
 * un auteur a rédigé des livres
 */
@Entity
public class Auteur extends Utilisateurs{
    @OneToMany
    private List<Livre> livres = new ArrayList<>();
    public String password = "monPass";

    public Auteur() {
    }

    public Auteur(String nom, String prenom, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
    }

    @JsonIgnore
    public List<Livre> getLivres() {
        return this.livres;
    }

    public void setLivres(List<Livre> livres) {
        this.livres = livres;
    }

    public String getPassword() {
        return this.password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void addLivre(Livre l){
        this.livres.add(l);
    }
}
