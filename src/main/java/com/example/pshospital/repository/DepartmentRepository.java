package com.example.pshospital.repository;

import com.example.pshospital.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepartmentsByHospitalId(Long hospitalId);
    @Query("select de from Doctor d join d.departments de where d.id = :doctorId")
    Department findDepartmentByDoctorId(Long doctorId);
}
