package view.listeners;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

/**
 * Listener for Help button (in order so solve the game)
 * @author  Artiom,Yoav
 */

public class HelpMenuListener extends SelectionAdapter {

    private final Shell shell;

    public HelpMenuListener(Shell shell) {
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        MessageBox messageBox = new MessageBox(shell, SWT.ICON_QUESTION);
        messageBox.setText("Information");
        messageBox.setMessage("This menu is a general menu for activating different games" + ", please open your xml properties file to start.");
        messageBox.open();
    }
}
