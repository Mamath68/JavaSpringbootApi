package fr.uha.serfa.lpdao25.BiblioTook.controller.dto;

import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;

import java.time.LocalDate;

public class LivreSansAuteur {

    public String titre = "50 nuances de Grey";
    public String ISBN = "1083KZH9D";
    public LocalDate datePublication = LocalDate.of(2012, 4, 3);

    public LivreSansAuteur(Livre l) {
        this.titre = l.getTitre();
        this.ISBN = l.getISBN();
        this.datePublication = l.getDatePublication();
    }
}
