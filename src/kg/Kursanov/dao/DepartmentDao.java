package kg.Kursanov.dao;

import kg.Kursanov.db.Database;
import kg.Kursanov.models.Department;

import java.util.List;

public interface DepartmentDao {

    Boolean add(int hospitalId, Department department);

    String remove(int id);

    List<Department> getAll(int hospitalId);

    List<Department> getAllDepartments();

    String update(int id, Department department);

    Department findDepartmentByName(String name);


}





