package kg.Kursanov.service.impl;

import kg.Kursanov.dao.PatientDao;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.models.Patient;
import kg.Kursanov.service.GenericService;
import kg.Kursanov.service.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements GenericService<Patient>, PatientService {

    private final PatientDao patientDao;

    public PatientServiceImpl(PatientDao patientDao) {
        this.patientDao = patientDao;
    }
    @Override
    public String add(int hospitalId, Patient patient) {
        try {
            patientDao.add(hospitalId, patient);
            return "Successfully added";
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String removeById(int id) {
        try {
            return patientDao.delete(id);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String updateById(int id, Patient patient) {
        try {
            return patientDao.updateById(id, patient);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public String addPatientsToHospital(int id, List<Patient> patients) {
        try {
            return patientDao.addPatientsToHospital(id, patients);
        } catch (NotFoundException e) {
            return e.getMessage();
        }
    }

    @Override
    public Patient getPatientById(int id) {
        try {
            return patientDao.findById(id);
        } catch (NotFoundException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return patientDao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        try {
            return patientDao.sortPatientsByAge(ascOrDesc);
        } catch (NotFoundException e){
            System.out.println(e.getMessage());
        }
        return null;
    }
}
