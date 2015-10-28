package boot;

import model.MazeModel;
import presenter.Presenter;
import view.MazeCLIView;
import view.MazeMenu;

import java.util.Scanner;

/**
 * Clients main class, runs the GUI (by default), unless the client want CLI- then being switched
 * @author Artiom,Yoav
 */

public class Run {
    public static void main(String[] args) throws Exception
    {

        MazeModel mazeModel = new MazeModel();
        MazeMenu mazeMenu = new MazeMenu(500, 500);

        Presenter presenter = new Presenter(mazeModel,mazeMenu);
        mazeModel.addObserver(presenter);
        mazeMenu.addObserver(presenter);

        mazeMenu.run();

        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter input file path...");
        String fileInput = scanner.next();

        System.out.println("Enter output file path...");
        String fileOutput = scanner.next();

        MazeCLIView mazeCLIView = new MazeCLIView();
        presenter.setView(mazeCLIView);
        mazeCLIView.addObserver(presenter);

        mazeCLIView.start(fileInput,fileOutput);

        System.exit(1);

//C:\Users\Timi\Desktop\input.txt

    }
}
