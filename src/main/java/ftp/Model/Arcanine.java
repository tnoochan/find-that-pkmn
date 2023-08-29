package ftp.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents Arcanine, a Pokemon
 */
public class Arcanine extends Pokemon {

    /**
     * Constructs a blank Arcanine with supplied random object
     *
     * @param rand - the random object associated with this Arcanine
     */
    public Arcanine(Random rand) {
        super(rand);
    }

    /**
     * Constructs an Arcanine object with its description
     */
    public Arcanine() {
        super("Arcanine", "Loves to run in straight lines");
    }

    /**
     * Takes steps restricted by the arguments. Voltorb's behavior is walking in straight lines
     * and if it reaches the end, picks another row/column to walk across/down
     *
     * @param numSteps - the total number of steps to take
     * @param xbound - the x bound
     * @param ybound - the y bound
     */
    @Override
    public void takeSteps(int numSteps, int xbound, int ybound) {
        int direction = this.rand.nextInt(2);
        if (direction == 0) { // horizontal movement
            int row = this.rand.nextInt(ybound);
            int start = this.rand.nextInt(xbound);
            int vector = 1;
            while (this.steps.size() < numSteps) {
                this.steps.add(new Coord(start, row));
                if (start + vector >= xbound || start + vector < 0) {
                    start += vector;
                    vector *= -1; // pick a new row and reverse direction
                    row = this.rand.nextInt(ybound);
                }
                start += vector;
            }

        } else { // vertical movement
            int col = this.rand.nextInt(xbound);
            int start = this.rand.nextInt(ybound);
            int vector = 1;
            while (this.steps.size() < numSteps) {
                this.steps.add(new Coord(col, start));
                if (start + vector >= ybound || start + vector < 0) {
                    start += vector;
                    vector *= -1;
                    col = this.rand.nextInt(xbound);
                }
                start += vector;
            }
        }
    }

    /**
     * Produces an image of Arcanine
     *
     * @param height - the height of the image
     * @param width - the width of the image
     * @return - an image of Arcanine
     * @throws FileNotFoundException if arcanine.png file could not be found
     */
    @Override
    public ImageView produceImage(int height, int width) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("src/main/resources/ImageFiles/arcanine.png");
        ImageView output = new ImageView(new Image(stream));
        output.setFitHeight(height);
        output.setFitWidth(width);
        output.setPreserveRatio(true);
        return output;
    }

    /**
     * Gets Arcanine's name
     *
     * @return - Arcanine's name
     */
    @Override
    public String getName() {
        return this.name;
    }
}
