package kg.Kursanov.dao.impl;

import kg.Kursanov.dao.HospitalDao;
import kg.Kursanov.db.Database;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.id.GeneratedId;
import kg.Kursanov.models.Hospital;
import kg.Kursanov.models.Patient;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Optional;

public class HospitalDaoImpl implements HospitalDao {

    private final Database database;

    public HospitalDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Hospital findById(int hospitalId) {
        return database.getAll().stream()
                .filter(hospital -> hospital.getId() == hospitalId).findFirst()
                .orElseThrow(() -> new NotFoundException("Hospital with id: " + hospitalId + " not found"));
    }

    @Override
    public Boolean add(Hospital hospital) {
        boolean baza = database.getAll().stream()
                .anyMatch(hospital1 -> hospital1.getHospitalName().equalsIgnoreCase(hospital.getHospitalName()));
        if (baza) throw  new NotFoundException("Hospital with name: " + hospital.getHospitalName() + " is already have");
        hospital.setId(GeneratedId.idGeneratorHospital());
        return database.save(hospital);
    }

    @Override
    public String delete(int id) {
        Optional<Hospital> first = database.getAll().stream()
                .filter(hospital -> hospital.getId() == id).findFirst();
        if (first.isPresent()){
            database.remove(first.get());
            return "Succsess";
        }
        else throw  new NotFoundException("Hospital with id: " + id + " not found");
    }

    @Override
    public List<Hospital> getAll() {
        return database.getAll();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(int id) {
        Optional<Hospital> first = database.getAll().stream()
                .filter(hospital -> hospital.getId() == id).findFirst();
        if (first.isPresent()) return first.get().getPatients();
        else throw new NotFoundException("Hospital with id:  + id +  not found");
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        List<Hospital> hospitals = database.getAll().stream()
                .filter(hospital -> hospital.getAddress().equalsIgnoreCase(address)).toList();
        if (!hospitals.isEmpty()){
            Hospital firstHospital = hospitals.get(0);
            return Collections.singletonMap(firstHospital.getAddress(),firstHospital);
        } else throw new NotFoundException("Hospital with address: " + address + " not found");
    }
}
