import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Objects;
import java.util.Scanner;

public class GUI extends AInput {
    private GameManager gm;
    private JFrame window;
    private JTextArea messageText;
    private JFormattedTextField widthText;
    private JFormattedTextField heightText;

    private JFormattedTextField roverXText;

    private JFormattedTextField roverYText;

    private JFormattedTextField roverDirText;

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
    private final String ARROW_IMG = "img/arrow50x50.png";

    private final String ROVER_IMG = "img/rover50x50-%s.png";
    private final int CELL_WIDTH = 50;
    private final int CELL_HEIGHT = 50;

    private JLabel roverLabel;

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
        messageText.setFont(new Font("Book Antiqua", Font.PLAIN, 26));
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

    private JLabel createObject(int bgNum, int objX, int objY, String objImg,
                               String[] choiceNames, String[] choiceCommands) {
        JPopupMenu popMenu = new JPopupMenu();

        JMenuItem[] menuItem = new JMenuItem[3];
        for (int i = 0; i < 3; i++) {
            menuItem[i] = new JMenuItem(choiceNames[i]);
            int finalI = i;
            menuItem[i].addActionListener(ev -> {
                switch (choiceCommands[finalI]) {
                    case "L" -> {
                        gm.getEv1().turnLeft();
                        updateRover(choiceCommands[finalI].toCharArray());
                    }
                    case "R" -> {
                        gm.getEv1().turnRight();
                        updateRover(choiceCommands[finalI].toCharArray());
                    }
                    case "M" -> {
                        gm.getEv1().moveForward();
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

    private void initMainBoard() {
        createBackground(0, BG_IMG);
        createSceneSizeComponents(0);
        createRoverInitComponents(0);
        createArrowButton(0, 0, 150, 50, 50, ARROW_IMG, "goScene1");
        bgPanel[0].add(bgLabel[0]);
    }

    private void initGameBoardComponents() {
        createBackground(1, BG_IMG);
        createGrid(1);

        Instruction instruction = instructionList.get(0);
        int x = instruction.getInitialPosition().x;
        int y = instruction.getInitialPosition().y;
        Direction dir = instruction.getDirection();
        System.out.println("roverPos:[" + x + "," + y + "," + dir + "]");
        Point roverPos = translateRoverPos(x, y);
        System.out.println("roverPos:" + roverPos);

        String img = String.format(ROVER_IMG, dir);
        System.out.println("Rover :" + img);
        roverLabel = createObject(1, roverPos.x, roverPos.y, img,
                new String[]{"Turn Left", "Turn Right", "Move one step forward"},
                new String[]{"L", "R", "M"});
        bgPanel[1].add(bgLabel[1]);
        gm.initGame();
    }

    private void forbiddenMove(){
        getMessageText().setText("Oh! You cannot go further. You will hit the boundary.");
    }

    private void updateRover(char[] command) {
        AVehicle rover = gm.getGame().getRovers().get(0);
        System.out.println("roverOldPos:["+rover.getPosDir()+"]");

        gm.getGame().getRovers().get(0).setMovements(command);
        try {
            gm.getGame().start();

            Direction newDir = gm.getGame().getRovers().get(0).getDirection();
            Point newPos = gm.getGame().getRovers().get(0).getPosition();
            System.out.println("roverNewPos:[" + rover.getPosDir() + "]");
            String img = String.format(ROVER_IMG, newDir);
            ImageIcon icon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(img)));
            roverLabel.setIcon(icon);
            Point boardPoint = translateRoverPos(newPos.x, newPos.y);
            roverLabel.setBounds(boardPoint.x, boardPoint.y, CELL_WIDTH, CELL_HEIGHT);
        }catch (IllegalArgumentException e){
            if ("Forbidden move. You will hit the boundary.".equals(e.getMessage())){
                forbiddenMove();
            }
        }
    }

    public Point translateRoverPos(int x, int y) {
        Dimension dim = sceneDimension;
        Point pos = getGridPos(x, dim.height - y - 1);
        return new Point(pos.x-CELL_WIDTH/2, pos.y+CELL_HEIGHT/2);
    }

    public Point getGridPos(int x, int y) {
        Dimension dim = sceneDimension;
        return new Point((WIN_WIDTH / 2 - (dim.width) * CELL_WIDTH / 2 + x * CELL_WIDTH),
                (WIN_HEIGHT - TEXT_AREA_HEIGHT) / 2 + (dim.height) * CELL_HEIGHT / 2 - (dim.height - y) * CELL_HEIGHT);
    }

    public void createGrid(int bgNum) {
        Dimension dim = sceneDimension;
        for (int row = 0; row < dim.width; row++) {
            for (int col = 0; col < dim.height; col++) {
                JLabel gridLabel = new JLabel();
                Point pos = getGridPos(row, col);
                gridLabel.setBounds(pos.x, pos.y, 50, 50);
                gridLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY, 1));
                bgPanel[bgNum].add(gridLabel);
            }
        }
    }

    public void createSceneSizeComponents(int bgNum) {
        int labelWidth = 200;
        int labelHeight = 50;
        int labelX = 200;
        int labelY = 100;
        int textFieldWidth = 50;
        int textFieldX = labelX + labelWidth;
        int textFieldY = labelY;

        JLabel plateauSizeLabel = new JLabel();
        plateauSizeLabel.setText("Plateau size (width x height):");
        plateauSizeLabel.setBounds(labelX, labelY, labelWidth, labelHeight);

        widthText = new JFormattedTextField();
        heightText = new JFormattedTextField();
        widthText.setBounds(textFieldX, textFieldY, textFieldWidth, labelHeight);
        heightText.setBounds(textFieldX + 50, textFieldY, textFieldWidth, labelHeight);

        bgPanel[bgNum].add(plateauSizeLabel);
        bgPanel[bgNum].add(widthText);
        bgPanel[bgNum].add(heightText);
    }

    public void createRoverInitComponents(int bgNum) {
        int labelWidth = 200;
        int labelHeight = 50;
        int labelX = 200;
        int labelY = 200;
        int textFieldWidth = 50;
        int textFieldX = 400;

        JLabel roverPosLabel = new JLabel("Rover position (x, y):");
        roverPosLabel.setBounds(labelX, labelY, labelWidth, labelHeight);

        JLabel roverDirLabel = new JLabel("Rover direction (N, E, S, W):");
        roverDirLabel.setBounds(labelX, labelY + 50, labelWidth, labelHeight);

        roverXText = new JFormattedTextField();
        roverYText = new JFormattedTextField();
        roverDirText = new JFormattedTextField();
        roverXText.setBounds(textFieldX, labelY, textFieldWidth, labelHeight);
        roverYText.setBounds(textFieldX + 50, labelY, textFieldWidth, labelHeight);
        roverDirText.setBounds(textFieldX, labelY + 50, textFieldWidth, labelHeight);

        bgPanel[bgNum].add(roverPosLabel);
        bgPanel[bgNum].add(roverDirLabel);
        bgPanel[bgNum].add(roverXText);
        bgPanel[bgNum].add(roverYText);
        bgPanel[bgNum].add(roverDirText);
    }


    public void createArrowButton(int bgNum, int x, int y, int width, int height, String img, String cmd) {
        ImageIcon arrowIcon = new ImageIcon(Objects.requireNonNull(getClass().getClassLoader().getResource(img)));
        JButton arrowButton = new JButton();
        arrowButton.setBounds(x, y, width, height);
        arrowButton.setBackground(null);
        arrowButton.setContentAreaFilled(false);
        arrowButton.setFocusPainted(false);
        arrowButton.setIcon(arrowIcon);
        arrowButton.addActionListener(ev -> {
            switch (cmd) {
                case "goScene1" -> {
                    sceneDimension = new Dimension(Integer.parseInt(widthText.getText()),
                            Integer.parseInt(heightText.getText()));
                    instructionList.add(new Instruction(parseInitialPos(), parseInitialDirection()));
                    initGameBoardComponents();
                    gm.getScreenChanger().showScreen1();
                }
                case "goScene0" -> gm.getScreenChanger().showScreen0();
                default -> {
                }
            }
        });
        arrowButton.setActionCommand(cmd);
        arrowButton.setBorderPainted(false);
        bgPanel[bgNum].add(arrowButton);
    }

    private Point parseInitialPos() {
        return new Point(Integer.parseInt(roverXText.getText()), Integer.parseInt(roverYText.getText()));
    }

    private Direction parseInitialDirection() {
        return Direction.valueOf(roverDirText.getText().trim().toUpperCase());
    }

    public JTextArea getMessageText() {
        return messageText;
    }

    public JPanel[] getBgPanel() {
        return bgPanel;
    }

    public Instruction parseInitialPosDirection(Scanner scanner) {
        return null;
    }

    public char[] parseMovement(Scanner scanner) {
        return null;
    }
}
