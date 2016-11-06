package matchmaker;

import model.gameInfo.GameSession;
import model.gameInfo.Player;
import model.gameInfo.SessionIsFullException;
import model.gameInfo.SingleGameSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Creates {@link GameSession} for single player
 *
 * @author Alpi
 */
public class SinglePlayerMatchMaker implements MatchMaker {
  @NotNull
  private final Logger log = LogManager.getLogger(SinglePlayerMatchMaker.class);
  @NotNull
  private final List<GameSession> activeGameSessions = new ArrayList<>();
  @NotNull
  private GameSession currentGameSession;

  public SinglePlayerMatchMaker(){
    GameSession currentGameSession = createNewGame();
    activeGameSessions.add(currentGameSession);
  }

  /**
   * Creates new GameSession for single player
   *
   * @param player single player
   */

  //Попытка подключить игрока к игровой сессии, выбрасывается SessionIsFullException если сессия переполнена
  @Override
  public void joinGame(@NotNull Player player) {
    try {
      currentGameSession.join(player);
      if (log.isInfoEnabled()) {
        log.info(player + " joined " + currentGameSession);
      }
    } catch (SessionIsFullException e){
      if (log.isInfoEnabled()) {
        log.info("Session " + currentGameSession + " is full");
      }
    }
  }

  @NotNull
  public List<GameSession> getActiveGameSessions() {
    return new ArrayList<>(activeGameSessions);
  }


  @NotNull
  private GameSession createNewGame() {
    return new SingleGameSession();
  }
}
