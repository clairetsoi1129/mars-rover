package model;

import ui.Game;

import java.awt.*;

public class AScene {
    protected Dimension dimension;

    protected Game game;

    public AScene(Dimension dimension, Game game){
        this.dimension = dimension;
        this.game = game;
    }

    public boolean reachBoundary(Point position){
        return position.getX() > dimension.getWidth() || position.getY() > dimension.getHeight()
                || position.getX() < 0 || position.getY() < 0;
    }

    public Dimension getDimension() {
        return dimension;
    }
}
