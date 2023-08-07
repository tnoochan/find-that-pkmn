package ftp.Controller;

import ftp.Model.Json.GameRecordsJson;
import ftp.Model.Json.GameStateJson;
import ftp.View.GameOverView;
import ftp.View.GameView;
import ftp.View.IntroView;
import ftp.View.LoadView;
import ftp.View.RecordsView;
import ftp.View.StartView;
import ftp.View.WindowView;
import java.nio.file.Path;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 * Sets up the application and controls when to display and run which windows
 */
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

    /**
     * Constructs a setup manager, default initializes all variables
     */
    public SetupManager() {
        this.gameControl = new GameController(this);
        this.initControlsAndView();
    }

    /**
     * Initializes connection with each controller and view objects
     */
    private void initControlsAndView() {
        this.findRecords();
        this.loadControl = new LoadController(this);
        this.startControl = new StartController(this);
        this.introControl = new IntroController(this);
        // this.gameOverControl = new GameOverController(this);
        this.gameView = new GameView(this.gameControl);
        this.startView = new StartView(this.startControl);
        this.recordsView = new RecordsView(this.recordsControl);
        this.loadView = new LoadView(this.loadControl);
        this.introView = new IntroView(this.introControl);
        // this.gameOverView = new GameOverView(this.gameOverControl);
    }

    /**
     * Attempts to find previous records that have been saved,
     * and establishes the proper records controller
     */
    private void findRecords() {
        Path path = Path.of("recordsonly/toprecords");
        try {
            GameRecordsJson record = TopRecordsOperator.read(path);
            this.recordsControl = record.recordsController();
            this.recordsControl.initSceneMaster(this);
        } catch (IllegalArgumentException e) {
            this.recordsControl = new RecordsController(this);
        }
    }

    /**
     * Starts the application
     *
     * @param stage the primary stage for this application, onto which
     *     the application scene can be set.
     *     Applications may create other stages, if needed, but they will not be
     *     primary stages.
     */
    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.initNodes();
        Scene scene = new Scene(this.startNode, 1000, 600);
        this.stage.setScene(scene);
        this.startControl.run();
        this.stage.show();
    }

    /**
     * Initializes each view node
     */
    private void initNodes() {
        this.startNode = this.startView.load();
        this.recordsNode = this.recordsView.load();
        this.gameNode = this.gameView.load();
        this.loadNode = this.loadView.load();
        this.introNode = this.introView.load();
        //this.gameOverNode = this.gameOverView.load();
    }

    /**
     * Swaps to the game screen
     *
     * @param name - the name of the save file
     */
    public void swapToGame(String name) {
        this.stage.getScene().setRoot(this.gameNode);
        this.gameControl.setGameName(name);
        this.gameControl.run();
    }

    /**
     * Swaps to the records screen
     */
    public void swapToRecords() {
        this.stage.getScene().setRoot(this.recordsNode);
        this.recordsControl.run();
    }

    /**
     * Swaps to the load game screen
     */
    public void swapToLoadGame() {
        this.stage.getScene().setRoot(this.loadNode);
        this.loadControl.run();
    }

    /**
     * Swaps back to this main screen
     */
    public void swapMainScreen() {
        this.stage.getScene().setRoot(this.startNode);
    }

    /**
     * Swaps to the game over scene
     *
     * @param finalScore - the score to display on the game over screen
     * @param name - the name of the save file
     */
    public void swapToGameOver(int finalScore, String name) {
        this.recordsControl.addEntry(finalScore, name);
        this.updateRecords();
        this.gameOverControl = new GameOverController(this);
        this.gameOverView = new GameOverView(this.gameOverControl);
        this.gameOverNode = this.gameOverView.load();
        this.stage.getScene().setRoot(this.gameOverNode);
        this.gameOverControl.setFinalScore(finalScore);
        this.gameOverControl.run();
        // remove save file to prevent user from opening it again, after losing
        this.loadControl.remove(name);
    }

    /**
     * Swaps to intro screen
     */
    public void swapToIntro() {
        this.stage.getScene().setRoot(this.introNode);
        this.introControl.run();
    }

    /**
     * Ends the game by saving the game data, updating records, and closing
     * the application
     */
    public void endGame() {
        GameStateJson savedData = new GameStateJson(this.gameControl);
        GameFileOperator.write(savedData,
                Path.of("savefiles/" + this.gameControl.getName()));
        this.updateRecords();
        this.endApplication();
    }

    /**
     * Updates the records
     */
    public void updateRecords() {
        GameRecordsJson savedData = new GameRecordsJson(this.recordsControl);
        TopRecordsOperator.write(savedData, Path.of("recordsonly/toprecords"));
    }

    /**
     * Ends the application
     */
    public void endApplication() {
        Platform.exit();
    }

    /**
     * Loads the content from the supplied json record
     *
     * @param record - the record to deserialize and extract data from
     */
    public void loadContent(GameStateJson record) {
        // .loadContent() most likely called after all of these
        // fields have been established, so overwriting
        GameController old = record.mainController();
        this.gameControl = old;
        this.gameControl.initSceneMaster(this);
        this.gameView = new GameView(this.gameControl);
        this.gameNode = this.gameView.load();
        this.stage.getScene().setRoot(this.gameNode);
        this.gameControl.run();
    }


}
