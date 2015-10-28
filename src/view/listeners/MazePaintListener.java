package view.listeners;

import algorithms.mazeGenerators.Maze3d;
import notifications.DisplayCrossSelectionNotification;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Color;
import org.eclipse.swt.graphics.RGB;
import org.eclipse.swt.widgets.Canvas;
import view.GameCharacter;
import view.MazeMenu;

import java.util.HashMap;
import java.util.Random;

/**
 * Defining game's paint events and creation
 * @author  Artiom,Yoav
 */

public class MazePaintListener implements PaintListener
{
    private final MazeMenu mazeMenu;
    private final Maze3d maze3d;
    private final Canvas canvas;
    private final GameCharacter gameCharacter;
    private final HashMap<Integer, Color> floorToColor = new HashMap<>();

    public MazePaintListener(MazeMenu mazeMenu, Canvas canvas, GameCharacter gameCharacter, Maze3d maze3d)
    {
        this.mazeMenu = mazeMenu;
        this.canvas = canvas;
        this.gameCharacter = gameCharacter;
        this.maze3d = maze3d;
        createFloorToColor(maze3d);

    }

    private void createFloorToColor(Maze3d maze3d)
    {
        for (int i = 0; i < maze3d.getDimension(); i++)
        {
            Random rand = new Random();

            float r = (float) (rand.nextFloat()/ 15f);
            float g = (float) (rand.nextFloat());
            float b = (float) (rand.nextFloat());

            floorToColor.put(i, new Color(null, new RGB(r, g, b)));
        }
    }

    @Override
    public void paintControl(PaintEvent e)
    {
        int z = gameCharacter.getZ();
        Color color = floorToColor.get(z);

        e.gc.setBackground(color);

        int width = canvas.getSize().x;
        int height = canvas.getSize().y;

        DisplayCrossSelectionNotification displayCrossSelectionNotification = new DisplayCrossSelectionNotification(maze3d, z, "Z");
        mazeMenu.applaySetChanged();
        mazeMenu.notifyObservers(displayCrossSelectionNotification);

        int[][] mazeData = mazeMenu.getData();
        int w = width / mazeData[0].length;
        int h = height / mazeData.length;

        for (int i = 0; i < mazeData.length; i++)
        {
            for (int j = 0; j < mazeData[0].length; j++)
            {
                int x = j * w;
                int y = i * h;

                if (mazeData[i][j] != 0)
                {
                    e.gc.fillRoundRectangle(x, y, w, h, 10, 10);
                }
            }
        }
        gameCharacter.paint(e, w, h);
    }
}
