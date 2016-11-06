package model.authDAO;

import java.util.List;

/**
 * Created by User on 05.11.2016.
 */
public interface AuthDAO<T> {

    List<T> getAll();

    List<T> getAllWhere(String ... hqlCondidtions);

    void insert(T t);

    void delete(T t);

}
