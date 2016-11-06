package DAOtest;

import com.squareup.okhttp.*;
import model.authDAO.JDBCDbConnection;
import model.authDAO.LB;
import model.authInfo.Leader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Assert;
import org.junit.Test;

import java.io.IOException;
import java.sql.*;
import java.util.List;


/**
 * Created by Max on 06.11.2016.
 */
public class LBtest {
    private static final Logger log = LogManager.getLogger(LBtest.class);
    private static final String PROTOCOL = "http";
    private static final String HOST = "127.0.0.1";
    private static final String PORT = "8081";
    private static final String SERVICE_URL = PROTOCOL + "://" + HOST + ":" + PORT;

    private static final OkHttpClient client = new OkHttpClient();

    @Test
    public void InsertTest(){
        String user="InserTest";
        LB.insert(user);
        Assert.assertEquals(0,LB.getUserScore(user));
        LB.deleteUser(user);
    }

    @Test
    public void UpdateTest(){
        String user ="update";
        LB.insert(user);
        LB.update(user,25);
        Assert.assertEquals(25,LB.getUserScore(user));
        LB.deleteUser(user);
    }

    @Test
    public void LeaderRegisterTest(){
        String user="LeaderTest";
        String password="LeaderTest";
        MediaType mediaType = MediaType.parse("application/x-www-form-urlencoded");
        RequestBody body = RequestBody.create(
                mediaType,
                String.format("user=%s&password=%s", user, password)
        );

        String requestUrl = SERVICE_URL + "/auth/register";
        Request request = new Request.Builder()
                .url(requestUrl)
                .post(body)
                .addHeader("content-type", "application/x-www-form-urlencoded")
                .build();
        try {
            OkHttpClient client = new OkHttpClient();
            Response response = client.newCall(request).execute();
            System.out.println(response.isSuccessful());
        } catch (IOException e) {
            //log.warn("Something went wrong in register.", e);
            System.out.println(false);
        }
        Assert.assertEquals(0,LB.getUserScore(user));
        LB.deleteUser(user);
    }

    @Test
    public void getLeaderTest(){
        LB.insert("LeaderUser1");
        LB.insert("LeaderUser2");
        LB.insert("LeaderUser3");
        LB.update("LeaderUser1",2147483647);
        LB.update("LeaderUser2",2147483646);
        LB.update("LeaderUser3",2147483645);
        List<Leader> l1=LB.getAll(3);
        Assert.assertEquals("LeaderUser1",l1.get(0).getName());
        Assert.assertEquals("LeaderUser2",l1.get(1).getName());
        Assert.assertEquals("LeaderUser3",l1.get(2).getName());
        LB.deleteUser("LeaderUser1");
        LB.deleteUser("LeaderUser2");
        LB.deleteUser("LeaderUser3");
    }
}
