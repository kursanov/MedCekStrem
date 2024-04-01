package kg.Kursanov.dao.impl;

import kg.Kursanov.dao.DoctorDao;
import kg.Kursanov.db.Database;
import kg.Kursanov.exeptions.NotFoundException;
import kg.Kursanov.id.GeneratedId;
import kg.Kursanov.models.Department;
import kg.Kursanov.models.Doctor;
import kg.Kursanov.models.Hospital;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class DoctorDaoImpl implements DoctorDao {

    private final Database database;

    public DoctorDaoImpl(Database database) {
        this.database = database;
    }

    @Override
    public Doctor findById(int doctorId) {
        return getAll().stream().filter(doctor -> doctor.getId() == doctorId)
                .findFirst().orElseThrow(() -> new NotFoundException("Doctor with id: " + doctorId + " not found"));
    }

    @Override
    public String add(int hospitalId, Doctor doctor) {

        Optional<Hospital> first = database.getAll().stream()
                .filter(hospital -> hospital.getId() == (hospitalId))
                .findFirst();
        if (first.isPresent()) {
            Hospital hospital = first.get();
            doctor.setId(GeneratedId.idGeneratorDoctor());
            hospital.getDoctors().add(doctor);
            return "Successfully added";
        } else throw new NotFoundException("Hospital with id: " + hospitalId + " not found!");
    }

    @Override
    public String remove(int id) {
        Optional<Doctor> first = database.getAll().stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(doctor -> doctor.getId() == (id))
                .findFirst();
        if (first.isPresent()) {
            getAll().remove(first.get());
            return "Successfully deleted";
        } else throw new NotFoundException("Doctor with id: " + id + " not found");
    }


    @Override
    public List<Doctor> getAll() {
        List<Doctor> doctors = new ArrayList<>();
        for (Hospital hospital : database.getAll()) {
            doctors.addAll(hospital.getDoctors());
        }
        if (!doctors.isEmpty()) return doctors;
        else throw new NotFoundException("No doctors found in any hospital");

    }

    @Override
    public String assignDoctorToDepartment(int departmentId, List<Integer> doctorsId) {
        for (Hospital hospital : database.getAll()) {
            for (Department department : hospital.getDepartments()) {
                if (departmentId == department.getId()) {
                    List<Doctor> doctorsToAdd = hospital.getDoctors().stream()
                            .filter(doctor -> doctorsId.contains(doctor.getId())).toList();
                    if (doctorsToAdd.isEmpty()) {
                        throw new IllegalArgumentException("Doctors with ids: " + doctorsId + " not found!");
                    }
                    department.getDoctors().addAll(doctorsToAdd);
                    hospital.getDoctors().removeAll(doctorsToAdd);
                    return "Successfully assign doctors to department";
                }
            }

        }
        throw new NotFoundException("Department with id: " + departmentId + " not found");
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(int id) {
        Optional<Hospital> first = database.getAll().stream()
                .filter(hospital -> hospital.getId() == (id))
                .findFirst();
        return first.map(Hospital::getDoctors)
                .orElseThrow(() -> new NotFoundException("Hospital with id: " + id + " not found"));
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(int id) {
        Optional<Department> first = database.getAll().stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getId() == id).findFirst();
        return first.map(Department::getDoctors)

                .orElseThrow(() -> new NotFoundException("Department with id: " + id + " not found"));
    }

    @Override
    public String updateById(int id, Doctor doctor) {
        Optional<Doctor> first = getAll().stream()
                .filter(doctor1 -> doctor1.getId() == id).findFirst();
        if (first.isPresent()) {
            Doctor doctor1 = first.get();
            doctor1.setFirstName(doctor.getFirstName());
            doctor1.setLastName(doctor.getLastName());
            doctor1.setGender(doctor.getGender());
            doctor1.setExperienceYear(doctor.getExperienceYear());
            return "Successfully updated";
        }
        Optional<Doctor> first1 = database.getAll().stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .flatMap(department -> department.getDoctors().stream())
                .filter(doctor1 -> doctor1.getId() == id).findFirst();
        if (first.isPresent()) {
            Doctor doctor1 = first1.get();
            doctor1.setFirstName(doctor.getFirstName());
            doctor1.setLastName(doctor.getLastName());
            doctor1.setGender(doctor.getGender());
            doctor1.setExperienceYear(doctor.getExperienceYear());
            return "Successfully updated";
        } else throw new NotFoundException("Doctor with id: " + id + " not found");

    }
}
