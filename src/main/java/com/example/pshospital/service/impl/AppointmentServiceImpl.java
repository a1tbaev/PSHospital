package com.example.pshospital.service.impl;

import com.example.pshospital.models.*;
import com.example.pshospital.repository.AppointmentRepository;
import com.example.pshospital.repository.DepartmentRepository;
import com.example.pshospital.repository.DoctorRepository;
import com.example.pshospital.repository.HospitalRepository;
import com.example.pshospital.service.AppointmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class AppointmentServiceImpl implements AppointmentService {
    private final AppointmentRepository appointmentRepository;
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final HospitalRepository hospitalRepository;
    @Override
    public void saveAppointment(Patient patient, Appointment appointment, Long id) {
        Doctor doctor = doctorRepository.findById(id).get();
        Hospital hospitalByDoctorId = hospitalRepository.getHospitalByDoctorId(id);
        Department departmentByDoctorId = departmentRepository.findDepartmentByDoctorId(id);
        appointmentRepository.save(appointment);
        hospitalByDoctorId.addAppointment(appointment);
        appointment.setDoctor(doctor);
        appointment.setDepartment(departmentByDoctorId);
    }

    @Override
    public Appointment getById(Long appointmentId) {
        return appointmentRepository.findById(appointmentId).get();
    }

    @Override
    public List<Appointment> getAllAppointments(Long doctorsId) {
        return appointmentRepository.findAppointmentByDoctorId(doctorsId);
    }

    @Override
    public void deleteAppointments(Long doctorId, Long appointmentsId) {
        Hospital hospital = doctorRepository.findById(doctorId).get().getHospital();
        Appointment appointment = appointmentRepository.findById(appointmentsId).get();
        appointment.setDoctor(null);
        appointment.setDepartment(null);
        appointment.setPatient(null);
        hospital.getAppointments().remove(appointment);
        appointmentRepository.deleteById(appointmentsId);
    }

    @Override
    public void updateAppointments(Long appointmentId, Appointment appointment) {
//        appointmentRepository.updateAppointments(appointmentId, appointment);
    }
}
