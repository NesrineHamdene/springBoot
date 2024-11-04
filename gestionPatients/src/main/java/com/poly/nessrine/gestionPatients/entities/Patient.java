package com.poly.nessrine.gestionPatients.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Builder
public class Patient {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String phone;
    private String nomImage;

    @ManyToMany

    @JoinTable(
            name = "patient_medecin",
            joinColumns = @JoinColumn(name = "patient_id"),
            inverseJoinColumns = @JoinColumn(name = "medecin_id")
    )
    private List<Medecin> medecins;

    public void setImage(String imageName) {

    }


    //public Patient(Object id, String name, String surname, String phone, Date birthDate) {
    //}
}
