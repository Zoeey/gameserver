package gameserver.usersdata;

import model.authDAO.LB;
import model.authInfo.Leader;
import model.authInfo.TokenUserStorage;
import model.authInfo.UsersJSON;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.List;

/**
 * Created by User on 25.10.2016.
 */
@Path("/data")
public class UserDataProvider {
    private static final Logger log = LogManager.getLogger(UserDataProvider.class);

    @POST
    @Path("/users")
    @Produces("application/json")
    public Response getLoggedInUsersList(){
        try{
            log.info("Users JSON requested");
            return Response.ok((new UsersJSON(TokenUserStorage.getUsers())).writeJson()).build();
        } catch (Exception e){
            log.info("Error sending users info");
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("leaderboard")
    @Consumes("application/x-www-form-urlencoded")
    @Produces("application/json")
    public Response getLeaders(){
        List<Leader> n1;
        int N = 5;
        n1= LB.getAll(N);
        String S="{ ";
        if(N>n1.size()) N=n1.size();
        for(int i=0;i<n1.size();i++) {
            S += n1.get(i).toJSON();
            if (i<n1.size()-1) S+=", ";
        }
        S+=" }";
        log.info("S");
        return Response.ok(S).build();
    }
}
