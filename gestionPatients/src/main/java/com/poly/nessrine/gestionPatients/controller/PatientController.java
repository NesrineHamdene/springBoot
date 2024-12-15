package com.poly.nessrine.gestionPatients.controller;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import com.poly.nessrine.gestionPatients.entities.Patient;
import com.poly.nessrine.gestionPatients.repository.MedecinRepository;
import com.poly.nessrine.gestionPatients.repository.PatientRepository;
import com.poly.nessrine.gestionPatients.services.IMedecinservices;
import com.poly.nessrine.gestionPatients.services.IPatientservices;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@Controller
@AllArgsConstructor
public class PatientController {
    private IPatientservices patientservices; //mon controller a besoin d acceder à la base de donnée
    private IMedecinservices medecinservices;
    private MedecinRepository medecinRepository;

        //Je vais afficher la liste des patients tawa
        @GetMapping(path = {"/liste"})
        public String listPatients(Model model,
                                   @RequestParam(name= "page", defaultValue = "1") int page,
                                   @RequestParam(name= "size", defaultValue = "2") int size,
                                   @RequestParam(name= "search", defaultValue = "") String search) {

            Page<Patient> listePage = patientservices.recherchePatientsParName(search, PageRequest.of(page- 1, size));

            // Ajouter les attributs au modèle
            model.addAttribute("current", listePage.getNumber() + 1);
            model.addAttribute("pages", new int[listePage.getTotalPages()]);
            model.addAttribute("patients", listePage.getContent()); // n7ot e liste fil modèle
            model.addAttribute("search", search);

            return "/liste";
        }



       /*@GetMapping("/recherche")
    public String savePatient(Model model) {
            List<Patient> patients=patientservices.recherchePatientsParName("",);
            model.addAttribute("patients", patients);
            return "/recherche";
       }*/

       @GetMapping("/delete/{id}")
      public String deletePatient(@PathVariable Long id) {
            patientservices.deletePatientById(id);
            return "redirect:/liste";

       }
    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable Long id, Model model) {
        Patient p = patientservices.getPatient(id);
        if (p == null) {
            // Gérer le cas où le patient n'existe pas
            throw new RuntimeException("Patient non trouvé pour l'ID : " + id);
        }
        model.addAttribute("patient", p);
        return "modifier";
    }

    //bch n enregisti les modifications
    @PostMapping("/saveEdit/{id}")
    public String updatePatient( @ModelAttribute Patient patient, @PathVariable Long id,
                                 @RequestParam("file") MultipartFile multipartFile) throws Exception {

        patient.setId(id);
        patientservices.editPatient(id,patient,multipartFile);
        return "redirect:/liste";
    }

    @GetMapping("/add") //Accéder à la page d'ajout de manière indépendante
    public String addPatient(Model model) {
            Patient p = new Patient();
            model.addAttribute("patient", p);
            model.addAttribute("medecin",medecinservices.getAllMedecin());
            return "Ajouter";
       }


       @PostMapping("/save")
    public String enregistrer(@ModelAttribute Patient patient , @RequestParam("file") MultipartFile file) throws Exception {
        // Enregistrer le patient dans la base de données
        patientservices.savePatient(patient,file);
        return "redirect:/liste"; // Redirige vers la liste des patients après l ajout
    }
    @GetMapping("/ajouter-medecin")
    public String afficherFormulaireMedecin(Model model) {
        model.addAttribute("medecin", new Medecin()); // Crée un objet Medecin pour le formulaire
        return "ajouter-medecin";
    }
    @PostMapping("/ajouterMedecin")
    public String ajouterMedecin(@ModelAttribute Medecin medecin) {
        medecinRepository.save(medecin); // Sauvegarde le médecin dans la base de données
        return "redirect:/liste"; // Redirige vers la liste des patients après l'ajout
    }


}

