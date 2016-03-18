package knoplab;

import net.imagej.Dataset;
import net.imagej.DatasetService;
import net.imagej.axis.CalibratedAxis;
import net.imagej.display.ImageDisplay;
import net.imglib2.Cursor;
import net.imglib2.RandomAccess;
import net.imglib2.algorithm.neighborhood.Neighborhood;
import net.imglib2.algorithm.neighborhood.RectangleShape;
import net.imglib2.img.Img;
import net.imglib2.type.numeric.RealType;
import net.imglib2.util.RealSum;

import org.scijava.ItemIO;
import org.scijava.command.Command;
import org.scijava.plugin.Parameter;
import org.scijava.plugin.Plugin;
import org.scijava.ui.UIService;

@Plugin(type = Command.class,menuPath = "Plugins>Command Tester",label="Test plugin")
public class CommandTester implements Command {

    @Parameter
    DatasetService datasetService;

    // we specify that the dataset is an input
    // ImageJ will inject the current dataset automatically
    @Parameter(type = ItemIO.BOTH)      
    Dataset dataset;
    
    // The image display gives information about the view.
    // e.g. which plane is currently viewed etc.
    // ImageJ will inject the current image display
    @Parameter
    ImageDisplay currentDisplay; 
    //
    
     @Parameter
    UIService uiService;
    
    /*
        This method is part of the plugin
    */
    
    public void run() {

        System.out.println("\nI'm running ! Here is the injected DatasetService : ");
        System.out.println(datasetService);
        
        System.out.println("\nAnd ImageJ takes care of setting the input for me !");
        System.out.println(dataset);
        
        // let's go through the different axes
        // first we create a array that contains informations about the axes of the image.
        CalibratedAxis[] axeArray = new CalibratedAxis[dataset.numDimensions()];
        
        // we fill it from the dataset
        dataset.axes(axeArray);
        
        for(CalibratedAxis axe :  axeArray) {
            System.out.println(String.format("Type = %s, unit = %s",axe.type().toString(),axe.unit()));
        }
        
        // Now let's go through the pixel of the image.
        // Let's say we want to go through the pixel of the current shown plane.
        // We must first know which plane is currently displayed.
        
//        long[] position = new long[currentDisplay.numDimensions()];
//        currentDisplay.localize(position);
        
        // The display position always put X and Y to 0
//        System.out.println("Position = "+Arrays.toString(position));
        
//        RandomAccess<RealType<?>> randomAccess = dataset.randomAccess();
        
//        long width = dataset.max(0);
//        long height = dataset.max(1);
        
//        System.out.println(String.format("Image %dx%d",width,height));
//       
//        DescriptiveStatistics stats = new DescriptiveStatistics();
//        randomAccess.setPosition(position);
//        for(int x = 0; x!=width;x++) {
//            for(int y = 0; y != height; y++) {
//                position[0] = x;
//                position[1] = y;
//               
//                randomAccess.setPosition(x,0);
//                randomAccess.setPosition(y,1);
//                
//                stats.addValue(randomAccess.get().getRealDouble());
                
//                System.out.println(String.format("(%d,%d) = %.0f",x,y,randomAccess.get().getRealDouble()));
//            }
//        }
        ///////////////////////////////////////////////////////////
        //////////////////// MEDIAN FILTER ///////////////////////
        /////////////////////////////////////////////////////////
        medianFilter();
        
    }
    
    public < T extends RealType< T >> void medianFilter(){
        Dataset copy = dataset.duplicateBlank();
        
        Img<T> source = (Img<T>) dataset.getImgPlus().getImg();
        
        Cursor<T> cursorInput = (Cursor<T>) source.localizingCursor();
        RandomAccess<T> randomAccessOutput = (RandomAccess < T >)copy.randomAccess();
        
        RectangleShape kernel = new RectangleShape(1, true);

        long[] dimensions = new long[5];
        
        for(Neighborhood<T> localNeighborhood : kernel.neighborhoods(source)){

            T centerValue = cursorInput.next();
            
            T sumNeighborhood = centerValue;
            sumNeighborhood.setZero();
            
            T numberOfNeighbourgs = sumNeighborhood.copy();
            
            for(T value : localNeighborhood){
                try{
                    sumNeighborhood.add(value);
                    numberOfNeighbourgs.inc();
                }
                catch(ArrayIndexOutOfBoundsException indexOut){
//                    System.out.println("Index out of bounds");
                }
            }
            sumNeighborhood.div(numberOfNeighbourgs);
            randomAccessOutput.setPosition(cursorInput);
            randomAccessOutput.get().set(sumNeighborhood);
        }
        uiService.show(copy);
    }
}