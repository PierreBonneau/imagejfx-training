/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp.ToDoUi;

import com.mycompany.mytodolist_javaapp.Plugin.TaskManagerPlugin;
import com.mycompany.mytodolist_javaapp.Task;
import com.mycompany.mytodolist_javaapp.TaskEnum;
import com.mycompany.mytodolist_javaapp.TaskEvent;
import com.mycompany.mytodolist_javaapp.TaskListService;
import com.mycompany.mytodolist_javaapp.TaskWrapper;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javafx.application.Platform;
import javafx.beans.Observable;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import org.scijava.Context;
import org.scijava.event.EventHandler;
import org.scijava.plugin.Parameter;

/**
 *
 * @author pierre
 */
public class ToDoUi extends BorderPane {
    
    @FXML
    private TextField textField;

    @FXML
    private ListView<TaskWrapper> listView;

    @FXML
    private final ToolBar toolBarTaskManager;
    
    @FXML
    private final ToolBar toolBarFileManager;

    @Parameter
    TaskListService tasksList;
    
    @Parameter
    TaskManagerPlugin taskManager;
    
    ObservableList<TaskWrapper> wrapperList = FXCollections.observableArrayList();

    public ToDoUi(Context context) throws IOException{
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/FXMLDocument.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
        context.inject(this);
        
        toolBarTaskManager = new TaskManagerToolBar(context);
        toolBarFileManager = new FileManagerToolBar(context);
        
        this.setBottom(toolBarTaskManager);
        this.setTop(toolBarFileManager);
        
        listView.setCellFactory(this::addCell);
        listView.setItems(wrapperList);
        textField.setPromptText("New task");
    }
    
    public void addTask(){
        tasksList.addTask(textField.getText());
        textField.clear();
    }
    
    public ListCell<TaskWrapper> addCell(ListView<TaskWrapper> listView){
        return new TaskCell();
    }
    
    @EventHandler
    public void onTaskEvent(TaskEvent event){
        if(event.getType() == TaskEnum.TASK_ADDED){
            Platform.runLater(() -> wrapperList.add(new TaskWrapper(event.getTask())));
        }
        else if (event.getType() == TaskEnum.TASK_DELETED){
            Platform.runLater(
            () -> wrapperList.remove(wrapperList.stream()
                    .filter(wrapper -> wrapper.getTask() == event.getTask())
                    .findFirst()
                    .orElse(null)));
        }
        else if (event.getType() == TaskEnum.ALL_SELECTED){
            Platform.runLater(() -> wrapperList.stream().forEach(w -> w.setDone(true)));
        }
        else if (event.getType() == TaskEnum.TASK_LOADED){
            Platform.runLater(() -> tasksList.getTasks().stream()
                    .forEach((t) -> wrapperList.add(new TaskWrapper(t))));
        }
    }
//    @EventHandler
//    public void onCheckAllEvent(CheckAllEvent event){
//        Platform.runLater(() -> wrapperList.stream().forEach(w -> w.setDone(true)));
//    }
    
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

