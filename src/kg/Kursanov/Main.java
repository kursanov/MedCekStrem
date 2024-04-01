package kg.Kursanov;


import kg.Kursanov.dao.impl.DepartmentDaoImpl;
import kg.Kursanov.dao.impl.DoctorDaoImpl;
import kg.Kursanov.dao.impl.HospitalDaoImpl;
import kg.Kursanov.dao.impl.PatientDaoImpl;
import kg.Kursanov.db.Database;
import kg.Kursanov.enume.Gender;
import kg.Kursanov.models.Department;
import kg.Kursanov.models.Doctor;
import kg.Kursanov.models.Hospital;
import kg.Kursanov.models.Patient;
import kg.Kursanov.service.HospitalService;
import kg.Kursanov.service.impl.DepartmentServiceImpl;
import kg.Kursanov.service.impl.DoctorServiceImpl;
import kg.Kursanov.service.impl.HospitalServiceImpl;
import kg.Kursanov.service.impl.PatientServiceImpl;

import java.util.*;

public class Main {
    public static void main(String[] args) {


        Database database = new Database();
        HospitalService hospital = new HospitalServiceImpl(new HospitalDaoImpl(database));
        DepartmentServiceImpl department = new DepartmentServiceImpl(new DepartmentDaoImpl(database));
        DoctorServiceImpl doctor = new DoctorServiceImpl(new DoctorDaoImpl(database));
        PatientServiceImpl patient = new PatientServiceImpl(new PatientDaoImpl(database));

        System.out.println();

        Scanner scanner = new Scanner(System.in);
        OuterLoop:
        while (true) {
            try {
                menu();
                switch (scanner.nextInt()) {
                    case 0 -> {break OuterLoop;}
                    case 1 -> System.out.println(hospital.addHospital(new Hospital(1,"My Hospital","Batken")));
                    case 2 -> System.out.println(hospital.findHospitalById(1));
                    case 3 -> System.out.println(hospital.getAllHospital());
                    case 4 -> System.out.println(hospital.getAllPatientFromHospital(1));
                    case 5 -> System.out.println(hospital.deleteHospitalById(1));
                    case 6 -> System.out.println(hospital.getAllHospitalByAddress("Batken"));
                    case 7 -> System.out.println(department.add(1, new Department(1,"Терепия")));
                    case 8 -> System.out.println(department.removeById(1));
                    case 9 -> System.out.println(department.updateById(1, new Department(28, "Хирургия")));
                    case 10 -> System.out.println(department.getAllDepartmentByHospital(1));
                    case 11 -> System.out.println(department.findDepartmentByName("Терепия"));
                    case 12 -> System.out.println(doctor.add(1, new Doctor(1, "Нуркамил", "Камчыев", Gender.MALE, 10)));
                    case 13 -> System.out.println(doctor.removeById(1));
                    case 14 -> System.out.println(doctor.updateById(1, new Doctor(1, "Айшуареа", "Рай кызы", Gender.FEMALE, 15)));
                    case 15 -> System.out.println(doctor.findDoctorById(1));
                    case 16 -> System.out.println(doctor.assignDoctorToDepartment(1,ids()));
                    case 17 -> System.out.println(doctor.getAllDoctorsByHospitalId(1));
                    case 18 -> System.out.println(doctor.getAllDoctorsByDepartmentId(1));
                    case 19 -> System.out.println(patient.add(1, new Patient(1, "Диана", "Ровшанбековна", 20, Gender.FEMALE)));
                    case 20 -> System.out.println(patient.removeById(1));
                    case 21 -> patient.updateById(1, new Patient(1, "Эсен", "ниязов", 21, Gender.MALE));
                    case 22 -> System.out.println(patient.addPatientsToHospital(1, patients()));
                    case 23 -> System.out.println(patient.getPatientById(1));
                    case 24 -> System.out.println(patient.getPatientByAge());
                    case 25 -> System.out.println(patient.sortPatientsByAge("Desc"));
                    default -> System.out.println("Enter correct choice!!!");
                }
            } catch (InputMismatchException e) {
                System.err.println("Введите допустимое целое число");
                scanner.next();
            }
        }
    }
    private static void menu() {
        System.out.println("""
                0 ->  Exit                                         13 -> Remove Doctor by ID
                1 ->  Add Hospital                                 14 -> Update Doctor by ID
                2 ->  Find Hospital by ID                          15 -> Find Doctor by ID
                3 ->  Get all Hospitals                            16 -> Assign Doctors to Department
                4 ->  Get all Patient from Hospital                17 -> Get all Doctors by Hospital ID
                5 ->  Delete Hospital by ID                        18 -> Get all Doctors by Department ID
                6 ->  Get all Hospitals by address                 19 -> Add Patient to Hospital by ID
                7 ->  Add Department to Hospital by ID             20 -> Remove Patient by ID
                8 ->  Remove Department by ID                      21 -> Update Patient by ID
                9 -> Update Department by ID                       22 -> Add Patients to Hospital by ID
                10 -> Get all Department by Hospital ID            23 -> Get Patient by ID
                11 -> Find Department by name                      24 -> Get Patient by age
                12 -> Add Doctor to Hospital by ID                 25 -> Sort Patient by age
                """);
        System.out.print("введите команду: ");
    }
    private static List<Patient> patients(){
        return new ArrayList<>(Arrays.asList(
                new Patient(1, "Мурат", "Газыев", 24, Gender.MALE),
                new Patient(2, "Гулжигит", "Сатыбеков", 24, Gender.MALE),
                new Patient(3, "Канышай", "Садирова", 19, Gender.FEMALE),
                new Patient(4, "Айзат", "Дуйшеева", 18, Gender.FEMALE),
                new Patient(5, "Датка", "Маматжанова", 21, Gender.FEMALE)
        ));
    }
    private static List<Integer> ids(){
        return new ArrayList<>(List.of(1));
    }






    }
