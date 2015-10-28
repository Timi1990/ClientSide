package view.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import view.MazeMenu;
import view.MazePropertiesWindow;

/**
 * Opens the properties window, obly if initialized with properties first
 * @author  Artiom,Yoav
 */

public class RunMazeListener extends SelectionAdapter {

    private final MazeMenu mazeMenu;
    private final Display display;
    private final Shell shell;
    private final MazePropertiesWindow mazePropertiesWindow;

    public RunMazeListener(MazeMenu mazeMenu, Display display, Shell shell, MazePropertiesWindow mazePropertiesWindow) {
        this.mazeMenu = mazeMenu;
        this.display = display;
        this.shell = shell;
        this.mazePropertiesWindow = mazePropertiesWindow;
    }

    @Override
    public void widgetSelected(SelectionEvent e) {
        if(!mazeMenu.isInitialized())
        {
            MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING);
            messageBox.setText("Error");
            messageBox.setMessage("You must load game properties first..");
            display.beep();
            messageBox.open();
        } else
        {
            mazePropertiesWindow.createWindow().open();
        }

    }
}
