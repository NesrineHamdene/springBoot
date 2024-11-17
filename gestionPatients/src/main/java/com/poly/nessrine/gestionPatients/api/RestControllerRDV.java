package com.poly.nessrine.gestionPatients.api;

import com.poly.nessrine.gestionPatients.entities.RDV;
import com.poly.nessrine.gestionPatients.services.servicesRDV;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestControllerRDV {
    private servicesRDV service;

    // Récupérer tous les rendez-vous
    @GetMapping
    public ResponseEntity<List<RDV>> getAllRDVs() {
        List<RDV> rdvs = service.findAllRDVs();
        return ResponseEntity.ok(rdvs);
    }

    // Créer un nouveau rendez-vous pour un médecin spécifique
    @PostMapping("/medecin/{medecinId}")
    public ResponseEntity<RDV> createRDV(@PathVariable Long medecinId, @RequestBody RDV rdv) {
        try {
            RDV nouveauRDV = service.creerRendezVous(medecinId, rdv);
            return ResponseEntity.status(HttpStatus.CREATED).body(nouveauRDV);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); // Médecin non trouvé
        }
    }

    /*// Enregistrer un rendez-vous existant (mettre à jour)
    @PutMapping("/{id}")
    public String updateRDV(@PathVariable Long id, @RequestBody RDV rdv) {
        service.enregistrerRendezVous(rdv);
        return "RDV modifié avec succès";
    }*/

}
