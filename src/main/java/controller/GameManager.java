package controller;

import view.GUI;
import view.ScreenChanger;

public class GameManager {
    private GUI gui;
    private Game game;

    private ScreenChanger screenChanger;
    private EventHandler evHandler;

    public GameManager() {
        gui = new GUI(this);
        evHandler = new EventHandler(this);
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

    public EventHandler getEvHandler() {
        return evHandler;
    }
}
