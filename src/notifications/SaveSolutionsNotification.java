package notifications;

import model.IModel;

import java.io.Serializable;


/**
 * Holds the file path which solutions will be saved to
 * @author  Artiom,Yoav
 */

public class SaveSolutionsNotification extends ObservableNotification<String> implements Serializable {

    private final String filePath;

    public SaveSolutionsNotification(String filePath) {
        this.filePath = filePath;
    }

    @Override
    public void apply() {
        model.toServer(this);
    }


    @Override
    public void init(IModel model) {
        this.model = model;
    }

    @Override
    public String getData() {
        return "solution saved at "+filePath;
    }

}
