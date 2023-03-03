package com.example.pshospital.repository;

import com.example.pshospital.models.Appointment;
import com.example.pshospital.models.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    List<Patient> getPatientsByAppointments(List<Appointment> appointment);
}
