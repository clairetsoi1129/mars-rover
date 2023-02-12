public class GameManager {
    private GUI gui;
    private Game game;

    private ScreenChanger screenChanger;
    private Event01 ev1;

    public GameManager() {
        gui = new GUI(this);
        ev1 = new Event01(this);
        screenChanger = new ScreenChanger(this);
        screenChanger.showScreen0();
    }

    public void initGame(){
        game = new Game(gui);
    }

    public static void main(String[] args){
        new GameManager();
    }

    public GUI getGui() {
        return gui;
    }

    public Game getGame() {
        return game;
    }

    public ScreenChanger getScreenChanger() {
        return screenChanger;
    }

    public Event01 getEv1() {
        return ev1;
    }
}
