/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp;

import com.mycompany.mytodolist_javaapp.ToDoUi.ToDoUi;
import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.scijava.SciJava;

/**
 *
 * @author pierre
 */
public class MyToDoListMain extends Application {
    
    @Override
    public void start(Stage stage) throws Exception {
        SciJava scijava = new SciJava();
//        Context context = new Context();
//        scijava.setContext(context);
        ToDoUi ui = new ToDoUi(scijava.context());

//        scijava.context().inject(ui);

        Scene scene = new Scene(ui, 500, 400);
        
        stage.setTitle("TODO");
        stage.setScene(scene);
        stage.setMinHeight(300.0);
        stage.setMinWidth(400.0);
        
        stage.show();
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
