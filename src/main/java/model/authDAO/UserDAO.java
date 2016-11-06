package model.authDAO;

import model.authInfo.User;

import java.util.List;

/**
 * Created by User on 05.11.2016.
 */
public class UserDAO implements AuthDAO<User>  {

    @Override
    public List<User> getAll() {
        return DbConnection.selectTransaction("from User");
    }

    @Override
    public List<User> getAllWhere(String... hqlCondidtions) {
        return null;
    }

    @Override
    public void insert(User user) {
        DbConnection.insertTransaction(user);
    }

    @Override
    public void delete(User user) {

    }
}
