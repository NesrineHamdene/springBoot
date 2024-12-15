package com.poly.nessrine.gestionPatients.services;

import com.poly.nessrine.gestionPatients.entities.Medecin;

import java.util.List;

public interface IMedecinservices {
public List<Medecin> getAllMedecin();


   public Medecin findMedecinById(Long medecinId);

   public Medecin save(Medecin medecin);
}
