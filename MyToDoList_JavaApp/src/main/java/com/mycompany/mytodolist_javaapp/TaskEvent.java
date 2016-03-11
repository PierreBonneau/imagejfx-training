/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp;

import org.scijava.event.SciJavaEvent;

/**
 *
 * @author pierre
 */
public class TaskEvent extends SciJavaEvent{
    private final TaskEnum type;
    private final Task task;
    
    public TaskEvent(TaskEnum type, Task task){
        this.type = type;
        this.task = task;
    }
    
    public TaskEnum getType(){
        return this.type;
    }
    
    public Task getTask(){
        return this.task;
    }
}
