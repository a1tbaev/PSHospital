package com.example.pshospital.service;

import com.example.pshospital.models.Appointment;
import com.example.pshospital.models.Patient;

import java.util.List;

public interface AppointmentService {
    void saveAppointment(Patient patient, Appointment appointment, Long id);
    Appointment getById(Long appointmentId);
    List<Appointment> getAllAppointments(Long doctorsId);
    void deleteAppointments(Long doctorId, Long appointmentsId);
    void updateAppointments(Long appointmentId, Appointment appointment);
}
