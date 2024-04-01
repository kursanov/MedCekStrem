package kg.Kursanov.dao;

import kg.Kursanov.models.Patient;

import java.util.List;
import java.util.Map;

public interface PatientDao {


    Patient findById(int patientId);

    Boolean add(int hospitalId, Patient patient);

    String delete(int id);

    List<Patient> getAll();

    String updateById(int id, Patient patient);

    String addPatientsToHospital(int id, List<Patient> patients);

    Map<Integer, List<Patient>> getPatientByAge();

    List<Patient> sortPatientsByAge(String ascOrDesc);
}
