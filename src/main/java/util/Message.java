package util;

public class Message {
    public static final String TITLE = "Welcome to Melody Mars model.Rover!!";
    public static final String WELCOME_MSG = "Welcome to Melody Mars Mission, you can have a wonderful experience " +
            "to move rovers around the surface of Mars!";

    public static final String[] MENU_NAMES = {"Turn Left", "Turn Right", "Move one step forward"};

    public static final String[] MENU_CMDS = {"L","R","M"};

    public static final String FORBIDDEN_MOVE = "Oh! You cannot go further. You will hit the boundary.";

    public static final String ERR_MSG_FORBIDDEN_MOVE = "Forbidden move. You will hit the boundary.";
    public static final String ERR_MSG_WRONG_INSTRUCTION = "Wrong Instruction. Please input L,R,M only.";

    public static final String MSG_ASK_PLATEAU_SIZE = "Please specify the size (width x height) of area you" +
            " want to explore.";

}
