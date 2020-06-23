package com.github.martinfrank.javarouge.javafx;

import com.github.martinfrank.javarouge.javafx.control.Controller;
import com.github.martinfrank.javarouge.javafx.control.ControllerFactory;
import com.github.martinfrank.javarouge.javafx.res.ResourceManager;
import com.github.martinfrank.javarouge.model.Player;
import com.github.martinfrank.javarouge.model.RougeGame;
import com.github.martinfrank.javarouge.save.SaveGame;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class App extends Application {

    private static final Logger LOGGER = LoggerFactory.getLogger(App.class);

    public static void main(String[] args) {
        launch(args);
    }

    private Pane root;

    @Override
    public void init() {
        RougeGame game = new RougeGame();
        SaveGame saveGame = new SaveGame(new Player("mosh"));
        game.loadSaveGame(saveGame);

        ResourceManager resourceManager = new ResourceManager(getClass().getClassLoader());
        ControllerFactory controllerFactory = new ControllerFactory(game);
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(resourceManager.getGuiRoot());
            fxmlLoader.setControllerFactory(controllerFactory);
            root = fxmlLoader.load();
        } catch (IOException e) {
            LOGGER.debug("error", e);
        }
        Controller controller = controllerFactory.getRootController();
        controller.init();
    }

    @Override
    public void start(Stage stage) {
        if (root != null) {
            stage.setScene(new Scene(root));
            stage.setTitle("tbd: set title");
            stage.show();
        } else {
            LOGGER.debug("error during init");
            Platform.exit();
            System.exit(0);
        }
    }

}
