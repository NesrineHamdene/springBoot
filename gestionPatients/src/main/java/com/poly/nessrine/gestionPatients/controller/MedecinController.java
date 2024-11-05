package com.poly.nessrine.gestionPatients.controller;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import com.poly.nessrine.gestionPatients.repository.MedecinRepository;
import com.poly.nessrine.gestionPatients.services.IMedecinservices;
import com.poly.nessrine.gestionPatients.services.IRDVservices;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

public class MedecinController {
    private IRDVservices servicesRDV;
    private IMedecinservices medecinservices;

    @GetMapping("/rdvs")
    public String afficherGestionRDV(Model model) {
        model.addAttribute("rdvs", servicesRDV.findAllRDVs());
        model.addAttribute("medecins", medecinservices.getAllMedecin());
        return "gestion-rdv";
    }


}


