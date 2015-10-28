package view;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import org.eclipse.swt.graphics.Image;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.io.Serializable;

public class GameHandler implements Serializable
{
    private final Maze3d maze3d;
    private final MazeMenu mazeMenu;

    public GameHandler(Maze3d maze3d, MazeMenu mazeMenu)
    {
        this.maze3d = maze3d;
        this.mazeMenu = mazeMenu;
    }

    public void checkForWin(Position position)
    {
        Position exitPosition = maze3d.getExitPosition();

        if(exitPosition.equals(position))
        {
            for (Control control : mazeMenu.shell.getChildren()) {
                control.dispose();
            }
            Shell shell = new Shell();
            shell.setSize(300,300);
            shell.setText("You Won!!");

            shell.setBackgroundImage(new Image(mazeMenu.display,"resources/marko2.jpg"));
            shell.open();
        }
    }
}
