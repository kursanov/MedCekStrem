package kg.Kursanov.service.impl;

import kg.Kursanov.dao.HospitalDao;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.models.Hospital;
import kg.Kursanov.models.Patient;
import kg.Kursanov.service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {

    private final HospitalDao hospitalDao;

    public HospitalServiceImpl(HospitalDao hospitalDao) {
        this.hospitalDao = hospitalDao;
    }

    @Override
    public String addHospital(Hospital hospital) {
        try {
            hospitalDao.add(hospital);
            return "successfully added";
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public Hospital findHospitalById(int id) {
        try {
            return hospitalDao.findById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAll();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(int id) {
        try {
            return hospitalDao.getAllPatientFromHospital(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public String deleteHospitalById(int id) {
        try {
            return hospitalDao.delete(id);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        try {
            return hospitalDao.getAllHospitalByAddress(address);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }
}
