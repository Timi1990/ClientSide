package view.listeners;

import algorithms.demo.Maze3dDomain;
import algorithms.demo.Maze3dState;
import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Searcher;
import algorithms.search.Solution;
import algorithms.search.State;
import notifications.SolveMazeNotification;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Canvas;
import org.eclipse.swt.widgets.Label;
import view.GameCharacter;
import view.GameHandler;
import view.MazeMenu;

import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;

public class HelpSelectionListener extends SelectionAdapter
{
    private final GameCharacter gameCharacter;
    private final Canvas canvas;
    private final Label floorNum;
    private final MazeMenu mazeWindow;
    private final Maze3d maze3d;
    private final GameHandler gameHandler;

    public HelpSelectionListener(MazeMenu mazeWindow, GameCharacter gameCharacter, Canvas canvas, Label floorNum, Maze3d maze3d, GameHandler gameHandler)
    {
        this.mazeWindow = mazeWindow;
        this.gameCharacter = gameCharacter;
        this.canvas = canvas;
        this.floorNum = floorNum;
        this.maze3d = maze3d;
        this.gameHandler = gameHandler;

    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        mazeWindow.applaySetChanged();
        SolveMazeNotification solveMazeNotification = new SolveMazeNotification(maze3d,gameCharacter.getCurrentPosition());
        mazeWindow.notifyObservers(solveMazeNotification);
        Solution solution = mazeWindow.getData();

        final ArrayList<State> solutionList = solution.getSolutionList();
        Timer timer = new Timer();
        final TimerTask task = new TimerTask()
        {
            @Override
            public void run()
            {
                if(!canvas.isDisposed()) {
                    canvas.getDisplay().syncExec(() -> {
                        int i = solutionList.size() - 1;

                        if (i >= 0) {
                            Maze3dState maze3dState = (Maze3dState) solutionList.get(i);

                            Position position = maze3dState.getPosition();
                            gameCharacter.setPosition(position);

                            Integer z = position.getZ();
                            floorNum.setText(z.toString());

                            canvas.redraw();

                            solutionList.remove(i);
                            gameHandler.checkForWin(position);
                        } else {
                            timer.cancel();
                        }
                    });
                }
            }
        };

        timer.scheduleAtFixedRate(task, 0, 500);
    }
}
