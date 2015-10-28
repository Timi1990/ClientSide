package notifications;

import model.IModel;
import org.eclipse.swt.SWT;
import org.eclipse.swt.widgets.MessageBox;
import org.eclipse.swt.widgets.Shell;

import java.io.Serializable;

/**
 * Notification for exception catching, when command's invalid
 * @author  Artiom,Yoav
 */

public class CommandNotFoundNotification extends ObservableNotification implements Serializable {


    @Override
    public void apply() {

    }

    @Override
    public void print() {
        System.out.println("Command not found!");
    }

    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public Object getData() {
        MessageBox messageBox = new MessageBox(new Shell(), SWT.ICON_WARNING|SWT.OK);
        messageBox.setText("Command invalid");
        messageBox.setText("The command entered is invalid");
        messageBox.open();
        return null;
    }

    @Override
    public void setData(Object data) {

    }
}
