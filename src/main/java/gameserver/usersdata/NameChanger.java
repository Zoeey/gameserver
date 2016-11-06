package gameserver.usersdata;

import model.authInfo.Token;
import model.authInfo.TokenUserStorage;
import model.authInfo.User;
import gameserver.authentification.Authentification;
import gameserver.authentification.Autorized;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

//10.3.13.96

/**
 * Created by User on 25.10.2016.
 */
@Path("/profile")
public class NameChanger {
    private static final Logger log = LogManager.getLogger(NameChanger.class);

    @Autorized
    @POST
    @Path("/name")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("text/plain")
    public Response changeName(@FormParam("name")String newName){
        try {
            if (newName == null) {
                return Response.status(Response.Status.BAD_REQUEST).build();
            }
            User currentUser = Authentification.getAutorizedUser();
            Token oldToken = TokenUserStorage.getTokenByUser(currentUser);
            TokenUserStorage.delete(currentUser);
            for(User user: Authentification.getRegisteredUsers()){
                if(user.equals(currentUser)){
                    user.setLogin(newName);
                }
            }
            currentUser.setLogin(newName);
            TokenUserStorage.add(oldToken,currentUser);
            log.info("User '{}' has new name", currentUser);
            return Response.ok("Name changed").build();
        } catch (Exception e){
            log.info("Error name changing for '{}'", Authentification.getAutorizedUser());
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }
}
