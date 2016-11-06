package model.gameInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.jetbrains.annotations.NotNull;

/**
 * Server player avatar
 * <a href="https://atom.mail.ru/blog/topic/update/39/">HOMEWORK 1</a> example game instance
 *
 * @author Alpi
 */
public class Player {
    @NotNull
    private static final Logger log = LogManager.getLogger(Player.class);
    @NotNull
    private String name;

    public final long id;

  /**
   * Create new Player
   *
   * @param name        visible name
   */
  public Player(@NotNull String name, long id) {
    this.name = name;
    this.id = id;
    if (log.isInfoEnabled()) {
      log.info(toString() + " created");
    }
  }

    @NotNull
    public String getName() {
        return name;
    }

    @Override
  public String toString() {
    return "Player{" +
        "name='" + name + '\'' +
        "id='" + id + '\'' +
        '}';
  }
}
