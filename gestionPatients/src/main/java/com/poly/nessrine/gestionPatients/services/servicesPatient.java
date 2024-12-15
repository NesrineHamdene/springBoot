package com.poly.nessrine.gestionPatients.services;
import com.poly.nessrine.gestionPatients.entities.Patient;
import com.poly.nessrine.gestionPatients.repository.PatientRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;


@Service
@AllArgsConstructor
public class servicesPatient implements IPatientservices {
    private PatientRepository patientRepository;

    @Override
    public Page<Patient> recherchePatientsParName(String search, Pageable pageable) {
        return patientRepository.findByNameContains(search, pageable);
    }


    @Override
    public void savePatient(Patient patient,MultipartFile multipartFile) throws IOException {
       if(!multipartFile.isEmpty()){
           patient.setNomImage(saveImage(multipartFile));
       }
        patientRepository.save(patient);
    }

    @Override
    public Patient getPatient(Long id) {
        return patientRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Patient introuvable avec l'ID : " + id));
    }



    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);

    }

    @Override
    public List<Patient> findAllPatients() {
        return patientRepository.findAll();
    }

    @Override
    public Patient findPatientByName(String patientName) {
        return patientRepository.findByName(patientName).orElse(new Patient());  }

    @Override
    public void editPatient(Long id, Patient patient, MultipartFile multipartFile) throws IOException {
        Patient expatient= patientRepository.findById(id).orElse(null);
        expatient.setName(patient.getName());
        expatient.setSurname(patient.getSurname());
        expatient.setPhone(patient.getPhone());


        // Vérifier si une nouvelle image est fournie
        if (multipartFile != null && !multipartFile.isEmpty()) {
            // Gérer le stockage de l'image
            String imageName = saveImage(multipartFile); // Méthode pour enregistrer l'image
            expatient.setImage(imageName); // Assurez-vous que votre Patient a un champ pour l'image
        }
        patientRepository.save(expatient); // Enregistrer les modifications
    }



    public String saveImage(MultipartFile mfile) throws IOException {
        // Récupérer le nom du fichier d'origine
        String fileName = mfile.getOriginalFilename();
        if (fileName == null || !fileName.contains(".")) {
            throw new IllegalArgumentException("Nom de fichier invalide");
        }

        // Générer un nouveau nom pour le fichier
        String[] tab = fileName.split("\\.");
        String newName = tab[0] + System.currentTimeMillis() + "." + tab[1];

        // Spécifier le répertoire de destination
        String directory = "src/main/resources/static/photos"; // Assurez-vous que le chemin est correct et relatif à votre projet
        File fileDir = new File(directory);

        // Créer le répertoire s'il n'existe pas encore
        if (!fileDir.exists()) {
            boolean created = fileDir.mkdirs(); // Crée tous les répertoires nécessaires
            if (!created) {
                throw new IOException("Impossible de créer le répertoire : " + fileDir.getAbsolutePath());
            }
        }

        // Vérifier les autorisations d'écriture
        if (!fileDir.canWrite()) {
            throw new IOException("Le répertoire n'est pas accessible en écriture : " + fileDir.getAbsolutePath());
        }

        // Spécifier le chemin du fichier à sauvegarder
        Path path = Paths.get(fileDir.getAbsolutePath(), newName);

        // Écrire le fichier à l'emplacement spécifié
        Files.write(path, mfile.getBytes());

        // Retourner le nom du nouveau fichier
        return newName;
    }


}