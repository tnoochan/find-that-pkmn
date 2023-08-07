package ftp.Model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import javafx.scene.image.ImageView;

/**
 * Represents classes of Pokemons
 */
public abstract class Pokemon {

    protected List<Coord> steps;
    protected final Random rand;
    private boolean visible = false;
    protected final String name;
    protected final String description;

    /**
     * Constructs a Pokemon with all parameters
     *
     * @param steps - the steps this Pokemon has taken
     * @param rand - the random object associated with this Pokemon
     * @param visible - whether this Pokemon is visible
     * @param name - the name of this Pokemon
     * @param description - the description of this Pokemon
     */
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

    //    public Pokemon(Random rand, String name) {
    //        this.rand = rand;
    //        this.name = name;
    //        this.description = "";
    //    }

    /**
     * Constructs a Pokemon with the supplied name and description
     *
     * @param name - the name of the Pokemon
     * @param description - the description associated with this Pokemon
     */
    public Pokemon(String name, String description) {
        this.steps = new ArrayList<>();
        this.rand = new Random();
        this.name = name;
        this.description = description;
    }

    /**
     * Cause this Pokemon to take steps restricted by arguments supplied
     *
     * @param numSteps - the total number of steps to take
     * @param xbound - the x bound
     * @param ybound - the y bound
     */
    public abstract void takeSteps(int numSteps, int xbound, int ybound);

    /**
     * Produces an image of this Pokemon
     *
     * @param height - the height of the image
     * @param width - the width of the image
     *
     * @return - an image of this Pokemon
     * @throws FileNotFoundException if the file to load this Pokemon's image is not found
     */
    public abstract ImageView produceImage(int height, int width) throws FileNotFoundException;

    /**
     * Gets this Pokemon's steps
     *
     * @return - this Pokemon's steps
     */
    public List<Coord> getSteps() {
        return this.steps;
    }

    /**
     * Gets the visibility status of this Pokemon
     *
     * @return true, if this Pokemon is visible, false otherwise
     */
    public boolean getVisible() {
        return this.visible;
    }

    /**
     * Sets the visibility status of this Pokemon
     *
     * @param status - set this Pokemon to visible (true), or not (false)
     */
    public void setVisible(boolean status) {
        this.visible = status;
    }

    /**
     * Gets this Pokemon's name
     *
     * @return - the name of this Pokemon
     */
    public abstract String getName();

    /**
     * Gets the description of this Pokemon
     *
     * @return - the Pokemon's description
     */
    public String getDescription() {
        return this.description;
    }

}