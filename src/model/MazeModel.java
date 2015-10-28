package model;

import networking.Connection;
import networking.SocketConnection;
import notifications.ObservableNotification;
import notifications.PropertiesNotification;
import notifications.SaveSolutionsNotification;
import presenter.Properties;

import java.beans.XMLDecoder;
import java.io.*;
import java.util.List;
import java.util.Observable;
import java.util.Scanner;

/**
 * An implementation for IModel interface, uses Connection interface for various network connections
 * @author Artiom,Yoav
 */
public class MazeModel extends Observable implements IModel
{

    private Connection connection;

    /**
     * Sends instance of observable notification to server, then reads it, and finally sends back to presenter
     * @param observableNotification
     */
    @Override
    public void toServer(ObservableNotification observableNotification) {

            observableNotification.nullModel();
            connection.sendData(observableNotification);
            observableNotification = connection.getData();

            setChanged();
            notifyObservers(observableNotification);

    }


    /**
     * Sets the client properties when requested, such as: View type, port etc.
     * @param observableNotification
     * @throws IOException
     */
    @Override
    public void setProperties(ObservableNotification observableNotification) throws IOException {
        PropertiesNotification propertiesNotification = (PropertiesNotification)observableNotification;
        String filePath = (String)propertiesNotification.getData();

        XMLDecoder xmlDecoder = null;
        try
        {
            xmlDecoder = new XMLDecoder(new BufferedInputStream(new FileInputStream(filePath)));
        } catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }

        Properties properties = (Properties) xmlDecoder.readObject();

        xmlDecoder.close();

        List<String> propertiesList = properties.getPropertiesList();

        int port = Integer.parseInt(propertiesList.get(0));
        String host = propertiesList.get(1);
        String view = propertiesList.get(2);
        connection = new SocketConnection(port,host);
        System.out.println("Connected to port "+port);

        if(view.equals("CLI"))
        {
            propertiesNotification.setViewCLI(true);
            setChanged();
            notifyObservers(propertiesNotification);
        }
    }

    /**
     * Sends instance of string (when CLI view) to server, then reads it, and finally sends back to presenter
     * as observable notificaion
     * @param command
     */
    @Override
    public void cliCommandToServer(String command)
    {
        if(command.equals("Exit"))
        {
            System.out.println("Do you want to save solutions before exit?");
            Scanner scanner = new Scanner(System.in);
            String answer = scanner.nextLine();
            if(answer.toLowerCase().equals("yes"))
            {
                System.out.println("Enter gz. file path to save to...");
                answer = scanner.nextLine();
                connection.sendData(new SaveSolutionsNotification(answer));
            }
            connection.close();
        }

        else {
            connection.sendData(command);
            ObservableNotification observableNotification = connection.getData();
            setChanged();
            notifyObservers(observableNotification);
        }
    }


    /**
     * Closes connection when commanded to
     */
    @Override
    public void close() {
        connection.close();
    }


}
