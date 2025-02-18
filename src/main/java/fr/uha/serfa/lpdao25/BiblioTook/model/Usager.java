package fr.uha.serfa.lpdao25.BiblioTook.model;

import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Usager extends Utilisateurs{

    @ElementCollection
    private List<Address> adresses = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY)
    private Livre emprunt;

    public Livre getEmprunt() {
        return emprunt;
    }


    public Usager(String nom, String prenom, LocalDate naissance) {
        this.nom = nom;
        this.prenom = prenom;
        this.naissance = naissance;
    }

    public Usager() {
    }



    public int getNbrLivresEmprunt() {
        return 0;
    }


    @Override
    public String toString() {
        return "Usager{" +
                "id=" + id +
                ", nom='" + nom + '\'' +
                ", prenom='" + prenom + '\'' +
                ", naissance=" + naissance +
                '}';
    }
}
