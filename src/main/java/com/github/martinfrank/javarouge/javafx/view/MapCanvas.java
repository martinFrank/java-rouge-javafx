package com.github.martinfrank.javarouge.javafx.view;

import com.github.martinfrank.drawlib.Point;
import com.github.martinfrank.javarouge.model.Player;
import com.github.martinfrank.javarouge.model.map.RougeMap;
import com.github.martinfrank.javarouge.model.map.RougeMapEdge;
import com.github.martinfrank.javarouge.model.map.RougeMapField;
import com.github.martinfrank.javarouge.model.map.RougeMapNode;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class MapCanvas extends Canvas {

    private final FieldRenderer fieldRenderer;
    private final PlayerRenderer playerRenderer;

    @SuppressWarnings("unused")
    private static final Logger LOGGER = LoggerFactory.getLogger(MapCanvas.class);

    private RougeMap map;
    private Player player;

    public MapCanvas() {
        super();
        fieldRenderer = new FieldRenderer(getGraphicsContext2D());
        playerRenderer = new PlayerRenderer(getGraphicsContext2D());
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public void setMap(RougeMap map) {
        this.map = map;
        int h = (int) map.getTransformed().getHeight();
        int w = (int) map.getTransformed().getWidth();
        setHeight(h);
        setWidth(w);
    }

    public void drawMap() {
        if (map != null) {
            map.getFields().forEach(this::drawField);
        }
    }

    private void drawNode(RougeMapNode node) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setStroke(Color.RED);
        gc.setLineWidth(5);
        Point point = node.getPoint().getTransformed();
        gc.strokeLine(point.getX(), point.getY(), point.getX(), point.getY());
    }

    private void drawEdge(RougeMapEdge edge) {
        GraphicsContext gc = getGraphicsContext2D();
        gc.setFill(Color.GREEN);
        gc.setStroke(Color.BLACK);
        gc.setLineWidth(1);

        Point a = edge.getLine().getTransformed().getA();
        Point b = edge.getLine().getTransformed().getB();
        gc.strokeLine(a.getX(), a.getY(), b.getX(), b.getY());
    }

    private void drawField(RougeMapField field) {
        fieldRenderer.render(field);
        if (player != null && field.getIndex().equals(player.getPosition())) {
            playerRenderer.render(player, field);
        }

        field.getEdges().forEach(this::drawEdge);
//        field.getNodes().forEach(this::drawNode);

    }


//    public MouseSelection getSelectionAt(int x, int y) {
//        MouseSelection selection = new MouseSelection(x, y);
//        if (map != null) {
//            selection.setNode(map.getNodeAt(x, y));
//            selection.setEdge(map.getEdgeAt(x, y));
//            selection.setField(map.getFieldAt(x, y));
//        }
//        return selection;
//    }

    private static Color fromArgb(int argb) {
        double a = Math.abs((argb & 0xFF000000) / 4278190080d); //FIXME imprecise
        double r = (argb & 0x00FF0000) / 16711680d;
        double g = (argb & 0x0000FF00) / 65280d;
        double b = (argb & 0x000000FF) / 255d;
        LOGGER.debug("a {}, r {}, g {}, b {}", a, r, b, g);
        return new Color(r, g, b, a);
    }

}
