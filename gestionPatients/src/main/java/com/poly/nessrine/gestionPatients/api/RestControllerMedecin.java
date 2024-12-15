package com.poly.nessrine.gestionPatients.api;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import com.poly.nessrine.gestionPatients.services.IMedecinservices;
import com.poly.nessrine.gestionPatients.services.servicesMedecin;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api")
@AllArgsConstructor

public class RestControllerMedecin {
    private servicesMedecin servicesmedecin;

    @GetMapping("/liste-medecins")
    public List<Medecin> getAllMedecins() {
        return servicesmedecin.getAllMedecin();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Medecin> getMedecinById(@PathVariable Long id) {
        Medecin medecin = servicesmedecin.findMedecinById(id);
        return medecin != null ? ResponseEntity.ok(medecin) : ResponseEntity.notFound().build();
    }
    @PostMapping("/medecins")
    public ResponseEntity<Medecin> addMedecin(@RequestBody Medecin medecin) {
        Medecin savedMedecin = servicesmedecin.save(medecin);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMedecin);
    }

}
