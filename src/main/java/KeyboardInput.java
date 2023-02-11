import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class KeyboardInput extends AInput{
    private List<Instruction> instructionList = new ArrayList<>();

    public KeyboardInput() {
        super();
        Scanner scanner=new Scanner(System.in);
        printWelcomeMessage();
        parseSceneSize(scanner);
        Instruction instruction = parseInitialPosDirection(scanner);
        instruction.setMovements(parseMovement(scanner));
        instructionList.add(instruction);
        scanner.close();
    }

    private void printWelcomeMessage(){
        System.out.println("Welcome to Melody Mars Mission!!");
        System.out.println("You will move rovers around the surface of Mars!");
    }

    public void parseSceneSize(Scanner in){
        System.out.println("To start, please enter plateau size you want to explore.");
        System.out.println("Plateau width (integer): ");
        int x=in.nextInt();
        System.out.println("Plateau height (integer): ");
        int y=in.nextInt();
        sceneDimension = new Dimension(x,y);
    }

    public Instruction parseInitialPosDirection(Scanner scanner) {
        return new Instruction(parseInitialPos(scanner), parseInitialDirection(scanner));
    }

    private Point parseInitialPos(Scanner scanner){
        System.out.println("Please let me know where you want your rover to place at the start of the game.");
        System.out.println("Rover initial position in integer, x: ");
        int x=scanner.nextInt();
        System.out.println("Rover initial position in integer, y: ");
        int y=scanner.nextInt();
//        scanner.nextLine(); //to consume the newline in previous in.nextInt() input
        return new Point(x,y);
    }

    private Direction parseInitialDirection(Scanner scanner){
        Direction direction = null;
        while (direction == null) {
            System.out.println("Rover initial facing direction (N,E,S,W): ");
            System.out.println("N - North");
            System.out.println("E - East");
            System.out.println("S - South");
            System.out.println("W - West");
            direction = Direction.valueOf(scanner.nextLine().trim().toUpperCase());
        }
        return direction;
    }

    public char[] parseMovement(Scanner scanner){
        System.out.println("Setup complete. You can control the rover now.");
        System.out.println("How do you want to move. Put your instruction in one line. (eg. LMRMMM): ");
        System.out.println("L - Turn Left 90 degree");
        System.out.println("R - Turn Right 90 degree");
        System.out.println("M - Move 1 step forward");
        String movement = scanner.nextLine();
        return movement.toCharArray();
    }

    public List<Instruction> getVehicleInstruction(){
        return instructionList;
    }
}
