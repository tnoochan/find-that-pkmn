package ftp.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Pokemon {

    protected List<Coord> steps;
    protected final Random rand;
    private boolean visible = false;

    public Pokemon(Random rand) {
        this.rand = rand;
    }

    public Pokemon() {
        this.steps = new ArrayList<>();
        this.rand = new Random();
    }

    // xbound, ybound exclusive
    public abstract void takeSteps(int numSteps, int xbound, int ybound);

    public List<Coord> getSteps() {
        return this.steps;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean status) {
        this.visible = status;
    }

}
