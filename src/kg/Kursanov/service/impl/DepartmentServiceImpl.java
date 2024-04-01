package kg.Kursanov.service.impl;

import kg.Kursanov.dao.DepartmentDao;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.models.Department;
import kg.Kursanov.service.DepartmentService;
import kg.Kursanov.service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements GenericService<Department>, DepartmentService {

    private final DepartmentDao departmentDao;

    public DepartmentServiceImpl(DepartmentDao departmentDao) {
        this.departmentDao = departmentDao;
    }

    @Override
    public List<Department> getAllDepartmentByHospital(int id) {
        try {
            return departmentDao.getAll(id);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        try {
            return departmentDao.findDepartmentByName(name);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(int hospitalId, Department department) {
        try {
            departmentDao.add(hospitalId, department);
            return "Successfully added";
        } catch (NotFoundException | IllegalArgumentException e){
            return e.getMessage();
        }
    }

    @Override
    public String removeById(int id) {
        try {
            return departmentDao.remove(id);
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }

    @Override
    public String updateById(int id, Department department) {
        try {
            return departmentDao.update(id, department);
        } catch (NotFoundException e){
            return e.getMessage();
        }
}}
