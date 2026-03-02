package com.smartclinic.webapp.repository;

import com.smartclinic.webapp.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends  JpaRepository<Appointment , Long> {
}
