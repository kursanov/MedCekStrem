package kg.Kursanov.service.impl;

import kg.Kursanov.dao.DoctorDao;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.models.Doctor;
import kg.Kursanov.service.DoctorService;
import kg.Kursanov.service.GenericService;

import java.util.List;

public class DoctorServiceImpl implements GenericService<Doctor>, DoctorService {

    private final DoctorDao doctorDao;

    public DoctorServiceImpl(DoctorDao doctorDao) {
        this.doctorDao = doctorDao;
    }


    @Override
    public Doctor findDoctorById(int id) {
        try {
            return doctorDao.findById(id);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(int departmentId, List<Integer> doctorsId) {
        try {
            return doctorDao.assignDoctorToDepartment(departmentId, doctorsId);
        } catch (NotFoundException | IllegalArgumentException e){
            return e.getMessage();
        }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(int id) {
        try {
            return doctorDao.getAllDoctorsByHospitalId(id);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(int id) {
        try {
            return doctorDao.getAllDoctorsByDepartmentId(id);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String add(int hospitalId, Doctor doctor) {
        try {
            return doctorDao.add(hospitalId, doctor);
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }

    @Override
    public String removeById(int id) {
        try {
            return doctorDao.remove(id);
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }

    @Override
    public String updateById(int id, Doctor doctor) {
        try {
            return doctorDao.updateById(id, doctor);
        } catch (NotFoundException e){
            return e.getMessage();
        }
    }
}
