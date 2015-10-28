package view;

import org.eclipse.swt.SWT;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.*;
import view.listeners.GeneratorMazeWindowListener;

/**
 * Maze's properties window, opens only when initialized with XML properties file, in charge
 * of maze's arguments retrieved from user
 * @author  Artiom,Yoav
 */

public class MazePropertiesWindow
{
    private final MazeMenu mazeMenu;

    public MazePropertiesWindow(MazeMenu mazeMenu)
    {
        this.mazeMenu = mazeMenu;
    }

    public Shell createWindow()
    {
        Shell shell1 = new Shell(Display.getDefault());
        shell1.setSize(400, 150);
        shell1.setText("Maze properties");
        GridLayout gridLayout = new GridLayout();
        gridLayout.numColumns = 4;
        shell1.setLayout(gridLayout);

        Label nameLabel = new Label(shell1, SWT.BORDER);
        nameLabel.setText("Maze name");

        Label dimensionLabel = new Label(shell1, SWT.BORDER);
        dimensionLabel.setText("Dimension");
        Label rowLabel = new Label(shell1, SWT.BORDER);
        rowLabel.setText("Rows");
        Label columnsLabel = new Label(shell1, SWT.BORDER);
        columnsLabel.setText("Columns");
        Text t1 = new Text(shell1, SWT.BORDER);
        Text t2 = new Text(shell1, SWT.SINGLE | SWT.BORDER);
        Text t3 = new Text(shell1, SWT.SINGLE | SWT.BORDER);
        Text t4 = new Text(shell1, SWT.SINGLE | SWT.BORDER);

        Button button = new Button(shell1, SWT.PUSH);
        button.setText("Generate");
        button.pack();
        t1.pack();
        t2.pack();
        t3.pack();
        t4.pack();

        GeneratorMazeWindowListener generatorMazeWindowListener = new GeneratorMazeWindowListener(mazeMenu, shell1, t1, t2, t3, t4);

        button.addSelectionListener(generatorMazeWindowListener);

        return shell1;
    }
}
