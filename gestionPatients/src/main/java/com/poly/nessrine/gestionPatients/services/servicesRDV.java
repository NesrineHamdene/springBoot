package com.poly.nessrine.gestionPatients.services;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import com.poly.nessrine.gestionPatients.entities.RDV;
import com.poly.nessrine.gestionPatients.repository.MedecinRepository;
import com.poly.nessrine.gestionPatients.repository.RDVRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@AllArgsConstructor
public class servicesRDV implements IRDVservices{

    private MedecinRepository medecinRepository;
    private RDVRepository rdvRepository;

    @Override
    public RDV creerRendezVous(Long medecinId,  RDV rdv) {
        Optional<Medecin> medecinOptional = medecinRepository.findById(medecinId);
        if (medecinOptional.isPresent()) {
            Medecin medecin = medecinOptional.get();
            rdv.setMedecin(medecin);
            return rdvRepository.save(rdv); //pour garder l obejet j ai utilisée repository
        }
        throw new RuntimeException("Médecin non trouvé");
    }

    @Override
    public List<RDV> findAllRDVs() {
        return rdvRepository.findAll();// recuperina tous les rdv
    }

    @Override
    public void enregistrerRendezVous(RDV nouveauRdv) {

        rdvRepository.save(nouveauRdv); // Enregistre le rendez-vous dans la base de données
    }

}
