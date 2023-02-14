package model;

import java.awt.*;

public class Sample implements IComponent{
    private Point location;
    private boolean isDigged;

    public Sample(Point location) {
        this.location = location;
        this.isDigged = false;
    }

    public Point getLocation() {
        return location;
    }

    public boolean isDigged() {
        return isDigged;
    }

    public void setDigged(boolean digged) {
        isDigged = digged;
    }
}
