package kg.Kursanov.id;

public class GeneratedId {

    public static  int idHospital;

    public static  int idPatient;
    public static  int idDepartment;
    public static  int idDoctor;


    public static int idGeneratorHospital(){
        return ++idHospital;
    }

    public static int idGeneratorPatient(){
        return ++ idPatient;
    }

    public static  int idGeneratorDepartment(){
        return ++idDepartment;
    }

    public static  int idGeneratorDoctor(){
        return ++idDoctor;
    }

}
