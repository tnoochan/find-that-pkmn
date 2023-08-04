package ftp.Model;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Voltorb extends Pokemon {

//    public Voltorb(List<Coord> steps, Random rand,
//                     boolean visible) {
//        super(steps, rand, visible, "Voltorb", "Loves to roll around in adjacent squares");
//    }

    public Voltorb() {
        super("Voltorb", "Loves to roll around in adjacent squares");
    }


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

    @Override
    public ImageView produceImage(int height, int width) throws FileNotFoundException {
        FileInputStream stream = new FileInputStream("src/main/resources/ImageFiles/voltorb.png");
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
