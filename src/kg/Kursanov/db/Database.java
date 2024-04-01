package kg.Kursanov.db;

import kg.Kursanov.models.Hospital;

import java.util.LinkedList;
import java.util.List;

public class Database {

    private List<Hospital> hospitals = new LinkedList<>();


    public Boolean save(Hospital hospital){
        return hospitals.add(hospital);
    }


    public Boolean remove(Hospital hospital){
        return hospitals.remove(hospital);
    }



    public List<Hospital> getAll(){
        return hospitals;
    }
}
