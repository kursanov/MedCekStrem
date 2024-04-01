package kg.Kursanov.service;

public interface GenericService<T> {


    String add(int  hospitalId, T t);

    String removeById(int id);

    String updateById(int id, T t);


}
