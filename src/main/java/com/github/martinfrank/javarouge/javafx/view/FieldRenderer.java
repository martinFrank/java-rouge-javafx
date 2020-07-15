package com.github.martinfrank.javarouge.javafx.view;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.drawlib.Shape;
import com.github.martinfrank.javarouge.model.entity.Entity;
import com.github.martinfrank.javarouge.model.entity.Item;
import com.github.martinfrank.javarouge.model.map.RougeMapField;
import com.github.martinfrank.javarouge.model.maze.Structure;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

import java.util.List;

public class FieldRenderer extends Renderer {

    public FieldRenderer(GraphicsContext graphicsContext) {
        super(graphicsContext);
    }

    public void render(RougeMapField field) {
        GraphicsContext gc = getGraphicsContext();
        Structure structure = field.getData().getStructure();
        gc.setFill(Color.GRAY);
        if (structure != null) {
            if (structure.getType() == Structure.Type.ROOM) {
                gc.setFill(Color.DARKGRAY.brighter());
            }
            if (structure.getType() == Structure.Type.CORRIDOR) {
                gc.setFill(Color.DARKGRAY);
            }
            if (structure.getType() == Structure.Type.ROOM_OUTER_BORDER) {
                gc.setFill(Color.RED);
            }
            if (structure.getType() == Structure.Type.ROOM_INNER_BORDER) {
                gc.setFill(Color.WHITE);
            }
            if (structure.getType() == Structure.Type.ROOM_OUTER_CORNER) {
                gc.setFill(Color.BLUE);
            }
//            if (structure.getType() == Structure.Type.BORDER){
//                gc.setFill(Color.YELLOW);
//            }
        }

        gc.setStroke(Color.DARKGRAY);
        gc.setLineWidth(1);


        Shape shape = field.getShape().getTransformed();
        double[] xs = shape.getPoints().stream().mapToDouble(Point::getX).toArray();
        double[] ys = shape.getPoints().stream().mapToDouble(Point::getY).toArray();
        int amount = xs.length;
        gc.fillPolygon(xs, ys, amount);


        List<Item> items = field.getData().getItems();
        Point c = shape.getCenter();
        gc.setFill(Color.ORANGE);
        for (Item item : items) {
            gc.fillText(item.getPrototype().getItemsample().getName(), c.getX(), c.getY());
        }

        List<Entity> entities = field.getData().getEntities();
        gc.setFill(Color.RED);
        for (Entity entity : entities) {
            gc.fillText(entity.getPrototype().getMonstersample().getName(), c.getX(), c.getY());
        }
    }
}
