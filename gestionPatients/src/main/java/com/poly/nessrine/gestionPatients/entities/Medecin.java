package com.poly.nessrine.gestionPatients.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Builder

public class Medecin {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String surname;
    private String specialty;
    private String numeroTelephone;
    @JsonIgnore
@ManyToMany(mappedBy = "medecins")
    private List<Patient> patients;
    @JsonIgnore
    @OneToMany(mappedBy = "medecin", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RDV> rendezVous;
}
