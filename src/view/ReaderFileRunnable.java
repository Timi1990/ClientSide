package view;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * Reads from some input from client, transfers to presenter
 * @author  Artiom,Yoav
 */

public class ReaderFileRunnable implements Runnable
{
    private final BufferedReader in;
    private final PrintWriter out;
    private final MazeCLIView view;

    public ReaderFileRunnable(BufferedReader in, PrintWriter out, MazeCLIView view)
    {
        this.in = in;
        this.out = out;
        this.view = view;
    }

    /**
     * Reads from input stream, and matches requests to various commands, according
     * to given command protocol.
     * Also in charge of closing the streams and runs as thread.
     */

    @Override
    public void run()
    {
        String currentLine;
        try
        {
            while ((currentLine = in.readLine()) != null)
            {
                view.notifyFromReader(currentLine);
            }
        } catch (Exception e)
        {
            e.printStackTrace();
        } finally
        {
            try
            {
                in.close();
            } catch (IOException e)
            {
                e.printStackTrace();
            }
            out.close();
        }
    }


}
