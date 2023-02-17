package view;

import controller.AInput;
import controller.GameManager;
import controller.GuiInput;
import util.Message;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;

public class GUI {
    private GameManager gm;
    private JFrame window;
    private JTextArea messageText;

    private JPanel[] bgPanel = new JPanel[10];
    private JLabel[] bgLabel = new JLabel[10];


    private final int WIN_WIDTH = 800;
    private final int WIN_HEIGHT = 600;

    private final int TEXT_AREA_WIDTH = WIN_WIDTH;
    private final int TEXT_AREA_HEIGHT = 100;
    private final int TEXT_AREA_X = 0;
    private final int TEXT_AREA_Y = WIN_HEIGHT - TEXT_AREA_HEIGHT;

    private final int BG_PANEL_WIDTH = WIN_WIDTH;
    private final int BG_PANEL_HEIGHT = WIN_HEIGHT - TEXT_AREA_HEIGHT;
    private final int BG_PANEL_X = 0;
    private final int BG_PANEL_Y = 0;

    private final String BG_IMG = "img/plateau800x600.png";

    private final int CELL_WIDTH = 50;
    private final int CELL_HEIGHT = 50;

    private RoverUI roverUI;
    private PlateauUI plateauUI;

    private AInput guiInput;

    public GUI(GameManager gm) {
        super();
        this.gm = gm;
        initMainWindow();
        initMainBoard();

        window.setVisible(true);

    }

    private void initMainWindow() {
        window = new JFrame(Message.TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setSize(WIN_WIDTH, WIN_HEIGHT);
        window.getContentPane().setBackground(Color.BLACK);
        window.setLayout(null);

        messageText = new JTextArea(Message.WELCOME_MSG);
        messageText.setBounds(TEXT_AREA_X, TEXT_AREA_Y, TEXT_AREA_WIDTH, TEXT_AREA_HEIGHT);
        messageText.setBackground(Color.BLACK);
        messageText.setForeground(Color.WHITE);
        messageText.setEditable(false);
        messageText.setLineWrap(true);
        messageText.setWrapStyleWord(true);
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 18));
        window.add(messageText);
    }

    private void createBackground(int bgNum, String bgImg) {
        bgPanel[bgNum] = new JPanel();
        bgPanel[bgNum].setBounds(BG_PANEL_X, BG_PANEL_Y, BG_PANEL_WIDTH, BG_PANEL_HEIGHT);
        bgPanel[bgNum].setBackground(Color.BLUE);
        bgPanel[bgNum].setLayout(null);
        window.add(bgPanel[bgNum]);

        bgLabel[bgNum] = new JLabel();
        bgLabel[bgNum].setBounds(BG_PANEL_X, BG_PANEL_Y, BG_PANEL_WIDTH, BG_PANEL_HEIGHT);

        ImageIcon bgIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(bgImg)));
        bgLabel[bgNum].setIcon(bgIcon);
    }

    public JLabel createObject(int bgNum, int objX, int objY, String objImg,
                               String[] choiceNames, String[] choiceCommands) {
        JPopupMenu popMenu = new JPopupMenu();

        JMenuItem[] menuItem = new JMenuItem[3];
        for (int i = 0; i < 3; i++) {
            menuItem[i] = new JMenuItem(choiceNames[i]);
            int finalI = i;
            menuItem[i].addActionListener(ev -> {
                switch (choiceCommands[finalI]) {
                    case "L" -> {
                        gm.getEvHandler().turnLeft();
                        updateRover(choiceCommands[finalI].toCharArray());
                    }
                    case "R" -> {
                        gm.getEvHandler().turnRight();
                        updateRover(choiceCommands[finalI].toCharArray());
                    }
                    case "M" -> {
                        gm.getEvHandler().moveForward();
                        updateRover(choiceCommands[finalI].toCharArray());
                    }
                }
            });
            menuItem[i].setActionCommand(choiceCommands[i]);
            popMenu.add(menuItem[i]);
        }

        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objX, objY, CELL_WIDTH, CELL_HEIGHT);
        ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(objImg)));
        objectLabel.setIcon(objectIcon);


        objectLabel.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
                if (SwingUtilities.isRightMouseButton(e)) {
                    popMenu.show(objectLabel, e.getX(), e.getY());
                }
            }

            @Override
            public void mouseReleased(MouseEvent e) {
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        bgPanel[bgNum].add(objectLabel);
        return objectLabel;

    }

    public JLabel createObject(int bgNum, int objX, int objY, String objImg) {
        JLabel objectLabel = new JLabel();
        objectLabel.setBounds(objX, objY, CELL_WIDTH, CELL_HEIGHT);
        ImageIcon objectIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(objImg)));
        objectLabel.setIcon(objectIcon);

        bgPanel[bgNum].add(objectLabel);
        return objectLabel;
    }

    private void initMainBoard() {
        createBackground(0, BG_IMG);
        guiInput = new GuiInput(this);
        bgPanel[0].add(bgLabel[0]);
    }

    public void initGameBoardComponents() {
        createBackground(1, BG_IMG);
        gm.initGame();
        plateauUI = new PlateauUI(gm.getGame().getPlateau(), this);
        roverUI = new RoverUI(gm.getGame().getRovers().get(0), this);
        bgPanel[1].add(bgLabel[1]);

        bgPanel[1].addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
            }

            @Override
            public void keyPressed(KeyEvent e) {
                switch (KeyEvent.getKeyText(e.getKeyCode())) {
                    case "L" -> {
                        gm.getEvHandler().turnLeft();
                        updateRover(KeyEvent.getKeyText(e.getKeyCode()).toCharArray());
                    }
                    case "R" -> {
                        gm.getEvHandler().turnRight();
                        updateRover(KeyEvent.getKeyText(e.getKeyCode()).toCharArray());
                    }
                    case "M" -> {
                        gm.getEvHandler().moveForward();
                        updateRover(KeyEvent.getKeyText(e.getKeyCode()).toCharArray());
                    }
                }
            }

            @Override
            public void keyReleased(KeyEvent e) {
            }
        });
        bgPanel[1].setFocusable(true);
    }

    private void updateRover(char[] command) {
        if (command.length == 0)
            return;

        gm.getGame().getRovers().get(0).setMovements(command);
        try {
            gm.getGame().start();
            roverUI.updateUI();
            plateauUI.updateSampleUI();

        } catch (IllegalArgumentException e) {
            getMessageText().setText(e.getMessage());
        }
    }

    public Point translateObjectPos(int x, int y) {
        Dimension dim = getGuiInput().getSceneSize();
        Point pos = getGridPos(x, dim.height - y - 1);
        return new Point(pos.x - CELL_WIDTH / 2, pos.y + CELL_HEIGHT / 2);
    }

    public Point translateObjectPos(Point point) {
        return translateObjectPos(point.x, point.y);
    }

    public Point getGridPos(int x, int y) {
        Dimension dim = getGuiInput().getSceneSize();
        return new Point((WIN_WIDTH / 2 - (dim.width) * CELL_WIDTH / 2 + x * CELL_WIDTH),
                (WIN_HEIGHT - TEXT_AREA_HEIGHT) / 2 + (dim.height) * CELL_HEIGHT / 2 - (dim.height - y) * CELL_HEIGHT);
    }

    public JTextArea getMessageText() {
        return messageText;
    }

    public JPanel[] getBgPanel() {
        return bgPanel;
    }

    public GameManager getGm() {
        return gm;
    }

    public AInput getGuiInput() {
        return guiInput;
    }
}
