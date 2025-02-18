package fr.uha.serfa.lpdao25.BiblioTook;

import fr.uha.serfa.lpdao25.BiblioTook.dao.AuteurRepository;
import fr.uha.serfa.lpdao25.BiblioTook.dao.UsagerRepository;
import fr.uha.serfa.lpdao25.BiblioTook.model.Bibliotheque;
import fr.uha.serfa.lpdao25.BiblioTook.utils.BibliothequeFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class BiblioTookApplication {

    public static void main(String[] args) {
        SpringApplication.run(BiblioTookApplication.class, args);
    }

    @Bean
    CommandLineRunner initRepos(AuteurRepository auteurRepo, UsagerRepository usagerRepo) {
        return args -> {
            System.out.println("\n\nInitialisation du projet\n---------------");
            Bibliotheque b = BibliothequeFactory.getBigBibliotheque();
            System.out.println(b.tousLesAuteurs());
            auteurRepo.saveAll(b.tousLesAuteurs());
            System.out.println("\n\nFIN \n---------------");
        };
    }
}
