package ftp.Controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ftp.Model.BasicPokemonGenerator;
import ftp.Model.Coord;
import ftp.Model.GridCell;
import ftp.Model.Pokemon;
import ftp.Model.PokemonGenerator;
import ftp.Model.StepsVerifier;
import ftp.View.BackgroundUtil;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

/**
 * Represents the main game controller that controls the sequence of events
 */
public class GameController {

    private static final int BOARD_WIDTH = 6;
    private static final int BOARD_HEIGHT = 6;
    private static final int CELL_HEIGHT = 60;
    private static final int CELL_WIDTH = 100;

    private String name = "";
    private int level;
    private Pokemon currentPokemon;
    private Coord currentPos;
    private List<Coord> sequenceAnswer;
    private Iterator<Coord> sequenceIterator;
    private SetupManager sceneMaster;
    private boolean gameOver = false;
    private PokemonGenerator generator;

    @FXML
    private Button submit;
    @FXML
    private Button repeat;
    @FXML
    private Button exitButton;
    @FXML
    private GridPane board;
    @FXML
    private Label instructions;
    @FXML
    private Button clear;
    @FXML
    private Label scoreLabel;
    @FXML
    private Label livesLabel;
    @FXML
    private Label guide;
    @FXML
    private AnchorPane backdrop;

    private int score;
    private int lives;
    private List<GridCell> userInput;
    private Label[][] boardLabel;

    /**
     * Constructs this controller with the supplied SetupManager
     * @param setup - the setup manager that manages the scenes
     */
    public GameController(SetupManager setup) {
        this.sceneMaster = setup;
        this.score = 0;
        this.level = 1;
        this.lives = 3;
        this.boardLabel = new Label[BOARD_HEIGHT][BOARD_WIDTH];
        this.userInput = new ArrayList<>();
        this.generator = new BasicPokemonGenerator();
        this.generatePokemon();
    }

    /**
     * Constructs this GameController with all necessary parameters
     *
     * @param level - the current level of the game
     * @param currentPokemon - the current Pokemon to be displayed
     * @param score - the current score
     * @param lives - the current num of lives
     * @param name - the name of this save file
     */
    @JsonCreator
    public GameController(
            @JsonProperty("level") int level,
            @JsonProperty("currentPokemon") Pokemon currentPokemon,
            @JsonProperty("score") int score,
            @JsonProperty("lives") int lives,
            @JsonProperty("name") String name) {
        this.level = level;
        this.currentPokemon = currentPokemon;
        this.score = score;
        this.lives = lives;
        this.name = name;
        this.userInput = new ArrayList<>();
        this.generator = new BasicPokemonGenerator();
        this.boardLabel = new Label[BOARD_HEIGHT][BOARD_WIDTH];
    }

    /**
     * Initializes this controller's SetupManager
     *
     * @param sceneMaster - the setup manager
     */
    public void initSceneMaster(SetupManager sceneMaster) {
        // need reference back to setup manager to switch scenes when necessary
        this.sceneMaster = sceneMaster;
    }

    /**
     * Runs this controller
     */
    public void run() {
        this.initBackground();
        this.setLabels();
        if (!this.gameOver) {
            this.initButtons();
            this.initBoard();
            this.clearInput();
            this.showAnimation();
        }
    }

    /**
     * Initializes the background of the scene
     */
    private void initBackground() {
        this.backdrop.setBackground(
                BackgroundUtil.loadImage("src/main/resources/BackgroundFiles/game-bkgrd.png"));
    }

    /**
     * Sets the name associated with this game
     *
     * @param name - the name of this game file
     */
    public void setGameName(String name) {
        this.name = name;
    }

    /**
     * Sets the text on the labels on screen
     */
    private void setLabels() {
        if (this.lives == 0) {
            this.gameOver = true;
            this.sceneMaster.swapToGameOver(this.score, this.name);
            return;
        }
        this.scoreLabel.setText("Score: " + this.score);
        this.livesLabel.setText("Lives: " + this.lives);

    }

