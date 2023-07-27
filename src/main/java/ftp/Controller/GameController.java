package ftp.Controller;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;

public class GameController {

    private static final int BOARD_WIDTH = 6;
    private static final int BOARD_HEIGHT = 6;
    private static final int LABEL_WIDTH = 100;
    private static final int LABEL_HEIGHT = 60;
    private static final int DEFAULT_INSET = 3;

    @FXML
    private Button exitButton;
    @FXML
    private GridPane board;
    @FXML
    private Label instructions;

    Label[][] boardLabels = new Label[BOARD_HEIGHT][BOARD_WIDTH];


    public void run() {
        this.initButtons();
        this.initBoard();
    }

    private void initBoard() {
        for (int row = 0; row < BOARD_HEIGHT; row++) {
            for (int col = 0; col < BOARD_WIDTH; col++) {
                Label label = new Label("" + row + "," + col);
                label.setMinHeight(LABEL_HEIGHT);
                label.setMinWidth(LABEL_WIDTH);
                label.setBackground(new Background(
                        new BackgroundFill(Color.rgb(211, 211, 211, .5), CornerRadii.EMPTY,
                                new Insets(DEFAULT_INSET))));
                label.setOnMouseClicked(e -> handleCellClicked(e));
                boardLabels[row][col] = label;
                this.board.add(label, col, row);

            }
        }
    }

    private void handleCellClicked(MouseEvent e) {
        System.out.println("cell clicked!");
    }

    private void initButtons() {
        this.exitButton.setOnAction(e -> {
            System.out.println("Saving, exiting");
            Platform.exit();
        });
    }

}
