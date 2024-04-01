package kg.Kursanov.dao;

import kg.Kursanov.models.Doctor;

import java.util.List;

public interface DoctorDao {


    Doctor findById(int doctorId);

    String add(int hospitalId, Doctor doctor);

    String remove(int id);

    List<Doctor> getAll();

    String assignDoctorToDepartment(int departmentId, List<Integer> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(int id);

    List<Doctor> getAllDoctorsByDepartmentId(int id);

    String updateById(int id, Doctor doctor);
}
