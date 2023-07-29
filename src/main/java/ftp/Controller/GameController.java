package ftp.Controller;

import ftp.Model.Coord;
import ftp.Model.GridCell;
import ftp.Model.Pokemon;
import ftp.Model.Scorbunny;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.util.Duration;

public class GameController {

    private static final int BOARD_WIDTH = 6;
    private static final int BOARD_HEIGHT = 6;

    private int level;
    private Pokemon currentPokemon;
    private Coord currentPos;
    private List<Coord> sequenceAnswer;
    private Iterator<Coord> sequenceIterator;

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

    private int score;
    private int lives;
    private List<GridCell> userInput;
    private Label[][] boardLabel;

    public GameController() {
        this.score = 0;
        this.level = 1;
        this.lives = 3;
        this.boardLabel = new Label[BOARD_HEIGHT][BOARD_WIDTH];
        this.userInput = new ArrayList<>();
        this.generatePokemon();
    }

    public void run() {
        this.setLabels();
        this.initButtons();
        this.initBoard();
        this.clearInput();
        this.showAnimation();
    }

    private void setLabels() {
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
            System.out.println("x: " + cell.getPos().getX() + ", y: " + cell.getPos().getY());
            cell.highlight();
            this.userInput.add(cell);
        }
    }

    private void hideButtons() {
        this.submit.setVisible(false);
        this.repeat.setVisible(false);
        this.clear.setVisible(false);
    }

    private void showButtons() {
        this.submit.setVisible(true);
        this.repeat.setVisible(true);
        this.clear.setVisible(true);
    }

    private void initButtons() {
        this.exitButton.setOnAction(e -> {
            System.out.println("Saving, exiting");
            Platform.exit();
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
        List<Coord> ans = this.userInput.stream().map(e -> e.getPos()).toList();
        if (ans.equals(this.sequenceAnswer)) {
            this.score += ans.size();
            this.setLabels();
            this.nextLevel();
            System.out.println("same answer!!!");
        } else {
            this.lives--;
            this.setLabels();
            System.out.println("wrong answer");
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
        this.currentPokemon = new Scorbunny();
        this.currentPokemon.takeSteps(this.level, BOARD_WIDTH, BOARD_HEIGHT);
    }

    private void showAnimation() {
        this.hideButtons();
        this.sequenceAnswer = this.currentPokemon.getSteps();
        this.sequenceIterator = this.sequenceAnswer.iterator();
        KeyFrame keyFrame = new KeyFrame(Duration.seconds(0.75), e -> this.nextTurn());
        Timeline timeline = new Timeline(keyFrame);
        timeline.setCycleCount(2 * this.currentPokemon.getSteps().size());
        timeline.playFromStart();

    }

    private void nextTurn() {
        if (this.currentPokemon.getVisible()) {
            this.boardLabel[currentPos.getY()][currentPos.getX()].setText("");
            this.currentPokemon.setVisible(false);
            if (!this.sequenceIterator.hasNext()) {
                this.showButtons();
            }
        } else {
            if (this.sequenceIterator.hasNext()) {
                this.currentPos = this.sequenceIterator.next();
                this.boardLabel[currentPos.getY()][currentPos.getX()].setText("HERE");
                this.currentPokemon.setVisible(true);
            }
        }
    }

    private void nextLevel() {
        this.level++;
        this.generatePokemon();
        this.showAnimation();
    }
}
