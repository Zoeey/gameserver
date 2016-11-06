package model.authInfo;

/**
 * Created by Max on 06.11.2016.
 */
public class Leader {
    private String name;
    private int points;

    public Leader setPts(int pts) {
        this.points = pts;
        return this;
    }

    public Leader setName(String nme){
        this.name = nme;
        return this;
    }

    public String getName(){
        return name;
    }

    public int getPoints(){
        return points;
    }
    public String toJSON(){
        return "\"" + this.name + "\": "+this.points;
    }
    /*
     CREATE TABLE leaderboard(
    muser VARCHAR(20) NOT NULL PRIMARY KEY,
    score INT NOT NULL DEFAULT 0);
     */

}
