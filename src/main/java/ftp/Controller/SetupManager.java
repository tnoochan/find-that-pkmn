package ftp.Controller;

import ftp.Model.Json.GameStateJson;
import ftp.View.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.nio.file.Path;

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
    IntroController introControl;
    WindowView introView;

    Parent startNode;
    Parent recordsNode;
    Parent gameNode;
    Parent loadNode;
    Parent gameOverNode;
    Parent introNode;

//    @JsonCreator
//    public SetupManager(
//            @JsonProperty("gameControl") GameController gameControl,
//            @JsonProperty("recordsControl") RecordsController recordsControl) {
//        this.gameControl = gameControl;
//        this.recordsControl = recordsControl;
//        this.initControlsAndView();
//    }


    public SetupManager() {
        this.recordsControl = new RecordsController(this);
        this.gameControl = new GameController(this);
        this.initControlsAndView();
    }

    private void initControlsAndView() {
        this.loadControl = new LoadController(this);
        this.startControl = new StartController(this);
        this.introControl = new IntroController(this);
//        this.gameOverControl = new GameOverController(this);
        this.gameView = new GameView(this.gameControl);
        this.startView = new StartView(this.startControl);
        this.recordsView = new RecordsView(this.recordsControl);
        this.loadView = new LoadView(this.loadControl);
        this.introView = new IntroView(this.introControl);

        //this.gameOverView = new GameOverView(this.gameOverControl);
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
        this.introNode = this.introView.load();
        //this.gameOverNode = this.gameOverView.load();
    }

    public void swapToGame(String name) {
        this.stage.getScene().setRoot(this.gameNode);
        this.gameControl.setGameName(name);
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

    public void swapToGameOver(int finalScore) {
        this.gameOverControl = new GameOverController(this);
        this.gameOverView = new GameOverView(this.gameOverControl);
        this.gameOverNode = this.gameOverView.load();
        this.stage.getScene().setRoot(this.gameOverNode);
        this.gameOverControl.setFinalScore(finalScore);
        this.gameOverControl.run();
    }

    public void swapToIntro() {
        this.stage.getScene().setRoot(this.introNode);
        this.introControl.run();
    }
    public void endGame() {
        GameStateJson savedData = new GameStateJson(this.gameControl);
        GameFileOperator.write(savedData,
                Path.of("savefiles/" + this.gameControl.getName()));
        Platform.exit();
    }

    public void loadContent(GameStateJson record) {
        GameController old = record.mainController();
        this.gameControl = old;
        this.gameControl.initSceneMaster(this);
        this.gameView = new GameView(this.gameControl);
        this.gameNode = this.gameView.load();
        this.stage.getScene().setRoot(this.gameNode);
        this.gameControl.run();
    }

}
