package ftp.Controller;

import ftp.View.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class SetupManager extends Application {

    Stage stage;
    StartController startControl;
    WindowView startView;
    GameController gameControl;
    WindowView gameView;
    RecordsController recordsControl;
    WindowView recordsView;
    LoadController loadControl;
    WindowView loadView;
    GameOverController gameOverControl;
    GameOverView gameOverView;

    Parent startNode;
    Parent recordsNode;
    Parent gameNode;
    Parent loadNode;
    Parent gameOverNode;


    public SetupManager() {
        this.startControl = new StartController(this);
        this.recordsControl = new RecordsController(this);
        this.loadControl = new LoadController(this);
        this.gameControl = new GameController(this);
        this.gameOverControl = new GameOverController(this);

        this.gameView = new GameView(this.gameControl);
        this.startView = new StartView(this.startControl);
        this.recordsView = new RecordsView(this.recordsControl);
        this.loadView = new LoadView(this.loadControl);
        this.gameOverView = new GameOverView(this.gameOverControl);
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.initNodes();
        Scene scene = new Scene(this.startNode, 1000, 600);
        this.stage.setScene(scene);
        this.startControl.run();
        this.stage.show();
    }

    private void initNodes() {
        this.startNode = this.startView.load();
        this.recordsNode = this.recordsView.load();
        this.gameNode = this.gameView.load();
        this.loadNode = this.loadView.load();
        this.gameOverNode = this.gameOverView.load();
    }

    public void swapToGame() {
        this.stage.getScene().setRoot(this.gameNode);
        this.gameControl.run();
    }

    public void swapToRecords() {
        this.stage.getScene().setRoot(this.recordsNode);
        this.recordsControl.run();
    }

    public void swapToLoadGame() {
        this.stage.getScene().setRoot(this.loadNode);
        this.loadControl.run();
    }

    public void swapMainScreen() {
        this.stage.getScene().setRoot(this.startNode);
    }

    public void swapToGameOver() {
        this.stage.getScene().setRoot(this.gameOverNode);
        this.gameOverControl.run();
    }

    public void endGame() {
        System.out.println("Ending game and saving");
        Platform.exit();
    }
}
