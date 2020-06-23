package com.github.martinfrank.javarouge.javafx.control;

import com.github.martinfrank.javarouge.model.RougeGame;
import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {


    private Controller rootController;

    private final RougeGame game;

    public ControllerFactory(RougeGame game) {
        this.game = game;
    }

    @Override
    public Object call(Class<?> type) {
        if (type == Controller.class) {
            rootController = new Controller(game);
            return rootController;
        } else {
            // default behavior for controllerFactory:
            try {
                return type.getDeclaredConstructor().newInstance();
            } catch (Exception exc) {
                exc.printStackTrace();
                throw new RuntimeException(exc); // fatal, just bail...
            }
        }
    }

    public Controller getRootController() {
        return rootController;
    }


}
