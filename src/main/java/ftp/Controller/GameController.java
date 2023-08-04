package ftp.Controller;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import ftp.Model.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

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

    private int score;
    private int lives;
    private List<GridCell> userInput;
    private Label[][] boardLabel;

    public GameController(SetupManager s) {
        this.sceneMaster = s;
        this.score = 0;
        this.level = 1;
        this.lives = 3;
        this.boardLabel = new Label[BOARD_HEIGHT][BOARD_WIDTH];
        this.userInput = new ArrayList<>();
        this.generator = new BasicPokemonGenerator();
        this.generatePokemon();
    }

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

    public void initSceneMaster(SetupManager sceneMaster) {
        this.sceneMaster = sceneMaster;
    }

    public void run() {
        this.setLabels();
        if (!this.gameOver) {
            this.initButtons();
            this.initBoard();
            this.clearInput();
            this.showAnimation();
        }
    }

    public void setGameName(String name) {
        this.name = name;
    }

    private void setLabels() {
        if (this.lives == 0) {
            this.gameOver = true;
            this.sceneMaster.swapToGameOver(this.score);
            return;
        }
        this.scoreLabel.setText("Score: " + this.score);
        this.livesLabel.setText("Lives: " + this.lives);

    }

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

    private void handleCellClicked(GridCell cell) {
        if (this.submit.isVisible()) {
            cell.highlight();
            this.userInput.add(cell);
        }
    }

    private void hideButtons() {
        this.submit.setVisible(false);
        this.repeat.setVisible(false);
        this.clear.setVisible(false);
        this.exitButton.setVisible(false);
    }

    private void showButtons() {
        this.submit.setVisible(true);
        this.repeat.setVisible(true);
        this.clear.setVisible(true);
        this.exitButton.setVisible(true);
    }

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

    public void handleRepeat() {
        this.clearInput();
        this.showAnimation();
    }

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

    private void clearInput() {
        for (GridCell gc : this.userInput) {
            gc.setDefaultHighlight();
        }
        this.userInput.clear();
    }

    private void generatePokemon() {
        this.currentPokemon = this.generator.generate();
        this.currentPokemon.takeSteps(this.level, BOARD_WIDTH, BOARD_HEIGHT);
    }

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

    private void nextLevel() {
        this.level++;
        this.generatePokemon();
        this.showAnimation();
    }

    private void displayGuide() {
        this.guide.setText("Follow " + this.currentPokemon.getName() +
                "!\nTrait: " + this.currentPokemon.getDescription());
        try {
            this.guide.setGraphic(this.currentPokemon.produceImage(2*CELL_HEIGHT, 2*CELL_WIDTH));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Could not produce image of current pokemon.");
        }
    }

    private void incorrectMessage() {
        this.instructions.setText("Try again! Click 'Repeat Animation' to see the animation again.");
    }

    private void positiveMessage() {
        this.instructions.setText("Nice work!");
    }



    // for json

    public int getLevel() {
        return this.level;
    }

    public Pokemon getCurrentPokemon() {
        return this.currentPokemon;
    }

    public int getScore() {
        return this.score;
    }

    public int getLives() {
        return this.lives;
    }

    public String getName() {
        return this.name;
    }

}
