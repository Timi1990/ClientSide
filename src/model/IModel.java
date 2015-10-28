package model;

import notifications.ObservableNotification;

import java.io.IOException;
import java.io.Serializable;

/**
 * Client's model interface, for communicating with server
 */

public interface IModel extends Serializable {

    void toServer(ObservableNotification observableNotification);

    void setProperties(ObservableNotification observableNotification) throws IOException;

    void cliCommandToServer(String command);

    void close();
}
