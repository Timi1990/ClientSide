package view;

import java.io.BufferedReader;
import java.io.PrintWriter;

/**
 * Creates a CLI from Reader file runnable
 * @author  Artiom,Yoav
 */
public class CLIFactory {

    public CLI createFrom(BufferedReader in, PrintWriter out,MazeCLIView view)
    {
        ReaderFileRunnable readerFileRunnable = new ReaderFileRunnable(in, out,view);

        return new CLI(readerFileRunnable);
    }
}
