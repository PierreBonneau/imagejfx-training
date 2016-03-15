/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp.Plugin;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.mycompany.mytodolist_javaapp.DefaultTask;
import com.mycompany.mytodolist_javaapp.DefaultTaskListService;
import com.mycompany.mytodolist_javaapp.Task;
import com.mycompany.mytodolist_javaapp.TaskListService;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;

/**
 *
 * @author pierre
 */
@Plugin(type = FileManagerPlugin.class, label = "Save tasks", priority = 10)
public class SaveTasks implements FileManagerPlugin{
    @Parameter
    TaskListService tasksList;
    
    @Override
    public void execute() throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        mapper.writeValue(new File("/home/pierre/Desktop/tasks_save.json"), tasksList.getTasks());

        System.out.println("All tasks have been saved");

    }
}
