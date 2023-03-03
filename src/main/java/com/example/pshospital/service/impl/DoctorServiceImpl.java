package com.example.pshospital.service.impl;

import com.example.pshospital.models.Appointment;
import com.example.pshospital.models.Department;
import com.example.pshospital.models.Doctor;
import com.example.pshospital.models.Hospital;
import com.example.pshospital.repository.AppointmentRepository;
import com.example.pshospital.repository.DepartmentRepository;
import com.example.pshospital.repository.DoctorRepository;
import com.example.pshospital.service.DoctorService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DoctorServiceImpl implements DoctorService {
    private final DepartmentRepository departmentRepository;
    private final DoctorRepository doctorRepository;
    private final AppointmentRepository appointmentRepository;
    @Override
    public List<Doctor> getAllDoctors(Long departmentId) {
        return departmentRepository.findById(departmentId).get().getDoctors();
    }

    @Override
    public void saveDoctor(Doctor doctor, Long departmentId) {
        Department department = departmentRepository.findById(departmentId).get();
        doctorRepository.save(doctor);
        department.addDoctor(doctor);
    }

    @Override
    public Doctor getDoctorById(Long doctorId) {
        return doctorRepository.findById(doctorId).get();
    }

    @Override
    public void deleteDoctor(Long doctorId) {
        Doctor doctor = doctorRepository.findById(doctorId).get();
        Hospital hospital = doctor.getHospital();
        List<Appointment> appointments = doctor.getAppointments();
        appointments.forEach(a-> a.setDoctor(null));
        appointments.forEach(a-> a.setPatient(null));
        appointments.forEach(a-> a.setDepartment(null));
        hospital.getAppointments().removeAll(appointments);
        for (Appointment appointment : appointments) {
            appointmentRepository.deleteById(appointment.getId());
        }
        doctorRepository.deleteById(doctorId);
    }

    @Override
    public void updateDoctor(Doctor doctor, Long doctorId) {
        Doctor doctor1 = doctorRepository.findById(doctorId).get();
        doctor1.setFirstName(doctor.getFirstName());
        doctor1.setLastName(doctor.getLastName());
        doctor1.setPosition(doctor.getPosition());
    }
}
