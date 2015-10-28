package notifications;

import algorithms.mazeGenerators.Maze3d;
import algorithms.mazeGenerators.Position;
import algorithms.search.Solution;
import model.IModel;

import java.io.Serializable;


/**
 * Holds the maze which needs to be solved, also the solution thats being calculated
 * @author  Artiom,Yoav
 */

public class SolveMazeNotification extends ObservableNotification<Solution> implements Serializable
{
    private final Maze3d maze3d;
    private final Position startPosition;
    private Solution solution;

    public SolveMazeNotification(Maze3d maze3d, Position startPosition)
    {
        this.maze3d = maze3d;
        this.startPosition = startPosition;
    }

    @Override
    public void apply()
    {
        model.toServer(this);
    }

    @Override
    public void print()
    {
        System.out.println("Solution for maze "+maze3d.toString()+" is ready");
    }

    @Override
    public void init(IModel model)
    {
        this.model = model;
    }

    @Override
    public Solution getData()
    {
        return solution;
    }

    @Override
    public void setData(Solution solution)
    {
        this.solution = solution;
    }
}
