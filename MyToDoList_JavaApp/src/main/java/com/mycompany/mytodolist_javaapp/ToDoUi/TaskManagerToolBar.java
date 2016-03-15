/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp.ToDoUi;

import com.mycompany.mytodolist_javaapp.Plugin.TaskManagerPlugin;
import javafx.scene.control.Button;
import javafx.scene.control.ToolBar;
import org.scijava.Context;
import org.scijava.InstantiableException;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.PluginInfo;
import org.scijava.plugin.PluginService;

/**
 *
 * @author pierre
 */
public class TaskManagerToolBar extends ToolBar {
    @Parameter
    PluginService pluginService;
    
    public TaskManagerToolBar(Context context){
        context.inject(this);
        pluginService
                .getPluginsOfType(TaskManagerPlugin.class)
                .forEach(p->addPlugin(p, context));
   }
    
   public void addPlugin(PluginInfo<TaskManagerPlugin> pluginInfo, Context context){
       Button btn = new Button(pluginInfo.getLabel());
       try{
            TaskManagerPlugin plugin = pluginInfo.createInstance();
            context.inject(plugin);
            btn.setOnAction(actionEvent->applyPlugin(plugin));
       }
       catch(InstantiableException ie){
           System.out.println(ie);
       }
       this.getItems().add(btn);
   }
   
   public void applyPlugin(TaskManagerPlugin plugin){
       plugin.execute();
   }
}
