package view;

import notifications.ExitNotification;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

import java.util.Observable;

/**
 * GUI basic window, abstract class, can be used by all types of future GUI's
 * @author  Artiom,Yoav
 */

public abstract class BasicWindow extends Observable implements Runnable
{
    protected Display display = Display.getDefault();
    protected Shell shell = new Shell(display);
    protected Boolean command = true;

    BasicWindow(int width, int height)
    {
        shell.setSize(width, height);
        shell.setText("Maze menu");
    }

    public abstract void init();

    @Override
    public void run()
    {
        display.syncExec(new Runnable()
        {
            @Override
            public void run()
            {
                init();
                shell.open();
                while (!shell.isDisposed())
                {
                    if (!display.readAndDispatch())
                        display.sleep();
                }
                if(!command)
                {
                display.dispose();
                }
                else
                    System.exit(1);
            }
        });
    }

    public Shell getShell()
    {
        return shell;
    }

    public Boolean getBoolean()
    {
        return command;
    }

    public void setCommand(Boolean command) {
        this.command = command;
    }
}
