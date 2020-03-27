package com.github.martinfrank.javarouge.javafx.gui;

import com.github.martinfrank.javarouge.controller.RougeController;
import com.github.martinfrank.javarouge.model.map.RougeMap;
import com.github.martinfrank.javarouge.save.SaveGame;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class RootController {

    private static final Logger LOGGER = LoggerFactory.getLogger(RootController.class);
//    private GuiEventListener eventListener;
//    private RougeGame rougeGame;

    public void setMapCanvas(RougeMapCanvas mapCanvas) {
        this.mapCanvas = mapCanvas;
    }

    private RougeController<Pane> rougeController;

    @FXML
    private RougeMapCanvas mapCanvas;

    @FXML
    private TextArea console;


    public RootController() {
//        setGuiEventListener(civGame);
    }


    public void init() {
        LOGGER.debug("mapCanvas: {}", mapCanvas);
        mapCanvas.addEventFilter(MouseEvent.MOUSE_PRESSED, mouseEvent -> {
            int x = (int) mouseEvent.getX();
            int y = (int) mouseEvent.getY();
//            MouseSelection selection = mapCanvas.getSelectionAt(x, y);
//            eventListener.mouseSelect(selection);
        });
//        setMap(civGame.getMap());
    }

    public void setMap(RougeMap map) {
        mapCanvas.setMap(map);
        redrawMap();
    }

//    public void setGuiEventListener(GuiEventListener eventListener) {
//        this.eventListener = eventListener;
//    }

    public void redrawMap() {
        mapCanvas.drawMap();
    }

    public void clearConsole() {
        console.clear();
    }

//    public void endTurnButton(ActionEvent actionEvent) {
//        civGame.endTurn();
//        redrawMap();
//    }

    @FXML
    public void endTurnButton(ActionEvent actionEvent) {
//        civGame.endTurn();
        redrawMap();
    }

    public void setRougeController(RougeController<Pane> rougeController) {
        this.rougeController = rougeController;
    }

    public void loadSaveGame(SaveGame saveGame) {
        rougeController.loadSaveGame(saveGame);
    }
}
