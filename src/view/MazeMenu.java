package view;

import notifications.ObservableNotification;
import notifications.PropertiesNotification;
import org.eclipse.swt.SWT;
import org.eclipse.swt.events.SelectionAdapter;
import org.eclipse.swt.events.SelectionEvent;
import org.eclipse.swt.events.SelectionListener;
import org.eclipse.swt.widgets.FileDialog;
import org.eclipse.swt.widgets.Menu;
import org.eclipse.swt.widgets.MenuItem;
import org.eclipse.swt.widgets.MessageBox;
import view.listeners.*;

/**
 * Initializes maze's game menu, extends Basic Window
 * @author  Artiom,Yoav
 */

public class MazeMenu extends BasicWindow implements IView
{
    private Menu menuBar;
    private boolean initialized = false;
    public ObservableNotification observableNotification;

    public MazeMenu(int width, int height)
    {
        super(width, height);
        menuBar = new Menu(shell, SWT.BAR);
    }

    @Override
    public void init()
    {
        MenuItem fileMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        fileMenuHeader.setText("File");

        Menu fileMenu = new Menu(shell, SWT.DROP_DOWN);
        fileMenuHeader.setMenu(fileMenu);

        MenuItem openItem = new MenuItem(fileMenu, SWT.CASCADE);
        openItem.setText("Open");

        Menu openSubMenu = new Menu(shell, SWT.DROP_DOWN);
        openItem.setMenu(openSubMenu);

        MenuItem openProperties = new MenuItem(openSubMenu, SWT.PUSH);
        openProperties.setText("Load Properties\t(CTRL+O)");
        openProperties.setAccelerator(SWT.CTRL + 'O');

        MenuItem runMazeItem = new MenuItem(fileMenu, SWT.PUSH);
        runMazeItem.setText("Start game\t(CTRL+P)");
        runMazeItem.setAccelerator(SWT.CTRL + 'P');

        MenuItem fileSaveItem = new MenuItem(fileMenu, SWT.PUSH);
        fileSaveItem.setText("Save\t(CTRL+S)");
        fileSaveItem.setAccelerator(SWT.CTRL + 'S');

        MenuItem fileLoadItem = new MenuItem(fileMenu, SWT.PUSH);
        fileLoadItem.setText("Load\t(CTRL+A)");
        fileLoadItem.setAccelerator(SWT.CTRL + 'A');

        MenuItem fileExitItem = new MenuItem(fileMenu, SWT.PUSH);
        fileExitItem.setText("Exit");

        MenuItem helpMenuHeader = new MenuItem(menuBar, SWT.CASCADE);
        helpMenuHeader.setText("Help");

        Menu helpMenu = new Menu(shell, SWT.DROP_DOWN);
        helpMenuHeader.setMenu(helpMenu);

        MenuItem helpGetHelpItem = new MenuItem(helpMenu, SWT.PUSH);
        helpGetHelpItem.setText("Get help");

        shell.setMenuBar(menuBar);
        runMazeItem.addSelectionListener(new SelectionAdapter() {
        });

        MazePropertiesWindow mazePropertiesWindow = new MazePropertiesWindow(this);


        runMazeItem.addSelectionListener(new RunMazeListener(this,display,shell,mazePropertiesWindow));
        fileExitItem.addSelectionListener(new ExitMenuListener(this,display,shell));
        fileSaveItem.addSelectionListener(new SaveMenuListener(this,shell));
        fileLoadItem.addSelectionListener(new LoadMenuListener(this,shell));
        helpGetHelpItem.addSelectionListener(new HelpMenuListener(shell));
        openProperties.addSelectionListener(new PropertiesListener(shell,this));


    }

    @Override
    public void notifyFromReader(String notify)
    {

    }

    public void applaySetChanged()
    {
        setChanged();
    }

    @Override
    public <T> void setData(ObservableNotification<T> observableNotification)
    {
        this.observableNotification = observableNotification;
    }

    @Override
    public <T> T getData()
    {
        return (T)observableNotification.getData();
    }


    public void setInitialized(boolean initialized) {
        this.initialized = initialized;
    }

    public boolean isInitialized() {
        return initialized;
    }
}
