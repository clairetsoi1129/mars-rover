package ui;

import input.GUI;
import model.AScene;
import model.Obstacle;
import model.Plateau;
import model.Sample;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class PlateauUI {
    private AScene plateau;
    private GUI gui;

    private List<ObstacleUI> obstacleUIs;
    private List<SampleUI> sampleUIs;

    public PlateauUI(AScene plateau, GUI gui) {
        this.plateau = plateau;
        this.gui = gui;
        initUI();
        initObstacleUI();
        initSampleUI();
    }

    private void initUI(){
        JPanel panel = gui.getBgPanel()[1];
        Dimension dim = plateau.getDimension();
        for (int row = 0; row < dim.width; row++) {
            for (int col = 0; col < dim.height; col++) {
                JLabel gridLabel = new JLabel();
                Point pos = gui.getGridPos(row, col);
                gridLabel.setBounds(pos.x, pos.y, 50, 50);
                gridLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                panel.add(gridLabel);
            }
        }
    }

    private void initObstacleUI(){
        obstacleUIs = new ArrayList<ObstacleUI>();
        List<Obstacle> obstacles = ((Plateau)plateau).getObstacles();
        for (Obstacle obstacle : obstacles) {
            obstacleUIs.add(new ObstacleUI(obstacle, gui));
        }
    }

    private void initSampleUI(){
        sampleUIs = new ArrayList<>();
        List<Sample> samples = ((Plateau)plateau).getSamples();
        for (Sample sample : samples) {
            sampleUIs.add(new SampleUI(sample, gui));
        }
    }


    public void updateSampleUI(){
        List<Sample> samples = ((Plateau)plateau).getSamples();
        for (int i=0; i<samples.size(); i++) {
            if (samples.get(i).isDigged()) {
                sampleUIs.get(i).remove();
            }
        }
    }
}
