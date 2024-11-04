package com.poly.nessrine.gestionPatients.repository;

import com.poly.nessrine.gestionPatients.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PatientRepository extends JpaRepository<Patient, Long> {
    //je vais chercher un patient par son nom

    public Page<Patient> findByNameContains(String search, Pageable pageable);


    Optional<Patient> findByName(String name);

}
