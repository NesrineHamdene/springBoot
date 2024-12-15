package com.poly.nessrine.gestionPatients.services;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import com.poly.nessrine.gestionPatients.repository.MedecinRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@AllArgsConstructor
public class servicesMedecin implements IMedecinservices{
    private MedecinRepository medecinRepository;

    @Override
    public  List<Medecin> getAllMedecin() {
        return medecinRepository.findAll();
    }

    @Override
    public Medecin findMedecinById(Long medecinId) {
        return medecinRepository.findById(medecinId).orElse(null);
    }

    @Override
    public Medecin save(Medecin medecin) {
        return medecinRepository.save(medecin);
    }



}