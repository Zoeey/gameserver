package model.gameObjects;

import model.gameInfo.GameConstants;
import model.gameInfo.Location;

/**
 * Created by User on 10.10.2016.
 */

public class SmallFood extends Cell {

    public SmallFood(Location location){
        super(location, GameConstants.FOOD_MASS);
    }
}
