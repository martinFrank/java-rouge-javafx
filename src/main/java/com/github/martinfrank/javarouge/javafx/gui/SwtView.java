package com.github.martinfrank.javarouge.javafx.gui;

import com.github.martinfrank.javarouge.view.RougeView;
import javafx.scene.layout.Pane;

public class SwtView implements RougeView<Pane> {

    public SwtView(Pane client) {
        setClient(client);
    }

    @Override
    public void setClient(Pane client) {

    }
}
