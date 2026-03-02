package com.smartclinic.webapp.model;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "appointments")

public class Appointment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String patientName;

    private LocalDate date;

    private String status;

    public Appointment() {};

    public Appointment(String patientName, LocalDate date, String status){
        this.patientName = patientName;
        this.date = date;
        this.status = status;
    }

    public Long getId() {return id; }

    public String getPatientName() {return patientName;}
    public void setPatientName(String patientName) { this.patientName = patientName; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}
