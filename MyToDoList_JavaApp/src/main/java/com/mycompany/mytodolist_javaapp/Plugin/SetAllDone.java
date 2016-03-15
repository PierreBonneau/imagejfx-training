/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp.Plugin;

import com.mycompany.mytodolist_javaapp.TaskListService;
import org.scijava.Contextual;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 *
 * @author pierre
 */
@Plugin(type = TaskManagerPlugin.class, label = "Select All", priority = 10)
public class SetAllDone implements TaskManagerPlugin{
    
    @Parameter
    TaskListService tasksList;
    
    @Override
    public void execute(){
        tasksList.checkAll();
    }
}
