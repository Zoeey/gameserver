package model.authDAO;

import model.authInfo.Token;

import java.util.List;

/**
 * Created by User on 05.11.2016.
 */
public class TokenDAO implements AuthDAO<Token> {
    @Override
    public List<Token> getAll() {
        return DbConnection.selectTransaction("from Token");
    }

    @Override
    public List<Token> getAllWhere(String... hqlCondidtions) {
        return null;
    }

    @Override
    public void insert(Token token) {
        DbConnection.insertTransaction(token);
    }

    @Override
    public void delete(Token token) {

    }
}
