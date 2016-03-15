/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp;

import java.util.List;
import org.scijava.service.SciJavaService;

/**
 *
 * @author pierre
 */
public interface TaskListService extends SciJavaService{
    public List<Task> getTasks();
    public void addTask(String name);
    public void addTask(Task task);
    public void checkAll();
    public void deleteTask(Task task);
    public void deleteSelected();
//    public void addTaskListeners(Consumer<TaskEvent> listener);
}
