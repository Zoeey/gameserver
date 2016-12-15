package model.gameInfo.utils;

import model.gameInfo.GameConstants;
import model.gameInfo.Location;
import model.gameObjects.GameField;
import model.gameObjects.Virus;
import org.jetbrains.annotations.NotNull;

import java.util.Random;

/**
 * @author apomosov
 */
public class RandomVirusGenerator implements VirusGenerator {
    @NotNull
    private final GameField field;
    private final int numberOfViruses;


    public RandomVirusGenerator(@NotNull GameField field, int numberOfViruses) {
        this.field = field;
        this.numberOfViruses = numberOfViruses;
    }

    @Override
    public void generate() {
        Random random = new Random();
        int virusRadius = (int) Math.sqrt(GameConstants.VIRUS_MASS / Math.PI);
        for (int i = 0; i < numberOfViruses; i++) {
        Virus virus = new Virus( new Location(virusRadius + random.nextInt(field.getWidth() - 2 * virusRadius),
              virusRadius + random.nextInt(field.getHeight() - 2 * virusRadius)));
        }
    }
}
