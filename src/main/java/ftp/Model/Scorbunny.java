package ftp.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
import java.util.Random;

public class Scorbunny extends Pokemon {

//    public Scorbunny(List<Coord> steps, Random rand,
//                   boolean visible) {
//        super(steps, rand, visible, "Scorbunny", "Loves to jump around");
//    }

    public Scorbunny() {
        super("Scorbunny", "Loves to jump around");
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
    public ImageView produceImage(int height, int width) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("src/main/resources/ImageFiles/scorbunny.png");
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