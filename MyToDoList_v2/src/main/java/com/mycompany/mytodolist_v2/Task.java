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
public class Task implements ItfTask{
    private String name;
    private boolean done;
    
    public Task(String name){
        this.name = name;
        this.done = false;
    }
    
    @Override
    public void setName(String name){
        this.name = name;
    }
    
    @Override
    public void setDone(boolean done){
        this.done = done;
    }
    
    @Override
    public String getName(){
        return this.name;
    }
    
    @Override
    public boolean getDone(){
        return this.done;
    }
}
