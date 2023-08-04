package ftp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import javafx.scene.image.ImageView;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public abstract class Pokemon {

    protected List<Coord> steps;
    protected final Random rand;
    private boolean visible = false;
    protected final String name;
    protected final String description;

    @JsonCreator
    public Pokemon(
            @JsonProperty("steps") List<Coord> steps,
            @JsonProperty("rand") Random rand,
            @JsonProperty("visible") boolean visible,
            @JsonProperty("name") String name,
            @JsonProperty("description") String description) {
        this.steps = steps;
        this.rand = rand;
        this.visible = visible;
        this.name = name;
        this.description = description;
    }

    public Pokemon(Random rand, String name) {
        this.rand = rand;
        this.name = name;
        this.description = "";
    }

    public Pokemon(String name, String description) {
        this.steps = new ArrayList<>();
        this.rand = new Random();
        this.name = name;
        this.description = description;
    }

    // xbound, ybound exclusive
    public abstract void takeSteps(int numSteps, int xbound, int ybound);

    public abstract ImageView produceImage(int height, int width) throws FileNotFoundException;

    public List<Coord> getSteps() {
        return this.steps;
    }

    public boolean getVisible() {
        return this.visible;
    }

    public void setVisible(boolean status) {
        this.visible = status;
    }

    public abstract String getName();

    public String getDescription() {
        return this.description;
    }

}