package model.authDAO;

import model.authInfo.Leader;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.net.server.SecureTcpSocketServer;

import javax.print.DocFlavor;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by Max on 06.11.2016.
 */
public class LB {
    private static final Logger log = LogManager.getLogger(LB.class);
    private static final String INSERT_POINTS_TEMPLATE =
            "INSERT INTO leaderboard VALUES (\'%s\');";
    private static final String UPDATE_POINTS =
            "UPDATE leaderboard SET score = %d WHERE muser =\'%s\';";
    private static final String SELECT_LEADERS =
            "SELECT * FROM leaderboard ORDER BY score DESC LIMIT %d;";

    public static void insert(String user){
        log.info(String.format(INSERT_POINTS_TEMPLATE, user));
        try (Connection con = JDBCDbConnection.getConnection();
             Statement stm = con.createStatement()) {
            stm.execute(String.format(INSERT_POINTS_TEMPLATE, user));
            log.info("User " + user +" Inserted to leaderbord");
            log.info(String.format(INSERT_POINTS_TEMPLATE, user));
        } catch (SQLException e) {
            log.error("Failed to insert pts");
        }
    }

    public static void update(String user,int pts){
        try (Connection con = JDBCDbConnection.getConnection();
             Statement stm = con.createStatement()) {
            stm.execute(String.format(UPDATE_POINTS, pts, user));
            log.info("user "+ user + " pts "+ pts + " updated");
            log.info(String.format(INSERT_POINTS_TEMPLATE, pts, user));
        } catch (SQLException e) {
            log.error("Failed to insert pts");
        }
    }

    public static List<Leader> getAll(int N) {
        List<Leader> leaders = new ArrayList<>();
        try (Connection con = JDBCDbConnection.getConnection();
             Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery(String.format(SELECT_LEADERS,N));
            while (rs.next()) {
                leaders.add(mapToLeader(rs));
                log.info("name " + rs.getString("muser") + "pts "+ rs.getInt("score"));
            }
        } catch (SQLException e) {
            log.error("Failed to getAll.", e);
            return Collections.emptyList();
        }

        return leaders;
    }

    public static void deleteUser(String name){
        List<Leader> leaders = new ArrayList<>();
        try (Connection con = JDBCDbConnection.getConnection();
             Statement stm = con.createStatement()) {
            stm.execute("DELETE FROM leaderboard WHERE muser = \'"+name+"\'");
            log.info("User "+ name + " deleted");
        }catch (SQLException e) {
            log.error("Failed to getAll.", e);
        }
    }

    public static int getUserScore(String user){
        try (Connection con = JDBCDbConnection.getConnection();
             Statement stm = con.createStatement()) {
            ResultSet rs = stm.executeQuery("SELECT * FROM leaderboard WHERE muser = \'" + user + "\'");
            rs.next();
            return rs.getInt("score");
        }catch (SQLException e) {
            log.error("Failed to getAll.", e);
            return -1;
        }
    }

    private static Leader mapToLeader(ResultSet rs) throws SQLException{
        return new Leader().setName(rs.getString("muser")).setPts(rs.getInt("score"));
    }
}
