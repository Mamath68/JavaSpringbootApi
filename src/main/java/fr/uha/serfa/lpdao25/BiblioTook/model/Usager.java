package fr.uha.serfa.lpdao25.BiblioTook.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usager extends Utilisateurs {

    @ElementCollection
    private List<Address> adresses = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn
    private Livre emprunt;

    public Usager(String nom, String prenom, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
    }

    public Usager() {
    }

    public Livre getEmprunt() {
        return this.emprunt;
    }

    public void setEmprunt(Livre emprunt) {
        this.emprunt = emprunt;
    }

    public int getNbrLivresEmprunt() {
        if (this.hasLivreEmprunt())
            return 1;
        return 0;
    }

    public boolean hasLivreEmprunt() {
        return this.emprunt != null;
    }

    @Override
    public String toString() {
        return "Usager{" +
                "id=" + this.getId() +
                ", nom='" + this.getNom() + '\'' +
                ", prenom='" + this.getPrenom() + '\'' +
                ", naissance=" + this.getNaissance() +
                '}';
    }
}
