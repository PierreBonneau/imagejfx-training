/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp.Plugin;

import com.mycompany.mytodolist_javaapp.TaskListService;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 *
 * @author pierre
 */
@Plugin(type = TaskManagerPlugin.class, label = "Delete selected", priority = 0)

public class DeleteAllSelected implements TaskManagerPlugin{
    @Parameter
    TaskListService tasksList;
            
    @Override
    public void execute(){
        tasksList.deleteSelected();
    }
}
