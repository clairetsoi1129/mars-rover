package view;

import model.Sample;

import javax.swing.*;
import java.awt.*;

public class SampleUI {
    private Sample sample;
    private final String SAMPLE_IMG = "img/sample50x50.png";

    private JLabel sampleLabel;
    private GUI gui;

    public SampleUI(Sample sample, GUI gui) {
        this.sample = sample;
        this.gui = gui;
        initUI();
    }

    private void initUI(){
        JPanel panel = gui.getBgPanel()[1];
        Point samplePos = gui.translateObjectPos(sample.getLocation());
        sampleLabel = gui.createObject(1, samplePos.x, samplePos.y, SAMPLE_IMG);
        panel.add(sampleLabel);
    }

    public void remove(){
        JPanel panel = gui.getBgPanel()[1];
        panel.remove(sampleLabel);
    }
}
