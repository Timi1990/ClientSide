package view;

import notifications.ObservableNotification;

/**
 * General interface for various types of view's
 * @author  Artiom,Yoav
 */

public interface IView
{
    void notifyFromReader(String notify);

    <T> void setData(ObservableNotification<T> observableNotification);

    <T> T getData();




}
