package com.poly.nessrine.gestionPatients.repository;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedecinRepository extends JpaRepository<Medecin, Long> {
}
