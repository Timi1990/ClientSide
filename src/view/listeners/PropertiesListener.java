package view.listeners;

import notifications.LoadSolutionsNotification;
import notifications.PropertiesNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import view.MazeMenu;

public class PropertiesListener extends SelectionAdapter {

    private final Shell shell;
    private final MazeMenu mazeMenu;

    public PropertiesListener(Shell shell, MazeMenu mazeMenu) {
        this.shell = shell;
        this.mazeMenu = mazeMenu;
    }


    @Override
    public void widgetSelected(SelectionEvent e) {
        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
        fileDialog.setText("Open");
        fileDialog.setFilterPath("C:/");
        String[] filters = {"*.xml"};
        fileDialog.setFilterExtensions(filters);
        String properties = fileDialog.open();


        if (properties != null) {
            mazeMenu.applaySetChanged();
            PropertiesNotification propertiesNotification = new PropertiesNotification(properties);
            mazeMenu.notifyObservers(propertiesNotification);
            mazeMenu.setInitialized(true);
            Boolean isCLI = propertiesNotification.getView();

            if (isCLI)
            {
                MessageBox messageBox = new MessageBox(shell, SWT.ICON_WARNING | SWT.OK);
                messageBox.setText("Switch to CLI");
                messageBox.setMessage("You have to restart the program");
                Display.getDefault().beep();
                messageBox.open();
                mazeMenu.setCommand(false);
                shell.dispose();
                Display.getDefault().dispose();
            }
        }


    }
}
