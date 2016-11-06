package DAOtest;

import model.authDAO.TokenDAO;
import model.authInfo.Token;
import org.junit.Test;

/**
 * Created by User on 05.11.2016.
 */
public class TokenDAOtest {

    TokenDAO tokenDAO = new TokenDAO();

    @Test
    public void insertTest(){
        System.out.println(tokenDAO.getAll());
        tokenDAO.insert(new Token());
        System.out.println(tokenDAO.getAll());
    }

    @Test
    public void getAllTest(){
        System.out.println(tokenDAO.getAll());
    }
}
