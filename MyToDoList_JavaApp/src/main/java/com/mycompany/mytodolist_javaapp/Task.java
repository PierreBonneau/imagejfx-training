/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp;

import javafx.collections.ObservableList;

/**
 *
 * @author pierre
 */
public interface Task {
    public void setName(String name);
    public void setDone(boolean done);
    
    public String getName();
    public boolean getDone();
}
