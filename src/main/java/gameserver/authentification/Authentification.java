package gameserver.authentification;

import model.authDAO.LB;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import model.authInfo.Token;
import model.authInfo.TokenUserStorage;
import model.authInfo.User;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by User on 20.10.2016.
 */
@Path("/auth")
public class Authentification {

    private static User autorizedUser;
    private static final Logger log = LogManager.getLogger(Authentification.class);
    private static CopyOnWriteArrayList<User> registeredUsers;

    static {
        registeredUsers = new CopyOnWriteArrayList<>();
    }

    public static void setAutorizedUser(User user) {
        autorizedUser = user;
    }

    public static User getAutorizedUser(){return autorizedUser;}

    public static List<User> getRegisteredUsers(){return registeredUsers;}

    @POST
    @Path("/register")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response register(@FormParam("user")String login, @FormParam("password")String password ){
        try {
            if (login == null || password == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            User currentUser = new User(login,password,"a");
            if (registeredUsers.contains(currentUser)) {
                log.info("User '{}' already exists", currentUser);
                return Response.status(Response.Status.NOT_ACCEPTABLE).build();
            }
            registeredUsers.add(currentUser);
            log.info("New user '{}' registered", currentUser);
            LB.insert(login);
            return Response.ok("User " + currentUser + " registered").build();
        } catch (Exception e){
            log.info("Error register user.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }


    @POST
    @Path("/login")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response login(@FormParam("user")String login, @FormParam("password")String password ){
        try {
            if (login == null || password == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            User currentUser = new User("a","a","a");
            if (!validatePassword(currentUser)) {
                log.info("Wrong password for user {}", currentUser);
                return Response.status(Response.Status.UNAUTHORIZED).build();
            }
            if(!TokenUserStorage.containsUser(currentUser)){
                TokenUserStorage.add(new Token(), currentUser);
            } else{
                log.info("User '{}' already logged in.", currentUser);
            }
            log.info("User '{}' logged in.", currentUser);
            return Response.ok(TokenUserStorage.getTokenByUser(currentUser).toString()).build();
        } catch (Exception e){
            log.info("Error login user.");
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
    }

    private boolean validatePassword(User user){
        return registeredUsers.contains(user);
    }

    public static void validateToken(Token token) throws Exception{
        if(TokenUserStorage.containsToken(token)){
            log.info("Correct token from '{}'", TokenUserStorage.getUserByToken(token));
        } else{

            throw new Exception("Token validation exception");
        }

    }

    @Autorized
    @POST
    @Path("/logout")
    @Produces("text/plain")
    public Response logout(){
        try{
            TokenUserStorage.delete(autorizedUser);
            log.info("User '{}' logged out", autorizedUser);
            return Response.ok("User logged out").build();
        } catch(Exception e) {
            log.info("Error logout user.");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }




}
