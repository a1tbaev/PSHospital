package com.example.pshospital.service;

import com.example.pshospital.models.Patient;

import java.util.List;

public interface PatientService {
    Patient getAllPatients(Long appointmentId);
    void savePatient(Long appointmentId, Patient newPatient);
    Patient findById(Long patientId);
    void deletePatient(Long patientId, Long appointmentId);
    void updatePatient(Long patientId, Patient patient);
}
