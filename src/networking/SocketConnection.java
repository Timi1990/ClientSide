package networking;

import notifications.ObservableNotification;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * An implementation of Connection, uses Sockets when initialized with port and host
 * @author Artiom,Yoav
 */
public class SocketConnection implements Connection {

    private final int port;
    private final String host;
    private Socket socket;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public SocketConnection(int port, String host) {
        this.port = port;
        this.host = host;
        try {
            this.socket = new Socket(host,port);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Retrieves data from server when recieved, casts to Observable Notification
     * @return
     */
    @Override
    public ObservableNotification getData() {
        try {
            return (ObservableNotification)in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Sends any object type data to server, then flushes
     * @param object
     */
    @Override
    public void sendData(Object object) {
        try {
            out.writeObject(object);
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * Closes the socket when commanded
     */
    @Override
    public void close() {
        try {
            socket.close();
            System.out.println("Socket closed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
