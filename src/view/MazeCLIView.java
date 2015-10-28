package view;

import notifications.ObservableNotification;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.PrintWriter;
import java.util.Observable;

public class MazeCLIView extends Observable implements IView
{

    private final CLIFactory cliFactory;

    public MazeCLIView()
    {
        cliFactory = new CLIFactory();
    }


    @Override
    public void notifyFromReader(String notify)
    {
        setChanged();

        notifyObservers(notify);
    }

    @Override
    public <T> T getData() {
        return null;
    }


    @Override
    public void setData(ObservableNotification observableNotification)
    {
        observableNotification.print();
    }

    public void start(String fileInput, String fileOutput)
    {
        try
        {
            BufferedReader in = new BufferedReader(new FileReader(fileInput));
            PrintWriter out = new PrintWriter(fileOutput);

            CLI cli = cliFactory.createFrom(in, out,this);
            cli.start();

        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
    }


}
