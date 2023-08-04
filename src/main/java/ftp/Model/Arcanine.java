package ftp.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

public class Arcanine extends Pokemon {

    public Arcanine() {
        super("Arcanine", "Loves to run in straight lines");
    }

    @Override
    public void takeSteps(int numSteps, int xbound, int ybound) {
        int direction = this.rand.nextInt(1);
        if (direction == 0) { // horizontal movement
            int row = this.rand.nextInt(ybound);
            int start = this.rand.nextInt(xbound);
            int vector = 1;
            while(this.steps.size() < numSteps) {
                this.steps.add(new Coord(start, row));
                if (start + vector >= xbound || start + vector < 0) {
                    start += vector;
                    vector *= -1;
                    row = this.rand.nextInt(ybound);
                }
                start += vector;
            }

        } else { // vertical movement

        }
    }

    @Override
    public ImageView produceImage(int height, int width) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("src/main/resources/ImageFiles/arcanine.png");
        ImageView output = new ImageView(new Image(stream));
        output.setFitHeight(height);
        output.setFitWidth(width);
        output.setPreserveRatio(true);
        return output;
    }

    @Override
    public String getName() {
        return this.name;
    }
}
