package com.example.pshospital.repository;

import com.example.pshospital.models.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    List<Department> findDepartmentsByHospitalId(Long hospitalId);
}
