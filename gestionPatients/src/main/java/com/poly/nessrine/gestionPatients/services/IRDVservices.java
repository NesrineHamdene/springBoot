package com.poly.nessrine.gestionPatients.services;

import com.poly.nessrine.gestionPatients.entities.RDV;
import com.poly.nessrine.gestionPatients.repository.RDVRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service

public interface IRDVservices {

     public RDV creerRendezVous(Long medecinId, RDV rdv);
     public List<RDV> findAllRDVs();

     public void enregistrerRendezVous(RDV nouveauRdv);
}
