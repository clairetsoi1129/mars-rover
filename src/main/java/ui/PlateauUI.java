package ui;

import input.GUI;
import model.AScene;
import javax.swing.*;
import java.awt.*;

public class PlateauUI {
    private AScene plateau;
    private GUI gui;

    public PlateauUI(AScene plateau, GUI gui) {
        this.plateau = plateau;
        this.gui = gui;
        initUI();
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
}
