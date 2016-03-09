/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

import org.scijava.service.AbstractService;

/**
 *
 * @author pierre
 */
public class DefaultTask implements Task{
    private String name;
    private boolean done;
    
    public DefaultTask(String name){
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
