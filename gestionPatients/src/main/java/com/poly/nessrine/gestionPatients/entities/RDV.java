package com.poly.nessrine.gestionPatients.entities;

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
    @ManyToOne
    @JoinColumn(name = "patient_id")
    private Patient patient;
    @ManyToOne
    @JoinColumn(name = "medecin_id")
    private Medecin medecin;
    private String image;
    private String patientName;




}