package ftp.Model;

import java.util.Random;

public class Scorbunny extends Pokemon {

    public Scorbunny() {
        super();
    }

    public Scorbunny(Random rand) {
        super(rand);
    }

    @Override
    public void takeSteps(int numSteps, int xbound, int ybound) {
        for (int i = 0; i < numSteps; i++) {
            int xPos = this.rand.nextInt(xbound);
            int yPos = this.rand.nextInt(ybound);
            steps.add(new Coord(xPos, yPos));
        }
    }

}
