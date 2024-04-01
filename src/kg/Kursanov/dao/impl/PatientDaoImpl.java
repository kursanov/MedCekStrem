package kg.Kursanov.dao.impl;

import kg.Kursanov.dao.PatientDao;
import kg.Kursanov.db.Database;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.id.GeneratedId;
import kg.Kursanov.models.Hospital;
import kg.Kursanov.models.Patient;

import java.util.*;
import java.util.stream.Collectors;

public class PatientDaoImpl implements PatientDao {

    private final Database database;

    public PatientDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Patient findById(int patientId) {
        return getAll().stream()
                .filter(patient -> patient.getId() == patientId).findFirst()
                .orElseThrow(() -> new NotFoundException("Patient with id: " + patientId + " not found"));
    }

    @Override
    public Boolean add(int hospitalId, Patient patient) {
        patient.setId(GeneratedId.idGeneratorPatient());
        Optional<Hospital> first = database.getAll().stream()
                .filter(hospital -> hospital.getId() == hospitalId).findFirst();
        return first.map(hospital -> hospital.getPatients().add(patient))
                .orElseThrow(() -> new NotFoundException("Hospital with id: " + hospitalId + " not found"));
    }

    @Override
    public String delete(int id) {
        List<Patient> patients = getAll();
        Optional<Patient> patientOptional = patients.stream()
                .filter(patient -> patient.getId() == id).findFirst();
        if (patientOptional.isPresent()){
            patients.remove(patientOptional.get());
            return "Succsses deleted";

        }else throw new NotFoundException("Patient with id: " + id + " not found");
    }

    @Override
    public List<Patient> getAll() {
        List<Patient> patients = new ArrayList<>();
        for (Hospital
                hospital: database.getAll()) {
            patients.addAll(hospital.getPatients());
        }
        if (!patients.isEmpty()) return patients;
        else throw new NotFoundException("No patients found in any hospital");
    }

    @Override
    public String updateById(int id, Patient patient) {
        Optional<Patient> first = getAll().stream()
                .filter(patient1 -> patient1.getId() == id).findFirst();
        if (first.isPresent()){
            Patient patient1 = first.get();
            patient1.setFirstName(patient.getFirstName());
            patient1.setLastName(patient.getLastName());
            patient1.setAge(patient.getAge());
            patient1.setGender(patient.getGender());
            return "Successfully updated";
        }else throw new NotFoundException("Patient with id: " + id + " not found");
    }

    @Override
    public String addPatientsToHospital(int id, List<Patient> patients) {
        patients.forEach(patient -> patient.setId(GeneratedId.idGeneratorPatient()));
        Optional<Hospital> hospitalOptional = database.getAll().stream()
                .filter(hospital -> hospital.getId() == (id))
                .findFirst();
        hospitalOptional.ifPresent(hospital -> hospital.getPatients().addAll(patients));
        if (hospitalOptional.isPresent()) return "Successfully added Patients";
        else throw new NotFoundException("Hospital with id: " + id + " not found");
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        return database.getAll().stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .collect(Collectors.groupingBy(Patient::getAge));
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = database.getAll().stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .toList();
        Comparator<Patient> comparator;
        if (ascOrDesc.equalsIgnoreCase("asc")) {
            comparator = Comparator.comparing(Patient::getAge);
        } else if (ascOrDesc.equalsIgnoreCase("desc")) {
            comparator = Comparator.comparing(Patient::getAge).reversed();
        } else throw new NotFoundException("Enter only asc or desc. You wrote: " + ascOrDesc);
        return patients.stream().sorted(comparator).toList();
    }
}
