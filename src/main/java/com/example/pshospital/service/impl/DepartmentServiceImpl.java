package com.example.pshospital.service.impl;
import com.example.pshospital.models.Appointment;
import com.example.pshospital.models.Department;
import com.example.pshospital.models.Hospital;
import com.example.pshospital.repository.AppointmentRepository;
import com.example.pshospital.repository.DepartmentRepository;
import com.example.pshospital.repository.HospitalRepository;
import com.example.pshospital.service.DepartmentService;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
@RequiredArgsConstructor
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    private final DepartmentRepository departmentRepo;
    private final AppointmentRepository appointmentRepository;
    private final HospitalRepository hospitalRepository;

    @Override
    public Department save(Department department, Long id) {
        Department save = departmentRepo.save(department);
        hospitalRepository.findById(id).get().addDepartment(save);
        return save;
    }

    @Override
    public List<Department> getAll(Long id) {
        return departmentRepo.findDepartmentsByHospitalId(id);
    }

    @Override
    public void deleteById(Long departmentId) {
        Hospital hospital = departmentRepo.getById(departmentId).getHospital();
        List<Appointment> appointments = hospital.getAppointments();
        List<Appointment> depAppointments = new ArrayList<>();
        for (Appointment appointment : appointments) {
            if (appointment.getDepartment().getId().equals(departmentId)) {
                depAppointments.add(appointment);
            }
        }
        depAppointments.forEach(a->a.setDepartment(null));
        depAppointments.forEach(a->a.setPatient(null));
        depAppointments.forEach(a->a.setDoctor(null));
        hospital.getAppointments().removeAll(depAppointments);
        for (Appointment depAppointment : depAppointments) {
            appointmentRepository.deleteById(depAppointment.getId());
        }
        departmentRepo.deleteById(departmentId);
    }
    @Override
    public Department getById(Long id) {
        return departmentRepo.getById(id);
    }

    @Override
    public void update(Long id, Department newDepartment) {
        Department department = departmentRepo.findById(id).get();
        department.setName(newDepartment.getName());
    }
}
