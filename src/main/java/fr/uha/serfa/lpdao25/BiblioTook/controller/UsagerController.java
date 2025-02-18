package fr.uha.serfa.lpdao25.BiblioTook.controller;


import fr.uha.serfa.lpdao25.BiblioTook.dao.LivreRepository;
import fr.uha.serfa.lpdao25.BiblioTook.dao.UsagerRepository;
import fr.uha.serfa.lpdao25.BiblioTook.model.Livre;
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
    private final LivreRepository livreRepo;

    public UsagerController(UsagerRepository ur, LivreRepository lr) {
        this.db_user = ur;
        this.livreRepo = lr;

        db_user.save(new Usager());
        Usager u = new Usager("Fred", "Fred", LocalDate.now());
        db_user.save(u);

        System.out.println(db_user.findAll());

    }


    @GetMapping("/usager")
    public List<Usager> getAllUsager(){
        return db_user.findAll();
    }

    @GetMapping("/usager/{id}")
    public ResponseEntity<Usager> getUsagerParID(@PathVariable Long id){
        Optional<Usager> peutetreUsager = db_user.findById(id);
        if(peutetreUsager.isPresent())
            return new ResponseEntity<>(peutetreUsager.get(), HttpStatus.OK);
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/usager")
    public Usager addUsager(@RequestBody Usager usagerASauvegarder){
        return db_user.save(usagerASauvegarder);
    }

    @DeleteMapping("/usager/{id}")
    public void deleteUsager(@PathVariable Long id){
         db_user.deleteById(id);
    }

    @PatchMapping("/usager/{id}")
    public void updateUsager(@PathVariable Long id, @RequestBody Usager usagerData){
        Usager u = db_user.getReferenceById(id);
        u.setNaissance(usagerData.getNaissance());
        u.setNom(usagerData.getNom());
        u.setPrenom(usagerData.getPrenom());
        db_user.save(u);
    }

    @PostMapping("/usager/{idUsager}/emprunte/{idLivre}")
    public ResponseEntity emprunter(@PathVariable(name = "idUsager") Long idUsager, @PathVariable Long idLivre){

        Optional<Usager> optionalUsager = db_user.findById(idUsager);
        if(optionalUsager.isEmpty())
            return ResponseEntity.notFound().build();

        Optional<Livre> optionalLivre = livreRepo.findById(idLivre);
        if(optionalLivre.isEmpty())
            return ResponseEntity.notFound().build();

        Usager u = optionalUsager.get();
        Livre l = optionalLivre.get();

        u.setEmprunt(l);
        db_user.save(u);

        return ResponseEntity.ok().build();
    }



    @PostMapping("/usager/{idUsager}/rend")
    public ResponseEntity rendre(@PathVariable(name = "idUsager") Long idUsager){

        Optional<Usager> optionalUsager = db_user.findById(idUsager);
        if(optionalUsager.isEmpty())
            return ResponseEntity.notFound().build();

        Usager u = optionalUsager.get();

        u.setEmprunt(null);
        db_user.save(u);

        return ResponseEntity.ok().build();
    }





}
