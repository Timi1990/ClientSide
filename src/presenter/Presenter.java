package presenter;

import model.IModel;
import notifications.ObservableNotification;
import view.IView;
import view.MazeCLIView;

import java.util.Observable;
import java.util.Observer;


/**
 * The presenter class (in MVP), handles various objects recieved from model/view
 * @author  Artiom,Yoav
 */

public class Presenter implements Observer
{
    private IModel model;
    private IView view;

    public Presenter(IModel model, IView view)
    {
        this.model = model;
        this.view = view;
    }

    /**
     * Overrides observer's update method, sends object to observables
     * @param o
     * @param arg
     */

    @Override
    public void update(Observable observable, Object obj)
    {
        if (observable == model)
        {
            view.setData((ObservableNotification) obj);


        } else if (observable == view) {
            if (obj instanceof ObservableNotification) {
                ObservableNotification observableNotification = (ObservableNotification)obj;
                observableNotification.init(model);
                observableNotification.apply();
            }
            else
            {
                String currentLine = (String) obj;
                model.cliCommandToServer(currentLine);
            }
        }

        }
    public void setView(MazeCLIView view)
    {
        this.view = view;
    }
    }




