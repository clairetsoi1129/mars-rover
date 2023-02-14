package ui;

import input.GUI;
import model.Direction;
import model.Instruction;
import util.Message;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ParameterUI {
    private GUI gui;
    private JFormattedTextField widthText;
    private JFormattedTextField heightText;
    private JFormattedTextField roverXText;
    private JFormattedTextField roverYText;
    private JFormattedTextField roverDirText;

    private final int BTN_CONT_WIDTH = 50;
    private final int BTN_CONT_HEIGHT = 50;

    private final String ARROW_IMG = "img/arrow50x50.png";

    private final String CMD_GO_SCENES_1 = "goScene1";
    private final String CMD_GO_SCENES_0 = "goScene0";

    public ParameterUI(GUI gui) {
        this.gui = gui;
        initUI();
    }

    private void initUI(){
        initPlateauSizeUI();
        initRoverPosUI();
        initContinueButton();
    }

    private void initPlateauSizeUI(){
        int labelWidth = 300;
        int labelHeight = 50;
        int labelX = 100;
        int labelY = 100;
        int textFieldWidth = 50;
        int textFieldX = labelX + labelWidth;
        int textFieldY = labelY;
        JPanel panel = gui.getBgPanel()[0];

        JLabel plateauSizeLabel = new JLabel();
        plateauSizeLabel.setText(Message.MSG_GET_PLATEAU_SIZE_GUI);
        plateauSizeLabel.setForeground(Color.WHITE);
        plateauSizeLabel.setBounds(labelX, labelY, labelWidth, labelHeight);

        widthText = new JFormattedTextField();
        heightText = new JFormattedTextField();
        widthText.setBounds(textFieldX, textFieldY, textFieldWidth, labelHeight);
        heightText.setBounds(textFieldX + 50, textFieldY, textFieldWidth, labelHeight);

        panel.add(plateauSizeLabel);
        panel.add(widthText);
        panel.add(heightText);
    }

    private void initRoverPosUI(){
        int labelWidth = 300;
        int labelHeight = 50;
        int labelX = 100;
        int labelY = 200;
        int textFieldWidth = 50;
        int textFieldX = 400;
        int textFieldY = labelY;
        JPanel panel = gui.getBgPanel()[0];

        JLabel roverPosLabel = new JLabel(Message.MSG_INIT_POS_X_Y_GUI);
        roverPosLabel.setBounds(labelX, labelY, labelWidth, labelHeight);
        roverPosLabel.setForeground(Color.WHITE);

        JLabel roverDirLabel = new JLabel(Message.MSG_INIT_DIR);
        roverDirLabel.setBounds(labelX, labelY + 50, labelWidth, labelHeight);
        roverDirLabel.setForeground(Color.WHITE);

        roverXText = new JFormattedTextField();
        roverYText = new JFormattedTextField();
        roverDirText = new JFormattedTextField();
        roverXText.setBounds(textFieldX, textFieldY, textFieldWidth, labelHeight);
        roverYText.setBounds(textFieldX + 50, textFieldY, textFieldWidth, labelHeight);
        roverDirText.setBounds(textFieldX, textFieldY+50, textFieldWidth, labelHeight);

        panel.add(roverPosLabel);
        panel.add(roverDirLabel);
        panel.add(roverXText);
        panel.add(roverYText);
        panel.add(roverDirText);
    }

    private void initContinueButton(){
        initButton(CMD_GO_SCENES_1);
    }

    private void initButton(String cmd){
        JPanel panel = gui.getBgPanel()[0];

        ImageIcon arrowIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(ARROW_IMG)));
        JButton arrowButton = new JButton();
        arrowButton.setBounds(400, 400, BTN_CONT_WIDTH, BTN_CONT_HEIGHT);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(ev -> {
            switch (cmd) {
                case CMD_GO_SCENES_1 -> {
                    Dimension dim = getPlateauSize();
                    Point pos = getRoverInitialPos();
                    Direction dir = getRoverInitialDirection();

                    if (dim != null && pos != null && dir != null) {
                        gui.setSceneDimension(dim);
                        List<Instruction> instructionList = new ArrayList<>();
                        instructionList.add(new Instruction(pos, dir));
                        gui.setInstructionList(instructionList);
                        gui.initGameBoardComponents();
                        gui.getGm().getScreenChanger().showScreen1();
                    }
                }
                case CMD_GO_SCENES_0 -> gui.getGm().getScreenChanger().showScreen0();
                default -> {
                }
            }
        });
        arrowButton.setActionCommand(CMD_GO_SCENES_1);
        arrowButton.setBorderPainted(false);
        panel.add(arrowButton);
    }

    public Dimension getPlateauSize(){
        try {
            int width = Integer.parseInt(widthText.getText());
            int height = Integer.parseInt(heightText.getText());
            if (width > 15 || height > 9)
                throw new IllegalArgumentException();
            return new Dimension(Integer.parseInt(widthText.getText()),
                    Integer.parseInt(heightText.getText()));
        }catch (NumberFormatException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_SIZE_GUI);
        }catch (IllegalArgumentException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_SIZE_GUI);
        }
        return null;
    }

    public Point getRoverInitialPos(){
        try {
            int x = Integer.parseInt(roverXText.getText());
            int y = Integer.parseInt(roverYText.getText());
            if (x > getPlateauSize().getWidth() || y > getPlateauSize().getHeight())
                throw new IllegalArgumentException();

            return new Point(x,y);
        }catch (NumberFormatException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_POS);
        }catch (IllegalArgumentException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_POS_OUTSIDE);
        }
        return null;
    }

    public Direction getRoverInitialDirection(){
        try {
            return Direction.valueOf(roverDirText.getText().trim().toUpperCase());
        }catch (IllegalArgumentException e){
            gui.getMessageText().setText(Message.ERR_MSG_INVALID_DIR);
        }
        return null;
    }
}
