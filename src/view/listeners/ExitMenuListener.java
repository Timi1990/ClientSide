package view.listeners;

import notifications.ExitNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import view.MazeMenu;

/**
 * Listener for organized exit action
 *
 * @author Artiom, Yoav
 */

public class ExitMenuListener extends SelectionAdapter {

    private final Display display;
    private final Shell shell;
    private final MazeMenu mazeMenu;

    public ExitMenuListener(MazeMenu mazeMenu, Display display, Shell shell) {
        this.display = display;
        this.shell = shell;
        this.mazeMenu = mazeMenu;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        if (mazeMenu.isInitialized()==true) {
            MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION | SWT.YES | SWT.NO);
            messageBox.setMessage("Are you sure you want to exit?");
            messageBox.setText("Exit");
            display.beep();
            int answer = messageBox.open();
            if (answer == SWT.YES) {
                mazeMenu.applaySetChanged();
                mazeMenu.notifyObservers(new ExitNotification());
                shell.dispose();
                display.dispose();

            }
        }
        else {
            shell.dispose();
            display.dispose();
            System.exit(1);
        }
    }

}
