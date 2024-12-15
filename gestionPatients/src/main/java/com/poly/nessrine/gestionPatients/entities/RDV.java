package com.poly.nessrine.gestionPatients.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Data
@NoArgsConstructor
@ToString
@AllArgsConstructor
@Entity
@Builder

public class RDV {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Date date;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
    private String image;
    private String patientName;




}