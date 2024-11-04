package com.poly.nessrine.gestionPatients.services;

import com.poly.nessrine.gestionPatients.entities.Patient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface IPatientservices {
    public void savePatient(Patient patient,MultipartFile file) throws Exception; //ajouter un patient
    public Page<Patient> recherchePatientsParName(String search, Pageable pageable);
    public Patient getPatient(Long id);// trouver un patitn par son idée.
    public void editPatient(Long id,Patient patient, MultipartFile image) throws Exception;
    public void deletePatientById(Long id);
    public List<Patient> findAllPatients();

    public Patient findPatientByName(String patientName);


    //public String savePatientWithImage(MultipartFile file) throws IOException;
}
