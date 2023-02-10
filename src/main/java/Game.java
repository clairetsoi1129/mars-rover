import java.util.ArrayList;
import java.util.List;

public class Game {
    private List<Rover> rovers;
    private Plateau plateau;

    public Game(AInput input) {
        List<Instruction> vehicleInstruction = input.getVehicleInstruction();
        plateau = new Plateau(input.getPlateauSize());

        rovers = new ArrayList<>();
        for (Instruction instruction: vehicleInstruction){
            rovers.add(new Rover(instruction));
        }
    }

    public void start(){
        for (Rover rover: rovers){
            rover.start();
        }
    }

    public List<Rover> getRovers() {
        return rovers;
    }

    public Plateau getPlateau() {
        return plateau;
    }
}
