package com.github.martinfrank.javarouge.javafx.view;

import javafx.scene.canvas.GraphicsContext;

public abstract class Renderer {

    private final GraphicsContext graphicsContext;

    public Renderer(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
    }

    GraphicsContext getGraphicsContext() {
        return graphicsContext;
    }
}
