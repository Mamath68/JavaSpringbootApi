package fr.uha.serfa.lpdao25.BiblioTook.dao;

import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Set;

@Repository
public interface LivreRepository extends JpaRepository<Livre, Long> {

}
