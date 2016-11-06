package DAOtest;

import model.authDAO.UserDAO;
import model.authInfo.User;
import org.junit.Test;

/**
 * Created by User on 05.11.2016.
 */
public class UserDAOtest {

    UserDAO userDAO = new UserDAO();

    @Test
    public void testGetAll(){
        System.out.println(userDAO.getAll());
    }

    @Test
    public void  insertTest(){
        User user =  new User("Andrew1","1234","a@m.ru");
        userDAO.insert(user);
        System.out.println(userDAO.getAll());
    }

}
