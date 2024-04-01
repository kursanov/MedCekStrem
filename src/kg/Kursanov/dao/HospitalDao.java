package kg.Kursanov.dao;

import kg.Kursanov.models.Hospital;
import kg.Kursanov.models.Patient;

import java.util.List;
import java.util.Map;

public interface HospitalDao {


    Hospital findById(int hospitalId);

    Boolean add(Hospital hospital);

    String delete(int id);

    List<Hospital> getAll();

    List<Patient> getAllPatientFromHospital(int id);

    Map<String, Hospital> getAllHospitalByAddress (String address);



}
