/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package knoplab;

import io.scif.services.DatasetIOService;
import net.imagej.Dataset;
import org.scijava.command.CommandService;
import org.scijava.event.EventHandler;
import org.scijava.event.EventService;
import org.scijava.io.event.DataOpenedEvent;
import org.scijava.plugin.Parameter;
import org.scijava.ui.UIService;

/**
 *
 * @author pierre
 */
public class AutoOpener {
    @Parameter
    CommandService commandService;
        
    @Parameter
    DatasetIOService datasetIoService;
        
    @Parameter
    UIService uiService;
        
    @Parameter
    EventService eventService;
        
    @EventHandler
    public void onDatasetCreated(DataOpenedEvent event) {
        System.out.println("Damn !");
        commandService.run(CommandTester.class, true);
    }
    
    public void openExampleFile() throws Exception{
        Dataset dataset = datasetIoService.open("src/main/resources/16bit-multidim.tif");
        uiService.show(dataset);
//        eventService.publish(new DataOpenedEvent(dataset.getSource(), dataset));
    }
}
