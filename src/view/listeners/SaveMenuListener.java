package view.listeners;

import notifications.SaveSolutionsNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;
import view.MazeMenu;

/**
 * Listener for save solutions crated so far
 * @author  Artiom,Yoav
 */

public class SaveMenuListener extends SelectionAdapter {

    private final MazeMenu mazeMenu;
    private final Shell shell;

    public SaveMenuListener(MazeMenu mazeMenu,Shell shell) {
        this.mazeMenu = mazeMenu;
        this.shell = shell;
    }

    @Override
    public void widgetSelected(SelectionEvent selectionEvent)
    {
        FileDialog fileDialog = new FileDialog(shell, SWT.SAVE);
        String[] filters = {"*.gz"};
        fileDialog.setFilterExtensions(filters);
        fileDialog.setText("Save");
        fileDialog.setFilterPath("C:/");
        String filePath = fileDialog.open();
        if (mazeMenu.isInitialized() && filePath != null){
        mazeMenu.applaySetChanged();
        SaveSolutionsNotification saveSolutionsNotification = new SaveSolutionsNotification(filePath);
        mazeMenu.notifyObservers(saveSolutionsNotification);

        MessageBox messageBox = new MessageBox(shell,SWT.DEFAULT);
        messageBox.setText("Solution saved");
        messageBox.setMessage("Solutions saved at "+mazeMenu.getData());
        messageBox.open();}

    }
}
