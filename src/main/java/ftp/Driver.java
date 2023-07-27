package ftp;

import ftp.Controller.SetupManager;
import javafx.application.Application;

/**
 * Program entry point
 */
public class Driver {

    /**
     * Entry point
     *
     * @param args - cmd line arguments
     */
    public static void main(String[] args) {
        Application.launch(SetupManager.class, args);
    }
}
