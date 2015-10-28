package notifications;

import model.IModel;

import java.io.IOException;
import java.io.Serializable;


/**
 * Holds the file path which has the XML propeties file
 * @author  Artiom,Yoav
 */

public class PropertiesNotification extends ObservableNotification<String>
{
    private Boolean viewCLI = false;
    private final String filePath;

    public PropertiesNotification(String filePath)
    {
        this.filePath = filePath;
    }


    @Override
    public void apply(){
        try {
            model.setProperties(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void init(IModel model)
    {
        this.model = model;

    }

    public Boolean getView()
    {
        return viewCLI;
    }


    @Override
    public String getData() {
        return filePath;
    }

    public void setViewCLI(Boolean viewCLI) {
        this.viewCLI = viewCLI;
    }
}
