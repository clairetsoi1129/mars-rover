import java.awt.*;

public class AScene {
    protected Dimension dimension;

    public AScene(Dimension dimension){
        this.dimension = dimension;
    }

    public boolean reachBoundary(Point position){
        return position.getX() > dimension.getWidth() || position.getY() > dimension.getHeight()
                || position.getX() < 0 || position.getY() < 0;
    }
}
