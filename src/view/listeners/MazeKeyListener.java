package view.listeners;

import algorithms.mazeGenerators.Maze3d;
import notifications.DisplayCrossSelectionNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.KeyAdapter;
import org.eclipse.swt.events.KeyEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import view.GameCharacter;
import view.GameHandler;
import view.MazeMenu;


/**
 * Defining game's key's actions
 * @author  Artiom,Yoav
 */

public class MazeKeyListener extends KeyAdapter
{
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Label label;
    private final Maze3d maze3d;
    private final MazeMenu mazeWindow;
    private final GameHandler gameHandler;

    public MazeKeyListener(MazeMenu mazeWindow, GameCharacter gameCharacter, Canvas canvas, Label label, Maze3d maze3d, GameHandler gameHandler)
    {
        this.mazeWindow = mazeWindow;
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
        this.label = label;
        this.maze3d = maze3d;
        this.gameHandler = gameHandler;
    }

    @Override
    public void keyPressed(KeyEvent keyEvent)
    {
        Integer x = gameCharacter.getX();
        Integer y = gameCharacter.getY();
        Integer z = gameCharacter.getZ();

        int[][] mazeData = getCrossSelectionBy(z);

        switch (keyEvent.keyCode)
        {
            case SWT.ARROW_RIGHT:
            {
                if (mazeData[y][x + 1] != 1)
                {
                    gameCharacter.setX(x + 1);

                    gameHandler.checkForWin(gameCharacter.getCurrentPosition());

                    canvas.redraw();
                }
                break;

            }
            case SWT.ARROW_LEFT:
            {
                if (x != 0 && mazeData[y][x - 1] != 1)
                {
                    gameCharacter.setX(x - 1);

                    gameHandler.checkForWin(gameCharacter.getCurrentPosition());
                    canvas.redraw();
                }
                break;
            }
            case SWT.ARROW_DOWN:
            {
                if (mazeData[y + 1][x] != 1)
                {
                    gameCharacter.setY(y + 1);

                    gameHandler.checkForWin(gameCharacter.getCurrentPosition());
                    canvas.redraw();
                }
                break;
            }
            case SWT.ARROW_UP:
            {
                if (mazeData[y - 1][x] != 1)
                {
                    gameCharacter.setY(y - 1);

                    gameHandler.checkForWin(gameCharacter.getCurrentPosition());
                    canvas.redraw();
                }
                break;
            }
            case SWT.PAGE_UP:
            {
                Integer updateZ = z + 1;

                if (updateZ < maze3d.getDimension())
                {
                    int[][] crossSelectionBy = getCrossSelectionBy(updateZ);

                    if (crossSelectionBy[y][x] == 0)
                    {
                        gameCharacter.setZ(updateZ);

                        gameHandler.checkForWin(gameCharacter.getCurrentPosition());
                        setTextIfEnable(updateZ.toString());

                        canvas.redraw();
                    }

                }
                break;
            }
            case SWT.PAGE_DOWN:
            {
                Integer updateZ = z - 1;

                if (updateZ >= 0)
                {
                    int[][] crossSelectionBy = getCrossSelectionBy(updateZ);

                    if (crossSelectionBy[y][x] == 0)
                    {
                        gameCharacter.setZ(updateZ);

                        gameHandler.checkForWin(gameCharacter.getCurrentPosition());

                        setTextIfEnable(updateZ.toString());

                        canvas.redraw();
                    }
                }
                break;
            }
        }
    }

    private int[][] getCrossSelectionBy(Integer z)
    {
        DisplayCrossSelectionNotification displayCrossSelectionNotification = new DisplayCrossSelectionNotification(maze3d, z, "Z");
        mazeWindow.applaySetChanged();
        mazeWindow.notifyObservers(displayCrossSelectionNotification);
        int[][] mazeData = mazeWindow.getData();

        return mazeData;
    }
    private void setTextIfEnable(String text)
    {
        if(!label.isDisposed())
        {
            label.setText(text);
        }
    }
}
