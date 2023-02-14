package model;

import java.awt.*;

public class Obstacle implements IComponent{
    private Point location;

    public Obstacle(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
