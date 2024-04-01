package kg.Kursanov.service;

import kg.Kursanov.models.Department;

import java.util.List;

public interface DepartmentService {

    List<Department> getAllDepartmentByHospital(int id);

    Department findDepartmentByName(String name);
}
