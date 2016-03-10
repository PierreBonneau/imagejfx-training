/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

import javafx.beans.property.Property;
import javafx.beans.property.adapter.JavaBeanObjectPropertyBuilder;
import javafx.beans.property.adapter.JavaBeanStringPropertyBuilder;

/**
 *
 * @author pierre
 */
public class TaskWrapper implements Task{
    private Task task;
    private Property<String> nameProperty;
    private Property<Boolean> doneProperty;
    
    public TaskWrapper(Task task){
        this.task = task;
        try {
            this.nameProperty = new JavaBeanStringPropertyBuilder().bean(task).name("name").build();
            this.doneProperty = new JavaBeanObjectPropertyBuilder<>().bean(task).name("done").build();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Override
    public String getName(){
        return task.getName();
    }
    
    @Override
    public boolean getDone(){
        return task.getDone();
    }
    
    @Override
    public void setDone(boolean done){
        this.doneProperty.setValue(done);
    }
    
    @Override
    public void setName(String name){
        this.nameProperty.setValue(name);
    }
    
    public Property<String> getNameProperty(){
        return this.nameProperty;
    }
    
    public Property<Boolean> getDoneProperty(){
        return this.doneProperty;
    }
    
    public Task getTask(){
        return this.task;
    }
}
