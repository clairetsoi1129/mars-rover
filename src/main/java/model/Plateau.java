package model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Plateau extends AScene {
    private List<Sample> samples;
    private List<Obstacle> obstacles;

    public Plateau(Dimension dimension){
        super(dimension);
        generateSample();
        generateObstacle();
    }

    private void generateSample(){
        samples = new ArrayList<>();
        samples.add(new Sample(new Point(2,5)));
        samples.add(new Sample(new Point(1,3)));
    }

    private void generateObstacle(){
        obstacles = new ArrayList<>();
        obstacles.add(new Obstacle(new Point(2,2)));
        obstacles.add(new Obstacle(new Point(3,3)));
        obstacles.add(new Obstacle(new Point(4,4)));
    }

    public boolean hasObstacle(Point roverPosition){
        boolean hasObstacle = false;
        for (Obstacle obstacle : obstacles) {
            if (roverPosition.equals(obstacle.getLocation())) {
                hasObstacle = true;
            }
        }
        return hasObstacle;
    }

    public Sample digSample(Point location){
        Sample tmpSample = null;

        for (Sample sample : samples) {
            if (sample.getLocation().equals(location)) {
                tmpSample = sample;
                tmpSample.setDigged(true);
            }
        }
        return tmpSample;
    }

    public List<Sample> getSamples() {
        return samples;
    }

    public List<Obstacle> getObstacles() {
        return obstacles;
    }
}
