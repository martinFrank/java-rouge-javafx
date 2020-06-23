package com.github.martinfrank.javarouge.javafx.control;

import com.github.martinfrank.javarouge.javafx.view.MapCanvas;
import com.github.martinfrank.javarouge.model.RougeGame;
import com.github.martinfrank.javarouge.model.message.Message;
import com.github.martinfrank.javarouge.model.message.MessageConsumer;
import com.github.martinfrank.maplib.Direction;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.VBox;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Controller implements MessageConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(Controller.class);
    private static final String STEERING_PREFIX = "steering";

    private final RougeGame game;

    @FXML
    public ScrollPane mapScrollPane;

    @FXML
    public VBox vbox;

    @FXML
    public HBox hbox;

    @FXML
    private MapCanvas mapCanvas;

    @FXML
    private Pane steering;


    Controller(RougeGame game) {
        this.game = game;
        this.game.getMap().scale(12);
        this.game.registerMessageConsumer(this);
    }


    public void init() {
        mapCanvas.setPlayer(game.getPlayer());
        mapCanvas.setMap(game.getMap());
        mapCanvas.drawMap();

        VBox.setVgrow(hbox, Priority.ALWAYS);
        HBox.setHgrow(mapScrollPane, Priority.ALWAYS);


        includeSteering();

        mapCanvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            LOGGER.debug("klicked");
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
        });

    }

    private void includeSteering() {
        steering.getChildren().forEach(n -> ((Button) n).setOnAction(
                event -> steer(n.getId().replace(STEERING_PREFIX, ""))));
    }

    private void steer(String dir) {
        game.movePlayer(Direction.valueOf(dir));
        mapCanvas.drawMap();
    }

    @Override
    public void receive(Message message) {
        LOGGER.debug("Message from game: " + message.type + (message.content == null ? "" : " " + message.content));
    }
}
