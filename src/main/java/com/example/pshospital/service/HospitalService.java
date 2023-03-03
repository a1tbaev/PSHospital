package com.example.pshospital.service;

import com.example.pshospital.models.Hospital;

import java.util.List;
import java.util.Optional;


public interface HospitalService {
    void save(Hospital hospital);
    List<Hospital> hospitals();
    Hospital getHospitalById(Long id);
    void deleteHospitalByID(Long id);
    void updateHospitalById(Long id, Hospital hospital);
}
