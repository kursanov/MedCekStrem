package kg.Kursanov.dao.impl;

import kg.Kursanov.dao.DepartmentDao;
import kg.Kursanov.db.Database;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.id.GeneratedId;
import kg.Kursanov.models.Department;
import kg.Kursanov.models.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DepartmentDaoImpl implements DepartmentDao {

    private final Database database;

    public DepartmentDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Boolean add(int hospitalId, Department department) {
        boolean baza = database.getAll().stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .anyMatch(department1 -> department1.getDepartmentName().equalsIgnoreCase(department.getDepartmentName()));
        if (baza) {
            throw new NotFoundException("Department with name: +department.getDepartmentName()+ already have");
        }
        Optional<Hospital> first = database.getAll().stream()
                .filter(hospital -> hospital.getId() == (hospitalId)).findFirst();
        if (first.isPresent()) {
            Hospital hospital = first.get();
            department.setId(GeneratedId.idGeneratorDepartment());
            return hospital.getDepartments().add(department);
        }else throw new NotFoundException("NotFound");

}

    @Override
    public String remove(int id) {
        Optional<Department> first = database.getAll().stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId() == (id))
                .findFirst();
        if (first.isPresent()){
            getAllDepartments().remove(first.get());
            return "Successfully deleted";
        }
        else throw new NotFoundException("Department with id: " + id + " not found");
    }

    @Override
    public List<Department> getAll(int hospitalId) {
        Optional<Hospital> first = database.getAll().stream()
                .filter(hospital -> hospital.getId() == hospitalId).findFirst();
        return first.map(Hospital::getDepartments)
                .orElseThrow(() -> new NotFoundException("Hospital with id: " + hospitalId + " not found"));
    }

    @Override
    public List<Department> getAllDepartments() {
        List<Department> allDepartments = new ArrayList<>();
        for (Hospital hospital:database.getAll()) allDepartments.addAll(hospital.getDepartments());
        if (!allDepartments.isEmpty()){
            return allDepartments;
        } else throw new IllegalArgumentException("No departments found in any hospital");
    }

    @Override
    public String update(int id, Department department) {
        Optional<Department> first = getAllDepartments().stream()
                .filter(department1 -> department1.getId() == (id))
                .findFirst();
        if (first.isPresent()) {
            Department hospital = first.get();
            hospital.setDepartmentName(department.getDepartmentName());
            return "Successfully updated";
        } else throw new NotFoundException("Department with id: " + id + " not found!");
    }

    @Override
    public Department findDepartmentByName(String name) {
        return getAllDepartments().stream()
                .filter(department -> department.getDepartmentName().equalsIgnoreCase(name))
                .findFirst().orElseThrow(() -> new NotFoundException("Department with name: " + name + " not found"));
    }
}
