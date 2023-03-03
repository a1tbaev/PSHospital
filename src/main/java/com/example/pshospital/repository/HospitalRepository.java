package com.example.pshospital.repository;

import com.example.pshospital.models.Hospital;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface HospitalRepository extends JpaRepository<Hospital, Long> {
    @Query("select h from Doctor d join d.hospital h where d.id = :doctorId")
    Hospital getHospitalByDoctorId(Long doctorId);

    @Query("select h from Department d join d.hospital h on d.id = :departmentId")
    Hospital findHospitalByDepartmentId(Long departmentId);
}
