package notifications;

import model.IModel;

import java.io.Serializable;

/**
 *
 * Indicates when client wants to exit, invokes closing socket
 * @author  Artiom,Yoav
 */

public class ExitNotification extends ObservableNotification implements Serializable {



    @Override
    public void apply() {
        model.close();
        System.exit(1);
    }

    @Override
    public void print() {

    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }
}
