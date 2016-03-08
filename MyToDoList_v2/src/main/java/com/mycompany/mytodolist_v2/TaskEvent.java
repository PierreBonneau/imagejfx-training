/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

/**
 *
 * @author pierre
 */
public class TaskEvent {
    private TaskEnum type;
    private ItfTask task;
    
    public TaskEvent(TaskEnum type, ItfTask task){
        this.type = type;
        this.task = task;
    }
    
    public TaskEnum getType(){
        return this.type;
    }
    
    public ItfTask getTask(){
        return this.task;
    }
}
