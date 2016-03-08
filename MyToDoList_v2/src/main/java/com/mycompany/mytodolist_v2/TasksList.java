/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.CheckBox;

/**
 *
 * @author pierre
 */
public class TasksList implements ItfTaskList{
    public List<ItfTask> tasks = new ArrayList<>();
    public List<Consumer<TaskEvent>> listenersList = new ArrayList<>();
    
    @Override
    public List<ItfTask> getTasks(){
        return this.tasks;
    }
    @Override
    public void addTask(String name){
        addTask(new Task(name));
    }
    
    @Override
    public void addTask(ItfTask task){
        tasks.add(task);     
        fireEvent(new TaskEvent(TaskEnum.TASK_ADDED, task));
        System.out.println("Task added");
    }
    
    @Override
    public void deleteTask(ItfTask task) {
        tasks.remove(task);
        fireEvent(new TaskEvent(TaskEnum.TASK_DELETED, task));
        System.out.println("Task deleted");
    }
    
    @Override
    public void addTaskListeners (Consumer<TaskEvent> listener){
        listenersList.add(listener);
    }
    
    public void fireEvent(TaskEvent event){
        for (Consumer<TaskEvent> listener : listenersList){
            listener.accept(event);
        }
    }
}