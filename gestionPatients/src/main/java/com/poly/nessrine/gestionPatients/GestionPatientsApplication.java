package com.poly.nessrine.gestionPatients;

import com.poly.nessrine.gestionPatients.entities.Medecin;
import com.poly.nessrine.gestionPatients.entities.Patient;
import com.poly.nessrine.gestionPatients.repository.MedecinRepository;
import com.poly.nessrine.gestionPatients.repository.PatientRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@SpringBootApplication
public class GestionPatientsApplication {
	private final MedecinRepository medecinRepository;

	public GestionPatientsApplication(MedecinRepository medecinRepository) {
		this.medecinRepository = medecinRepository;
	}

	//@Bean
	CommandLineRunner commandLineRunner(PatientRepository patientRepository){
		return args -> {
			//Patient patient1 = new Patient(null, "hassen", "ben moussa", "0123456789" );
			//Patient patient2 = new Patient(null, "Bob", "Dupont", "0987654321");

			//patientRepository.save(patient1);
			//patientRepository.save(patient2);
			//patientRepository.findAll().forEach(patient -> {System.out.println(patient.getName());});
			Patient patient1 = Patient.builder().name("Hassen").surname("ben moussa").phone("03356475212").build();
			Patient patient2 = Patient.builder().name("Ahmed").surname("hamdene").phone("03356475212").build();
			Patient patient3 = Patient.builder().name("Rihab").surname("ben salem").phone("03356475212").build();

			patientRepository.save(patient1);
			patientRepository.save(patient2);
			patientRepository.save(patient3);

			Medecin medecin1 = Medecin.builder().name("Aymen").surname("Ben Moussa").specialty("Cardiology").numeroTelephone("52856324").build();
			Medecin medecin2 = Medecin.builder().name("Nessrine").surname("Hamdene").specialty("Dermatology").numeroTelephone("51458763").build();
			Medecin medecin3 = Medecin.builder().name("Rihab").surname("Mathneni").specialty("Pediatric").numeroTelephone("22875421").build();

			medecinRepository.save(medecin1);
			medecinRepository.save(medecin2);
			medecinRepository.save(medecin3);



		};
	}




	public static void main(String[] args) {
		SpringApplication.run(GestionPatientsApplication.class, args);
	}

}
