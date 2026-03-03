package com.smartclinic.webapp.messaging;

import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class AppointmentProducer {

    private final JmsTemplate jmsTemplate;

    public AppointmentProducer(JmsTemplate jmsTemplate) {
        this.jmsTemplate = jmsTemplate;
    }

    public void sendAppointmentCreated(String patientName, String date) {

        String message = "New appointment: " + patientName + " on " + date;

        jmsTemplate.convertAndSend("appointments.queue", message);
    }
}
