package com.smartclinic.webapp.messaging;

import java.io.Serializable;
import java.time.LocalDateTime;

public class AppointmentMessage implements Serializable {

    private Long appointmentId;
    private String patientName;
    private LocalDateTime dateTime;

    public AppointmentMessage(Long appointmentId, String patientName, LocalDateTime dateTime) {
        this.appointmentId = appointmentId;
        this.patientName = patientName;
        this.dateTime = dateTime;
    }

    public Long getAppointmentId() { return appointmentId; }
    public String getPatientName() { return patientName; }
    public LocalDateTime getDateTime() { return dateTime; }
}