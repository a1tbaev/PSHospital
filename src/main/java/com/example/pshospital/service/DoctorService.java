package com.example.pshospital.service;

import com.example.pshospital.models.Doctor;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public interface DoctorService {
    List<Doctor> getAllDoctors(Long departmentId);
    void saveDoctor(Doctor doctor, Long departmentId);
    Doctor getDoctorById(Long doctorId);
    void deleteDoctor(Long doctorId);
    void updateDoctor(Doctor doctor, Long doctorId);
}
