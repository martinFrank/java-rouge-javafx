package com.github.martinfrank.javarouge.javafx.view;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Shape;
import com.github.martinfrank.javarouge.model.Player;
import com.github.martinfrank.javarouge.model.map.RougeMapField;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class PlayerRenderer extends Renderer {

    public PlayerRenderer(GraphicsContext graphicsContext2D) {
        super(graphicsContext2D);
    }

    public void render(Player player, RougeMapField field) {
        GraphicsContext gc = getGraphicsContext();

        gc.setFill(Color.PURPLE);

        Shape shape = field.getShape().getTransformed();
        double[] xs = shape.getPoints().stream().mapToDouble(Point::getX).toArray();
        double[] ys = shape.getPoints().stream().mapToDouble(Point::getY).toArray();
        int amount = xs.length;
        gc.fillPolygon(xs, ys, amount);
    }

}
