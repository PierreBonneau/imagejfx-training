/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_v2;

import javafx.collections.ObservableList;

/**
 *
 * @author pierre
 */
public interface ItfTask {
    public void setName(String name);
    public void setDone(boolean done);
    
    public String getName();
    public boolean getDone();
}
