package kg.Kursanov.service;

import kg.Kursanov.models.Doctor;

import java.util.List;

public interface DoctorService {

    Doctor findDoctorById(int id);

    String assignDoctorToDepartment(int departmentId, List<Integer> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(int id);

    List<Doctor> getAllDoctorsByDepartmentId(int id);
}
