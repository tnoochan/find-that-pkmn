package ftp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.Random;

public class Scorbunny extends Pokemon {

    public Scorbunny(List<Coord> steps, Random rand,
                   boolean visible) {
        super(steps, rand, visible, "Scorbunny");
    }

    public Scorbunny() {
        super("Scorbunny");
    }

    public Scorbunny(Random rand) {
        super(rand, "Scorbunny");
    }

    @Override
    public void takeSteps(int numSteps, int xbound, int ybound) {
        for (int i = 0; i < numSteps; i++) {
            int xPos = this.rand.nextInt(xbound);
            int yPos = this.rand.nextInt(ybound);
            steps.add(new Coord(xPos, yPos));
        }
    }

    @Override
    public String getName() {
        return this.name;
    }

}