import javax.swing.JFrame;

/**
 * Class that contains the main method for the program and creates the frame containing the component.
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class RadarViewer
{
    /**
     * main method for the program which creates and configures the frame for the program
     *
     */
    public static void main(String[] args) throws InterruptedException
    {
        // create the radar, set the monster location, and perform the initial scan
        final int ROWS = 100;
        final int COLS = 100;
        Radar radar = new Radar(ROWS, COLS);
        radar.setNoiseFraction(0.02);
        radar.scan();
        
        JFrame frame = new JFrame();
        
        frame.setTitle("Signals in Noise Lab: ");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        RadarComponent component = new RadarComponent(radar);
        frame.add(component);
        frame.pack();
        frame.setVisible(true);
        // perform 100 scans of the radar wiht a slight pause between each
        // after each scan, instruct the Java Run-Time to redraw the window
        for(int i = 0; i < 300; i++)
        {
            Thread.sleep(5); // sleep 100 milliseconds (1/10 second)
            
            radar.scan();
            
            frame.repaint();
            
            
        }
        int[] list = radar.findMax();
        System.out.println("DX: " +list[1]);
        System.out.println("DY: " +list[0]);
    }

}
