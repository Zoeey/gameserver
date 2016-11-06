package model.authInfo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by User on 20.10.2016.
 */
public class TokenUserStorage {
    private static ConcurrentHashMap<Token, User> userByToken;
    private static ConcurrentHashMap<User,Token> tokenByUser;

    static {
        userByToken = new ConcurrentHashMap<>();
        tokenByUser = new ConcurrentHashMap<>();
    }

    public static void add(Token token, User user){
        userByToken.put(token, user);
        tokenByUser.put(user, token);
    }

    public static void delete(User user){
        userByToken.remove(getTokenByUser(user));
        tokenByUser.remove(user);
    }

    public static List<User> getUsers(){
        return new ArrayList<>(userByToken.values());
    }

    public static User getUserByToken(Token token){
        return userByToken.get(token);
    }

    public static Token getTokenByUser(User user){
        return tokenByUser.get(user);
    }

    public static boolean containsUser(User user){
        return userByToken.values().contains(user);
    }

    public static boolean containsToken(Token token){
        return tokenByUser.values().contains(token);
    }
}
