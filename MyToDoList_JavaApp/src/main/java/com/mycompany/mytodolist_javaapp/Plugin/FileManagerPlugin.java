/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.mytodolist_javaapp.Plugin;

import java.io.IOException;
import org.scijava.plugin.SciJavaPlugin;

/**
 *
 * @author pierre
 */
public interface FileManagerPlugin extends SciJavaPlugin{
    public void execute() throws IOException;    
}
