package com.poly.nessrine.gestionPatients.api;

import com.poly.nessrine.gestionPatients.entities.Patient;
import com.poly.nessrine.gestionPatients.services.servicesPatient; // Importation de la classe de service
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api")
@AllArgsConstructor
public class RestControllerPatient {

    private  servicesPatient servicespatient;

    /*@GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = servicespatient.findAllPatients();
        return ResponseEntity.ok(patients);
    }*/

    /*@GetMapping("/{id}")
    public ResponseEntity<Patient> getPatient(@PathVariable Long id) {
        Patient patient = servicespatient.getPatient(id);
        return patient != null ? ResponseEntity.ok(patient) : ResponseEntity.notFound().build();
    }*/

    @PostMapping
    public ResponseEntity<Patient> addPatient(@RequestParam("patient") Patient patient,
                                              @RequestParam("file") MultipartFile file) {
        try {
            servicespatient.savePatient(patient, file);
            return ResponseEntity.status(HttpStatus.CREATED).body(patient);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id,
                                                 @RequestParam("patient") Patient patient,
                                                 @RequestParam(value = "file", required = false) MultipartFile file) {
        try {
            servicespatient.editPatient(id, patient, file);
            return ResponseEntity.ok(patient);
        } catch (IOException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatient(@PathVariable Long id) {
        servicespatient.deletePatientById(id);
        return ResponseEntity.noContent().build();
    }
}
