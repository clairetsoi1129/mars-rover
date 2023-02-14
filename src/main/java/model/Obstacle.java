package model;

import java.awt.*;

public class Obstacle implements Component {
    private Point location;

    public Obstacle(Point location) {
        this.location = location;
    }

    public Point getLocation() {
        return location;
    }
}
