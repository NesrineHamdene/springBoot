package com.poly.nessrine.gestionPatients.api;

import com.poly.nessrine.gestionPatients.entities.Patient;
import com.poly.nessrine.gestionPatients.services.servicesPatient;
import jakarta.annotation.Resource;
import lombok.AllArgsConstructor;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
@CrossOrigin(origins = "http://localhost:3000")  // Permet les requêtes de ton frontend
@RestController
@RequestMapping("/api/patients")
@AllArgsConstructor
public class RestControllerPatient {

    private  servicesPatient servicesPatient;

    // Récupérer la liste des patients
    @GetMapping
    public List<Patient> getAllPatients() {
        return servicesPatient.findAllPatients();
    }

    // Ajouter un patient
    @PostMapping
    public Patient addPatient(@RequestBody Patient patient, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        servicesPatient.savePatient(patient, multipartFile);
        return patient;
    }

    // Modifier un patient
    @PutMapping("/{id}")
    public Patient updatePatient(@PathVariable Long id, @RequestBody Patient patient, @RequestParam("image") MultipartFile multipartFile) throws IOException {
        servicesPatient.editPatient(id, patient, multipartFile);
        return patient;
    }

    // Supprimer un patient
    @DeleteMapping("/{id}")
    public void deletePatient(@PathVariable Long id) {
        servicesPatient.deletePatientById(id);
    }

    // Récupérer un patient par son nom
    @GetMapping("/search")
    public Patient getPatientByName(@RequestParam String name) {
        return servicesPatient.findPatientByName(name);
    }
    @GetMapping("/images/{filename}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        try {
           
            Path path = Paths.get("static.photos").resolve(filename);
            Resource resource = (Resource) new UrlResource(path.toUri());
            return ResponseEntity.ok().body(resource);
        } catch (MalformedURLException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


}



