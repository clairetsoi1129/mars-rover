package util;

public class Message {
    public static final String TITLE = "Welcome to Melody Mars Rover!!";
    public static final String WELCOME_MSG = "Welcome to Melody Mars Mission, you can have a wonderful experience " +
            "to move rovers around the surface of Mars!";

    public static final String MSG_GET_PLATEAU_SIZE = "To start, please enter plateau size you want to explore.";
    public static final String MSG_PLATEAU_WIDTH = "Plateau width (integer): ";
    public static final String MSG_PLATEAU_HEIGHT = "Plateau height (integer): ";

    public static final String MSG_GET_PLATEAU_SIZE_GUI = "Please enter plateau size (width x height).";


    public static final String MSG_GET_INIT_POS = "And please let me know where you want to place your rover at the beginning.";

    public static final String MSG_INIT_POS_X = "Rover initial position in integer, x:";
    public static final String MSG_INIT_POS_Y = "Rover initial position in integer, y:";

    public static final String MSG_INIT_POS_X_Y_GUI = "Rover initial position in integer, (x,y):";
    public static final String MSG_INIT_DIR = "Rover initial facing direction (N,E,S,W): ";

    public static final String MSG_SETUP_DONE = "Setup complete. You can control the rover now.";

    public static final String MSG_GET_INSTRUCTION = """
            How do you want to move? L - Turn Left 90 degree,\s
            R - Turn Right 90 degree,\s
            M - Move 1 step forward.\s
            Please put your instruction in one line. (eg. LMRMMM)""";
    public static final String[] MENU_NAMES = {"Turn Left", "Turn Right", "Move one step forward"};

    public static final String[] MENU_CMDS = {"L", "R", "M"};

//    public static final String FORBIDDEN_MOVE = "Oh! You cannot go further. You will hit the boundary.";

    public static final String ERR_MSG_FORBIDDEN_MOVE = "Oh! You cannot go further. You will hit the boundary.";
    public static final String ERR_MSG_HAS_OBSTACLE = "Oh! You cannot go further. You will hit the obstacle.";
    public static final String ERR_MSG_WRONG_INSTRUCTION = "Wrong Instruction. Please input L,R,M only.";

    public static final String ERR_MSG_INVALID_POS_DIR = "Invalid vehicle initial position or initial direction.";

    public static final String ERR_MSG_INVALID_SIZE = "Input is not a valid size. Please input integer which is greater than or equals to 1 only.";
    public static final String ERR_MSG_INVALID_SIZE_GUI = "Input is not a valid size. Please input integer which is greater than or equals to 1 only. Max allowable size is 15 x 9.";
    public static final String ERR_MSG_INVALID_DIR = "Input is not a valid direction. Please input N,E,S,W only.";
    public static final String ERR_MSG_INVALID_POS = "Input is not a valid position. Please input integer only.";
    public static final String ERR_MSG_INVALID_POS_OUTSIDE = "Input is out of plateau size.";


    public static final String MSG_TO_LEFT = "Turning left.";
    public static final String MSG_TO_RIGHT = "Turning right.";
    public static final String MSG_GO_FORWARD = "Move forward.";

}