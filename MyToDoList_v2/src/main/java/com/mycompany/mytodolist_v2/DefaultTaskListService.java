/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import org.scijava.event.DefaultEventService;
import org.scijava.event.EventService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.service.AbstractService;
import org.scijava.service.SciJavaService;

/**
 *
 * @author pierre
 */

@Plugin(type = SciJavaService.class)
public class DefaultTaskListService extends AbstractService implements TaskListService{
    
    public List<Task> tasks = new ArrayList<>();
    public List<Consumer<TaskEvent>> listenersList = new ArrayList<>();
    
    @Parameter
    EventService eventService;// = new DefaultEventService();
    
    @Override
    public List<Task> getTasks(){
        return this.tasks;
    }
    @Override
    public void addTask(String name){
        addTask(new DefaultTask(name));
    }
    
    @Override
    public void addTask(Task task){
        tasks.add(task);
        TaskEvent e = new TaskEvent(TaskEnum.TASK_ADDED, task);
        eventService.getInfo();
        eventService.publish(e);
//        fireEvent(new TaskEvent(TaskEnum.TASK_ADDED, task));
        System.out.println("Task added");
    }
    
    @Override
    public void deleteTask(Task task) {
        tasks.remove(task);
        eventService.publish(new TaskEvent(TaskEnum.TASK_DELETED, task));
//        fireEvent(new TaskEvent(TaskEnum.TASK_DELETED, task));
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