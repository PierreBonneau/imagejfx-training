/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mytodolist;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

/**
 *
 * @author pierre
 */
public class FXMLDocumentController implements Initializable {    
    @FXML
    private Label title;

    @FXML
    private TextField textField;

    @FXML
    private Button submitBtn;

    @FXML
    private Button checkAllBtn;

    @FXML
    private ListView<CheckBox> listView;

    @FXML
    private Button deteleBtn;

    private TasksList tasksList;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        tasksList = new TasksList();
        listView.setItems(tasksList.tasks);
        textField.setPromptText("New task");
    }

    public void addTask() {
        tasksList.tasks.add(new CheckBox(textField.getText()));
        textField.clear();
    }

    public void checkAll() {
        tasksList.tasks.stream()
                .forEach(t -> t.setSelected(true));
    }

    public void deleteSelected() {
        List<CheckBox> toRemove =  new ArrayList<>();
        tasksList.tasks.stream()
                .filter(p -> p.isSelected())
                .forEach(t -> toRemove.add(t));
        for(CheckBox cb : toRemove){
            tasksList.tasks.remove(cb);
        }
    }   
}
