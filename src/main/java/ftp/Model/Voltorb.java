package ftp.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents Voltorb, a Pokemon
 */
public class Voltorb extends Pokemon {

    //    public Voltorb(List<Coord> steps, Random rand,
    //                     boolean visible) {
    //        super(steps, rand, visible, "Voltorb", "Loves to roll around in adjacent squares");
    //    }

    /**
     * Constructs a Voltorb object with it's description
     */
    public Voltorb() {
        super("Voltorb", "Loves to roll around in adjacent squares");
    }


    /**
     * Takes steps restricted by the arguments. Voltorb's behavior is taking random steps on cells
     * adjacent to each other
     *
     * @param numSteps - the total number of steps to take
     * @param xbound - the x bound
     * @param ybound - the y bound
     */
    @Override
    public void takeSteps(int numSteps, int xbound, int ybound) {
        int startX = this.rand.nextInt(xbound);
        int startY = this.rand.nextInt(ybound);
        this.steps.add(new Coord(startX, startY));
        List<int[]> vectors = new ArrayList<>(Arrays.asList(
                new int[] {0, 1},
                new int[] {1, 0},
                new int[] {0, -1},
                new int[] {-1, 0}));
        for (int i = 1; i < numSteps; i++) {
            boolean valid = false;
            while (!valid) {
                int[] curVec = vectors.get(this.rand.nextInt(4));
                startX += curVec[0];
                startY += curVec[1];
                if (startX >= 0 && startX < xbound && startY >= 0 && startY < ybound) {
                    valid = true;
                }

            }
            this.steps.add(new Coord(startX, startY));
        }
    }

    /**
     * Produces an image of Voltorb
     *
     * @param height - the height of the image
     * @param width - the width of the image
     * @return - an image of Voltorb
     * @throws FileNotFoundException if voltorb.png file could not be found
     */
    @Override
    public ImageView produceImage(int height, int width) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("src/main/resources/ImageFiles/voltorb.png");
        ImageView output = new ImageView(new Image(stream));
        output.setFitHeight(height);
        output.setFitWidth(width);
        output.setPreserveRatio(true);
        return output;
    }

    /**
     * Gets Voltorb's name
     *
     * @return - Voltorb's name
     */
    @Override
    public String getName() {
        return this.name;
    }
}
