package com.example.pshospital.service;

import com.example.pshospital.models.Department;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DepartmentService {
    Department save(Department department, Long id);
    List<Department> getAll(Long id);
    void deleteById(Long id);
    Department getById(Long id);
    void update (Long id, Department newDepartment);
}
