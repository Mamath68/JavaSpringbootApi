package fr.uha.serfa.lpdao25.BiblioTook.dao;

import fr.uha.serfa.lpdao25.BiblioTook.model.Auteur;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuteurRepository extends JpaRepository<Auteur, Long> {





}
