package notifications;

import algorithms.mazeGenerators.Maze3d;
import model.IModel;

import java.io.Serializable;

/**
 * Holds the data of cross selection, sent to server to initialize
 * @author  Artiom,Yoav
 */

public class DisplayCrossSelectionNotification extends ObservableNotification<int[][]> implements Serializable
{
    public int[][] crossSelection;
    public final String axis;
    public final Integer index;
    public final Maze3d maze;

    public DisplayCrossSelectionNotification(Maze3d maze, Integer index, String axis)
    {
        this.axis = axis;
        this.index = index;
        this.maze = maze;
    }

    @Override
    public void apply()
    {
        model.toServer(this);
    }

    @Override
    public void print()
    {
        for (int[] aCrossSelection : crossSelection)
        {
            System.out.printf("{");
            for (int anACrossSelection : aCrossSelection)
            {
                System.out.printf(anACrossSelection + ",");
            }
            System.out.printf("}");
            System.out.printf("\n");
        }
    }

    @Override
    public void init(IModel model)
    {
        this.model = model;
    }

    @Override
    public int[][] getData()
    {
        return crossSelection;
    }

    @Override
    public void setData(int[][] data)
    {
        this.crossSelection = data;
    }

}
