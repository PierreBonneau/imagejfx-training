/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

import java.util.List;
import java.util.function.Consumer;
import javafx.collections.ObservableList;

/**
 *
 * @author pierre
 */
public interface ItfTaskList {
    public List<ItfTask> getTasks();
    public void addTask(String name);
    public void addTask(ItfTask task);
    public void deleteTask(ItfTask task);
    public void addTaskListeners(Consumer<TaskEvent> listener);
}
