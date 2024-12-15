package com.poly.nessrine.gestionPatients.controller;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import com.poly.nessrine.gestionPatients.entities.Patient;
import com.poly.nessrine.gestionPatients.entities.RDV;
import com.poly.nessrine.gestionPatients.repository.PatientRepository;
import com.poly.nessrine.gestionPatients.repository.RDVRepository;
import com.poly.nessrine.gestionPatients.services.IMedecinservices;
import com.poly.nessrine.gestionPatients.services.IPatientservices;
import com.poly.nessrine.gestionPatients.services.IRDVservices;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Controller

public class RDVController {
    private IPatientservices patientService;
    private IMedecinservices medecinService;
    private IRDVservices servicesRDV;
    private PatientRepository patientRepository;
    private RDVRepository rdvRepository;
    @GetMapping("/rdvs")
    public String afficherGestionRDV(Model model) {

        model.addAttribute("patients", patientService.findAllPatients()); // Assurez-vous d'avoir un service pour récupérer tous les patients
        model.addAttribute("rdvs", servicesRDV.findAllRDVs());
        model.addAttribute("medecins", medecinService.getAllMedecin()); // Récupère tous les médecins// Assurez-vous d'avoir un service pour récupérer tous les RDVs
        return "gestion-rdv";
    }
    @PostMapping("/rdvs")
    public String enregistrerRendezVous(@RequestParam("patientName") String patientName, // Récupérez le nom du patient
                                        @RequestParam("medecinId") Long medecinId,
                                        @RequestParam("date") LocalDateTime date) {
        // Créez un nouvel objet RDV
        RDV nouveauRdv = new RDV();

        // Assignez le nom du patient à nouveauRdv
        Patient patient = patientService.findPatientByName(patientName);
        if (patient == null) {
            // Créer un nouveau patient si pas trouvé (ou gérer autrement)
            patient = new Patient();
            patient.setName(patientName);
            patientRepository.save(patient); // Sauvegarder le nouveau patient
        }
        nouveauRdv.setPatient(patient);

        // Récupérez le médecin à partir du service
        Medecin medecin = medecinService.findMedecinById(medecinId);
        nouveauRdv.setMedecin(medecin);

        // Conversion de LocalDateTime à Date
        nouveauRdv.setDate(Date.from(date.atZone(ZoneId.systemDefault()).toInstant()));

        // Enregistrez le rendez-vous
        servicesRDV.enregistrerRendezVous(nouveauRdv);

        return "redirect:/rdvs";
    }


    @PostMapping("/creer/{medecinId}")
    public ResponseEntity<RDV> creerRendezVous(@PathVariable Long medecinId, @RequestBody RDV rdv) {
        try {
            // Appel du service pour créer un nouveau rendez-vous
            RDV nouveauRendezVous = servicesRDV.creerRendezVous(medecinId, rdv);
            return new ResponseEntity<>(nouveauRendezVous, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            // Gérer le cas où le médecin n'est pas trouvé
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/rdvs/delete/{id}")
    public String annulerRdv(@PathVariable Long id) {
        rdvRepository.deleteById(id);
        return "redirect:/rdvs";
    }
    @GetMapping("/rdvs/details/{id}")
    public String getRdvDetails(@PathVariable Long id, Model model) {
        Optional<RDV> rdv = rdvRepository.findById(id);
        if (rdv.isPresent()) {
            model.addAttribute("rdv", rdv.get());
            return "details-rdv";
        } else {
            // Redirection ou affichage d'un message d'erreur si l'ID est invalide
            return "redirect:/rdvs?error=RendezVousNotFound";
        }
    }



}
