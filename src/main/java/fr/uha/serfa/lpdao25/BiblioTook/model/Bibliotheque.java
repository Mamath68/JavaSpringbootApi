package fr.uha.serfa.lpdao25.BiblioTook.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Représente une bibliothèque dans notre domaine (gestion de bibliothèques)
 */
@Entity
public class Bibliotheque {
    @Id
    private Long id;

    private String adresse = "18 grand rue Mulhouse";
    private String nom = "Bibliothèque Central";
    @OneToMany
    private List<Livre> livres = new ArrayList<>();

    public Bibliotheque() {
        livres.add(new Livre());
        livres.add(new Livre("Jardinage 2024", "1028347SHCI", LocalDate.now(), new Auteur("Florent", "Bourgeois", LocalDate.now())));

    }

    public Bibliotheque(String adresse, String nom, List<Livre> livres) {
        this.adresse = adresse;
        this.nom = nom;
        this.livres = livres;
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getNom() {
        return nom;
    }

    public List<Livre> getLivres() {
        return livres;
    }

    /**
     * Permets de connaitre l'ensemble des auteurs de la bibliothèque
     * @return auteurs
     */
    public Set<Auteur> tousLesAuteurs(){
        Set<Auteur> auteurs = new HashSet<>();
        for (Livre l : this.livres){
            auteurs.add(l.getAuteur());
        }
        return auteurs;
    }

    /**
     * Permets de connaitre tous les auteurs dont le NOM contient partiellement la chaine en paramettre
     * @param nomRecherche String
     * @return auteursQuiMatchent
     */
    public Set<Auteur> auteurParNom(String nomRecherche){
        Set<Auteur> auteursQuiMatchent = new HashSet<>();
        for (Auteur a : this.tousLesAuteurs()){
            if(a.getNom().contains(nomRecherche)){
                auteursQuiMatchent.add(a);
            }
        }
        return  auteursQuiMatchent;
    }
}
