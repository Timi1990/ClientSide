package view;

import algorithms.mazeGenerators.Position;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.graphics.Image;


/**
 * Holds the game character of the game, in charge of its position and setting
 * @author  Artiom,Yoav
 */

public class GameCharacter
{
    private Position currentPosition;
    private final Image image;

    public GameCharacter(Image image, Position startPosition)
    {
        this.image = image;
        this.currentPosition = new Position(startPosition);
    }

    public Position getCurrentPosition()
    {
        return currentPosition;
    }

    public void setPosition(Position position)
    {
        this.currentPosition = position;
    }

    public int getZ()
    {
        return currentPosition.getZ();
    }

    public void setZ(int z)
    {
        currentPosition.setZ(z);
    }

    public int getX()
    {
        return currentPosition.getX();
    }

    public void setX(int x)
    {
        currentPosition.setX(x);
    }

    public int getY()
    {
        return currentPosition.getY();
    }

    public void setY(int y)
    {
        currentPosition.setY(y);
    }

    public void paint(PaintEvent paintEvent, int w, int h)
    {
        paintEvent.gc.drawImage(image, 0, 0, image.getBounds().width, image.getBounds().height, getX() * w, getY() * h, w, h);
    }
}
