package com.smartclinic.webapp.messaging;

import com.smartclinic.webapp.messaging.AppointmentMessage;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class AppointmentConsumer {

    @JmsListener(destination = "appointments.queue")
    public void receiveMessage(String message) {

        System.out.println("🔥 Message received from queue:");
        System.out.println(message);

        // Here later we update DB, change status, etc.
    }
}