    /**
     * Initializes the board with labels
     */
    private void initBoard() {
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                GridCell label = new GridCell(new Coord(col, row));
                label.init();
                label.setOnMouseClicked(e -> handleCellClicked(label));
                this.board.add(label, col, row);
                this.boardLabel[row][col] = label;
            }
        }
    }

    /**
     * Handles the event when a cell is clicked by keeping track
     * of what cells the user has pressed
     *
     * @param cell - the cell pressed
     */
    private void handleCellClicked(GridCell cell) {
        if (this.submit.isVisible()) {
            cell.highlight();
            this.userInput.add(cell);
        }
    }

    /**
     * Hides the buttons on the screen
     */
    private void hideButtons() {
        this.submit.setVisible(false);
        this.repeat.setVisible(false);
        this.clear.setVisible(false);
        this.exitButton.setVisible(false);
    }

    /**
     * Shows the buttons on the screen
     */
    private void showButtons() {
        this.submit.setVisible(true);
        this.repeat.setVisible(true);
        this.clear.setVisible(true);
        this.exitButton.setVisible(true);
    }

    /**
     * Initializes the event action handlers for each button
     */
    private void initButtons() {
        this.exitButton.setOnAction(e -> {
            this.sceneMaster.endGame();
        });
        this.hideButtons();
        this.submit.setOnAction(e -> {
            this.handleSubmit();
        });
        this.repeat.setOnAction(e -> {
            this.handleRepeat();
        });
        this.clear.setOnAction(e -> {
            this.clearInput();
        });
    }

    /**
     * Handles when repeat button is pressed (shows animation again)
     */
    private void handleRepeat() {
        this.clearInput();
        this.showAnimation();
    }

    /**
     * Handles when submit button is pressed (verifies answer)
     */
    private void handleSubmit() {
        boolean correct = StepsVerifier.verify(this.sequenceAnswer, this.userInput);
        if (correct) {
            this.positiveMessage();
            this.score += this.userInput.size();
            this.setLabels();
            this.nextLevel();
        } else {
            this.incorrectMessage();
            this.lives--;
            this.setLabels();
        }
        this.clearInput();
    }

    /**
     * Clears user input by resetting color selection on board
     */
    private void clearInput() {
        for (GridCell gc : this.userInput) {
            gc.setDefaultHighlight();
        }
        this.userInput.clear();
    }

    /**
     * Generates a random Pokemon and instructs it to take steps
     */
    private void generatePokemon() {
        this.currentPokemon = this.generator.generate();
        this.currentPokemon.takeSteps(this.level, BOARD_WIDTH, BOARD_HEIGHT);
    }

    /**
     * Shows the animation
     */
    private void showAnimation() {
        this.hideButtons();
        this.displayGuide();
        this.sequenceAnswer = this.currentPokemon.getSteps();
        this.sequenceIterator = this.sequenceAnswer.iterator();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.75), e -> this.nextTurn());
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(2 * this.currentPokemon.getSteps().size());
        timeline.playFromStart();
    }

    /**
     * Advances the animation to the next turn
     */
    private void nextTurn() {
        try {
            if (this.currentPokemon.getVisible()) {
                Label label = this.boardLabel[currentPos.getY()][currentPos.getX()];
                label.setGraphic(null);
                this.currentPokemon.setVisible(false);
                if (!this.sequenceIterator.hasNext()) {
                    this.showButtons();
                }
            } else {
                if (this.sequenceIterator.hasNext()) {
                    this.currentPos = this.sequenceIterator.next();
                    Label label = this.boardLabel[currentPos.getY()][currentPos.getX()];
                    label.setGraphic(this.currentPokemon.produceImage(CELL_HEIGHT, CELL_WIDTH));
                    this.currentPokemon.setVisible(true);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not produce image of current pokemon.");
        }

    }

    /**
     * Advances the player to the next level
     */
    private void nextLevel() {
        this.level++;
        this.generatePokemon();
        this.showAnimation();
    }

    /**
     * Instructs view elements to display text to guide the user
     */
    private void displayGuide() {
        this.guide.setText("Follow " + this.currentPokemon.getName()
                + "!\nTrait: " + this.currentPokemon.getDescription());
        try {
            this.guide.setGraphic(this.currentPokemon.produceImage(2 * CELL_HEIGHT, 2 * CELL_WIDTH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not produce image of current pokemon.");
        }
    }

    /**
     * Display message when user is incorrect
     */
    private void incorrectMessage() {
        this.instructions.setText(
                "Try again! Click 'Repeat Animation' to see the animation again.");
    }

    /**
     * Displays message when user is correct
     */
    private void positiveMessage() {
        this.instructions.setText("Nice work!");
    }

    /**
     * Gets this game's level (for json)
     *
     * @return - the level
     */
    public int getLevel() {
        return this.level;
    }

    /**
     * Gets the current Pokemon shown on the animation (for json)
     *
     * @return - the current Pokemon
     */
    public Pokemon getCurrentPokemon() {
        return this.currentPokemon;
    }

    /**
     * Gets this game's score (for json)
     *
     * @return - the score
     */
    public int getScore() {
        return this.score;
    }

    /**
     * Gets the number of lives remaining in this game (for json)
     *
     * @return - the number of lives
     */
    public int getLives() {
        return this.lives;
    }

    /**
     * Gets the name of this game save file (for json)
     *
     * @return - the name of the game file
     */
    public String getName() {
        return this.name;
    }

}
