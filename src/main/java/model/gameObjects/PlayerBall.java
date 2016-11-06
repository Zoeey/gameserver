package model.gameObjects;

import matchmaker.SinglePlayerMatchMaker;
import model.gameInfo.Location;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * Created by User on 10.10.2016.
 */

//Игровая сущность, управляемая игроком
public class PlayerBall {

    private final Logger log = LogManager.getLogger(SinglePlayerMatchMaker.class);
    public Location location;
    private int size;
    public final String name;

    public PlayerBall(Location location, int size, String name) {
        this.name = name;
        this.location = location;
        this.size = size;
        if (log.isInfoEnabled()) {
            log.info(toString() + " created");
        }
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }

    @Override
    public String toString() {
        return "PlayerBall{" +
                "location=" + location + " " +
                "name=" + name + " " +
                "size=" + size +
                '}';
    }


}
