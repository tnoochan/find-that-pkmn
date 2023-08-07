package ftp.Model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * Represents Scorbunny, a Pokemon
 */
public class Scorbunny extends Pokemon {

    //    public Scorbunny(List<Coord> steps, Random rand,
    //                   boolean visible) {
    //        super(steps, rand, visible, "Scorbunny", "Loves to jump around");
    //    }

    /**
     * Constructs a Scorbunny object with its description
     */
    public Scorbunny() {
        super("Scorbunny", "Loves to jump around");
    }

    /**
     * Takes steps restricted by the arguments. Scorbunny's behavior is completely randomized
     *
     * @param numSteps - the total number of steps to take
     * @param xbound - the x bound
     * @param ybound - the y bound
     */
    @Override
    public void takeSteps(int numSteps, int xbound, int ybound) {
        for (int i = 0; i < numSteps; i++) {
            int xpos = this.rand.nextInt(xbound);
            int ypos = this.rand.nextInt(ybound);
            steps.add(new Coord(xpos, ypos));
        }
    }

    /**
     * Produces an image of Scorbunny
     *
     * @param height - the height of the image
     * @param width - the width of the image
     * @return - an image of Scorbunny
     * @throws FileNotFoundException if scorbunny.png file could not be found
     */
    @Override
    public ImageView produceImage(int height, int width) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("src/main/resources/ImageFiles/scorbunny.png");
        ImageView output = new ImageView(new Image(stream));
        output.setFitHeight(height);
        output.setFitWidth(width);
        output.setPreserveRatio(true);
        return output;
    }

    /**
     * Gets Scorbunny's name
     *
     * @return - Scorbunny's name
     */
    @Override
    public String getName() {
        return this.name;
    }

}