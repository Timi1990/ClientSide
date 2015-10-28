package networking;

import notifications.ObservableNotification;

import java.io.Serializable;


/**
 * General interface for various types of connections
 * @author Artiom,Yoav
 */

public interface Connection extends Serializable {

    ObservableNotification getData();

    void sendData(Object object);

    void close();
}
