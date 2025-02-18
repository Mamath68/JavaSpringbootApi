package fr.uha.serfa.lpdao25.BiblioTook.controller;


import fr.uha.serfa.lpdao25.BiblioTook.dao.UsagerRepository;
import fr.uha.serfa.lpdao25.BiblioTook.model.Usager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/bibliotook")
public class UsagerController {

    private final UsagerRepository db_user;

    public UsagerController(UsagerRepository ur) {
        this.db_user = ur;

        db_user.save(new Usager());
        Usager u = new Usager("Testot", "Fred", LocalDate.now());
        db_user.save(u);

        System.out.println(db_user.findAll());
    }

    @GetMapping("/usager")
    public List<Usager> getAllUsager() {
        return db_user.findAll();
    }

    @GetMapping("/usager/{id}")
    public ResponseEntity<Usager> getUsagerParID(@PathVariable Long id) {
        Optional<Usager> peutetreUsager = db_user.findById(id);
        if (peutetreUsager.isPresent())
            return new ResponseEntity<>(peutetreUsager.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        // autre facon :
        // return db_user.getReferenceById(id);
    }

    @PostMapping("/usager")
    public Usager addUsager(@RequestBody Usager usagerASauvegarder) {
        return db_user.save(usagerASauvegarder);
    }

    @DeleteMapping("/usager/{id}")
    public void deleteUsager(@PathVariable Long id) {
        db_user.deleteById(id);
    }

    @PatchMapping("/usager/{id}")
    public void updateUsager(@PathVariable Long id, @RequestBody Usager usagerData) {
        Usager u = db_user.getReferenceById(id);
        u.setNaissance(usagerData.getNaissance());
        u.setNom(usagerData.getNom());
        u.setPrenom(usagerData.getPrenom());
        db_user.save(u);
    }


}
