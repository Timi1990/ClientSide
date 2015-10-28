package view.listeners;

import notifications.LoadSolutionsNotification;
import notifications.SaveSolutionsNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import view.MazeMenu;

/**
 * Listener for load solutions
 * @author  Artiom,Yoav
 */

public class LoadMenuListener extends SelectionAdapter {
    private final MazeMenu mazeMenu;
    private final Shell shell;

    public LoadMenuListener(MazeMenu mazeMenu, Shell shell) {
        this.mazeMenu = mazeMenu;
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent) {
        FileDialog fileDialog = new FileDialog(shell, SWT.OPEN);
        String[] filters = {"*.gz"};
        fileDialog.setFilterExtensions(filters);
        fileDialog.setText("Load");
        fileDialog.setFilterPath("C:/");
        String filePath = fileDialog.open();
        if (mazeMenu.isInitialized() && filePath != null) {
            mazeMenu.applaySetChanged();
            LoadSolutionsNotification loadSolutionsNotification = new LoadSolutionsNotification(filePath);
            mazeMenu.notifyObservers(loadSolutionsNotification);

            MessageBox messageBox = new MessageBox(shell, SWT.DEFAULT);
            messageBox.setText("Solutions loaded");
            messageBox.setMessage(mazeMenu.getData());
            messageBox.open();
        }

    }

}
