package com.example.pshospital.service.impl;

import com.example.pshospital.models.Appointment;
import com.example.pshospital.models.Patient;
import com.example.pshospital.repository.AppointmentRepository;
import com.example.pshospital.repository.PatientRepository;
import com.example.pshospital.service.PatientService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class PatientServiceImpl implements PatientService {
    private final PatientRepository patientRepository;
    private final AppointmentRepository appointmentRepository;
    @Override
    public Patient getAllPatients(Long appointmentId) {
        return patientRepository.getPatientByAppointmentId(appointmentId);
    }

    @Override
    public void savePatient(Long appointmentId, Patient newPatient) {
        patientRepository.save(newPatient);
        appointmentRepository.findById(appointmentId).get().setPatient(newPatient);
    }

    @Override
    public Patient findById(Long patientId) {
        return patientRepository.findById(patientId).get();
    }

    @Override
    public void deletePatient(Long patientId, Long appointmentId) {
        List<Appointment> appointments = patientRepository.findById(patientId).get().getAppointments();
        List<Appointment> patAppointments = new ArrayList<>();

        for (Appointment appointment : appointments) {
            if (appointment.getPatient().getId().equals(patientId)){
                patAppointments.add(appointment);
            }
        }
        patAppointments.forEach(p -> p.setPatient(null));
        patientRepository.delete(patientRepository.findById(patientId).get());
    }

    @Override
    public void updatePatient(Long patientId, Patient patient) {
        Patient patient1 = patientRepository.findById(patientId).get();
        patient1.setFirstName(patient.getFirstName());
        patient1.setLastName(patient.getLastName());
        patient1.setEmail(patient.getEmail());
        patient1.setGender(patient.getGender());
        patient1.setPhoneNumber(patient.getPhoneNumber());
    }
}
