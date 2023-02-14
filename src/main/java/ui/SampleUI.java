package ui;

import input.GUI;
import model.Sample;

import javax.swing.*;
import java.awt.*;

public class SampleUI {
    private Sample sample;
    private final String SAMPLE_IMG = "img/sample50x50.png";
    private final int CELL_WIDTH = 50;
    private final int CELL_HEIGHT = 50;

    private JLabel sampleLabel;
    private GUI gui;

    public SampleUI(Sample sample, GUI gui) {
        this.sample = sample;
        this.gui = gui;
        initUI();
    }

    private void initUI(){
        JPanel panel = gui.getBgPanel()[1];
        Point samplePos = gui.translateRoverPos(sample.getLocation());
        sampleLabel = gui.createObject(1, samplePos.x, samplePos.y, SAMPLE_IMG);
        panel.add(sampleLabel);
    }

    public void remove(){
        JPanel panel = gui.getBgPanel()[1];
        panel.remove(sampleLabel);
    }
}
