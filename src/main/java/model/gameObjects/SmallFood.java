package model.gameObjects;

import model.gameInfo.Location;

/**
 * Created by User on 10.10.2016.
 */

//Небольшие кусочик еды, разбросанные по всей карте, которые маленькие игроки едят, чтобы немного подрасти

public class SmallFood {


    public Location location;
    private int size;

    public SmallFood(Location location, int size){
        this.location = location;
        setSize(size);
    }


    public void setSize(int size) {
        this.size = size;
    }

    public int getSize() {
        return size;
    }
}
