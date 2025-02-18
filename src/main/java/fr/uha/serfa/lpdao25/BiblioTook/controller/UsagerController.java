package fr.uha.serfa.lpdao25.BiblioTook.controller;

import fr.uha.serfa.lpdao25.BiblioTook.dao.UsagerRepository;
import fr.uha.serfa.lpdao25.BiblioTook.model.Usager;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bibliotook")
public class UsagerController {
    private final UsagerRepository userRepo;

    public UsagerController(UsagerRepository userRepo) {
        this.userRepo = userRepo;
    }

    @GetMapping("/usager")
    public List<Usager> getAllUsager() {
        return userRepo.findAll();
    }

    @GetMapping("/usager/{id}")
    public Optional<Usager> getOneUsagerById(@PathVariable Long id) {
        return userRepo.findById(id);
    }

    @PostMapping("/usager")
    public Usager addUser(@RequestBody Usager usager) {
        return userRepo.save(usager);
    }

    @DeleteMapping("/usager")
    public void deleteAllUsager() {
        userRepo.deleteAll();
    }

    @DeleteMapping("/usager/{id}")
    public void deleteOnUsagerById(@PathVariable Long id) {
        userRepo.deleteById(id);
    }

    @PatchMapping("/usager/{id}")
    public Usager updateOnUsagerById(@PathVariable Long id, @RequestBody Usager usagerDetails) {
        Usager usager = userRepo.findById(id).orElseThrow(() -> new RuntimeException("Usager not found"));
        usager.setNom(usagerDetails.getNom());
        usager.setPrenom(usagerDetails.getPrenom());
        usager.setNaissance(usagerDetails.getNaissance());
        usager.setNbrLivresEmprunt(usagerDetails.getNbrLivresEmprunt());
        return userRepo.save(usager);
    }

    @PatchMapping("/usager")
    public List<Usager> updateAllUsager(@RequestBody List<Usager> usagers) {
        return userRepo.saveAll(usagers);
    }
}
