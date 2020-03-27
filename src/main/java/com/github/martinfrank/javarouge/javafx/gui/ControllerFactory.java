package com.github.martinfrank.javarouge.javafx.gui;

import javafx.util.Callback;

public class ControllerFactory implements Callback<Class<?>, Object> {


    private RootController rootController;

    public ControllerFactory() {
    }

    @Override
    public Object call(Class<?> type) {
        if (type == RootController.class) {
            rootController = new RootController();
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

    public RootController getRootController() {
        return rootController;
    }


}
