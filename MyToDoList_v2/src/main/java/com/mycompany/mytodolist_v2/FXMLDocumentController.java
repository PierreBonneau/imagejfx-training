/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.Observable;
import javafx.beans.property.Property;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxListCell;
import javafx.scene.layout.HBox;

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
    private ListView<TaskWrapper> listView;

    @FXML
    private Button deteleBtn;

    private ItfTaskList tasksList;
    
    ObservableList<TaskWrapper> wrapperList = FXCollections.observableArrayList();
    
    public void initialize(URL url, ResourceBundle rb) {
        tasksList = new TasksList();
        tasksList.addTaskListeners(this::onTaskEvent);
        listView.setCellFactory(this::addCell);
        tasksList.getTasks().forEach(t -> wrapperList.add(new TaskWrapper(t)));
        listView.setItems(wrapperList);
        textField.setPromptText("New task");
    }
    
    public void addTask(){
        tasksList.addTask(textField.getText());
        textField.clear();
    }
    
    public void checkAll() {
        wrapperList.forEach(w -> w.setDone(true));
        System.out.println("All checked");
    }
    
    public void deleteSelected() {
        List<ItfTask> toRemove =  new ArrayList<>();
        tasksList.getTasks().stream()
                .filter(t -> t.getDone())
                .forEach(t -> toRemove.add(t));
        for(ItfTask t : toRemove){
            tasksList.deleteTask(t);
        }
        //tasksList.getTasks().removeAll(toRemove);
        System.out.println("Selected element deleted");
    }
    
    public ListCell<TaskWrapper> addCell(ListView<TaskWrapper> listView){
        return new TaskCell();
    }
    
    public void onTaskEvent(TaskEvent event){
        if(event.getType() == TaskEnum.TASK_ADDED){
            wrapperList.add(new TaskWrapper(event.getTask()));
        }
        else if (event.getType() == TaskEnum.TASK_DELETED){
            wrapperList.remove(wrapperList.stream()
                    .filter(wrapper -> wrapper.getTask() == event.getTask())
                    .findFirst()
                    .orElse(null));
        }
    }
    
    public class TaskCell extends ListCell<TaskWrapper>{
        CheckBox cb = new CheckBox();
        Label label = new Label();
        HBox hbox = new HBox(cb, label);
        
        public TaskCell(){
            hbox.getStyleClass().add("list-cell");
            itemProperty().addListener(this::onItemChanged);
        }
        
        public void onItemChanged(Observable obs, TaskWrapper oldValue, TaskWrapper newValue){
            if (oldValue != null) {
                cb.selectedProperty().unbindBidirectional(oldValue.getDoneProperty());
            }

            if (newValue == null) {
                setGraphic(null);
            } 
            else {
            setGraphic(hbox);
            label.textProperty().setValue(newValue.getName());
            cb.selectedProperty().bindBidirectional(newValue.getDoneProperty());
            }
        }
    }
}

