package model.gameInfo;

import model.gameObjects.GameField;
import model.gameObjects.PlayerBall;
import model.gameObjects.SmallFood;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by User on 10.10.2016.
 */
public class SingleGameSession implements GameSession {
    @NotNull
    private static final Logger log = LogManager.getLogger(Player.class);
    @NotNull
    private GameField gameField;
    @NotNull
    private List<SmallFood> smallFoodList = new ArrayList<>();
    @NotNull
    private Map<Player,PlayerBall> playerBallMap = new HashMap<>();

    public SingleGameSession(){
        gameField = new GameField(GameConstants.DEFAULT_WIDTH, GameConstants.DEFAULT_HEIGHT);
        smallFoodList = generateSmallFood();
        if (log.isInfoEnabled()) {
            log.info(toString() + " created");
        }
    }


    //разбрасывает объекты SmallFood в случайных местах на карте
    private List<SmallFood> generateSmallFood(){
        List<SmallFood> result = new ArrayList<>();
        for(int i = 0; i < GameConstants.SMALL_FOOD_AMOUNT; i ++){
            result.add(new SmallFood(Location.getRandomLocation(), GameConstants.DEFAULT_SMALL_FOOD_SIZE));
        }
        if (log.isInfoEnabled()) {
            log.info("Small food generated");
        }
        return result;
    }

    @Override
    public void join(@NotNull Player player) throws SessionIsFullException {
        if(playerBallMap.size() < GameConstants.MAX_PLAYERS_IN_SESSION) {
            playerBallMap.put(player,
                    new PlayerBall(Location.getRandomLocation(), GameConstants.INITIAL_PLAYER_BALL_SIZE, player.getName()));

        } else{
            throw new SessionIsFullException();
        }
    }

    @Override
    public String toString(){
        return "SingleGameSession{" +
                "number of players="+ playerBallMap.size() +
                '}';
    }
}
