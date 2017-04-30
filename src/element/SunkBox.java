package element;

import java.awt.Point;

import game.GameIModel;


public class SunkBox extends Box{

    /**
     * 
     * Constructs a SunkBox with the given parameter(s)
     * @param p the Position
     * @param model the GameIModel (used to load the image)
     */
    public SunkBox(Point p, GameIModel model) {
        super(p, ElementType.SUNK);
        background = model.getGameSunkImage();
    }

    /**
     * Indicates if the this GameElement is useful
     * to the Strategy, like
     * a hitBox is useful (it let us know that there is a
     * Ship at the position)
     * @return true if the GameElement can be used to the IA
     */
    @Override
    public boolean isStrategicallyUseful() {
        return false;
    }
}
