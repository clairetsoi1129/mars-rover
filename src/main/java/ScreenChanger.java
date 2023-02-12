public class ScreenChanger {
    GameManager gm;

    public ScreenChanger(GameManager gm){
        this.gm = gm;
    }

    public void showScreen1(){
        if (gm.getGui().getBgPanel()[1] != null) {
            gm.getGui().getBgPanel()[1].setVisible(true);
        }
        if (gm.getGui().getBgPanel()[0] != null) {
            gm.getGui().getBgPanel()[0].setVisible(false);
        }
        gm.getGui().getMessageText().setText("Screen 1");
    }

    public void showScreen0(){
        if (gm.getGui().getBgPanel()[1] != null) {
            gm.getGui().getBgPanel()[1].setVisible(false);
        }
        if (gm.getGui().getBgPanel()[0] != null) {
            gm.getGui().getBgPanel()[0].setVisible(true);
        }
        gm.getGui().getMessageText().setText("Screen 0");
    }
}
