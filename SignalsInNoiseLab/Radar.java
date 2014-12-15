import java.util.Scanner;
/**
 * The model for radar scan and accumulator
 * 
 * @author @gcschmit
 * @version 19 July 2014
 */
public class Radar
{
    
    // stores whether each cell triggered detection for the current scan of the radar
    private boolean[][] currentScan;
    private boolean[][] previousScan;
    
    // value of each cell is incremented for each scan in which that cell triggers detection 
    private int[][] accumulator;
    
    // location of the monster
    private int monsterLocationRow;
    private int monsterLocationCol;

    // probability that a cell will trigger a false detection (must be >= 0 and < 1)
    private double noiseFraction;
    
    // number of scans of the radar since construction
    private int numScans;
    
    private int dx;
    private int dy;
   

    /**
     * Constructor for objects of class Radar
     * 
     * @param   rows    the number of rows in the radar grid
     * @param   cols    the number of columns in the radar grid
     */
    public Radar(int rows, int cols)
    {
        // initialize instance variables
        currentScan = new boolean[rows][cols]; // elements will be set to false
        accumulator = new int[11][11]; // elements will be set to 0
        previousScan = new boolean[rows][cols];
        // randomly set the location of the monster (can be explicity set through the
        //  setMonsterLocation method
        monsterLocationRow = (int)(Math.random() * rows);
        monsterLocationCol = (int)(Math.random() * cols);
        
        noiseFraction = 0.05;
        numScans= 0;
        Scanner s = new Scanner(System.in);
        System.out.print("Enter a DX: ");
        dx=s.nextInt();
        System.out.print("Enter a DY: ");
        dy=s.nextInt();
        
    }
    
    /**
     * Performs a scan of the radar. Noise is injected into the grid and the accumulator is updated.
     * 
     */
    public void scan()
    {
        for (int i = 0; i < currentScan.length; i++)
        {
            for (int j = 0; j < currentScan.length; j++)
            {
                previousScan[i][j] = currentScan[i][j];
            }
        }
        monsterLocationRow += dy;
        monsterLocationCol += dx;
        
        if (monsterLocationCol<0)
        {
            monsterLocationCol=getNumCols()-1;
        }
        if (monsterLocationCol>getNumCols()-1)
        {
            monsterLocationCol=0;
        }
        if (monsterLocationRow<0)
        {
            monsterLocationRow=getNumRows()-1;
        }
        if (monsterLocationRow>getNumRows()-1)
        {
            monsterLocationRow=0;
        }
        // zero the current scan grid
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                currentScan[row][col] = false;
            }
        }
        
        // detect the monster
        currentScan[monsterLocationRow][monsterLocationCol] = true;
        
        // inject noise into the grid
        injectNoise();
        
        // udpate the accumulator
        for(int row = 0; row < previousScan.length; row++)
        {
            for(int col = 0; col < previousScan[0].length; col++)
            {
                if (previousScan[row][col] == true)
                {
                    for (int row1 = 0; row1 < currentScan.length; row1++)
                    {
                        for (int col1 = 0; col1 < currentScan.length; col1++)
                        {
                            if (currentScan[row1][col1]==true)
                            {
                                if (Math.abs(row1-row)<=5 && (Math.abs(col1-col)<=5))
                                {
                                    accumulator[row1-row+5][col1-col+5]++;
                                }
                            }
                        }
                    }
                }
            }
        }
     
        
        // keep track of the total number of scans
        numScans++;
    }

    public int[] findMax()
    {
        int max=0; 
        int[] list = new int[2];
        for (int i=0; i<accumulator.length; i++)
        {
            for (int j=0; j<accumulator[0].length; j++)
            {
                if (accumulator[i][j]>max)
                {
                    max=accumulator[i][j];
                    list[0]=i-5;
                    list[1]=j-5;
                }
            }
        }
        return list;
    }
    
    /**
     * Sets the location of the monster
     * 
     * @param   row     the row in which the monster is located
     * @param   col     the column in which the monster is located
     * @pre row and col must be within the bounds of the radar grid
     */
    public void setMonsterLocation(int row, int col)
    {
        // remember the row and col of the monster's location
        monsterLocationRow = row;
        monsterLocationCol = col;
        
        // update the radar grid to show that something was detected at the specified location
        currentScan[row][col] = true;
    }
    
     /**
     * Sets the probability that a given cell will generate a false detection
     * 
     * @param   fraction    the probability that a given cell will generate a flase detection expressed
     *                      as a fraction (must be >= 0 and < 1)
     */
    public void setNoiseFraction(double fraction)
    {
        noiseFraction = fraction;
    }
    
    /**
     * Returns true if the specified location in the radar grid triggered a detection.
     * 
     * @param   row     the row of the location to query for detection
     * @param   col     the column of the location to query for detection
     * @return true if the specified location in the radar grid triggered a detection
     */
    public boolean isDetected(int row, int col)
    {
        return currentScan[row][col];
    }
    
    /**
     * Returns the number of times that the specified location in the radar grid has triggered a
     *  detection since the constructor of the radar object.
     * 
     * @param   row     the row of the location to query for accumulated detections
     * @param   col     the column of the location to query for accumulated detections
     * @return the number of times that the specified location in the radar grid has
     *          triggered a detection since the constructor of the radar object
     */
    public int getAccumulatedDetection(int row, int col)
    {
        return accumulator[row][col];
    }
    
    /**
     * Returns the number of rows in the radar grid
     * 
     * @return the number of rows in the radar grid
     */
    public int getNumRows()
    {
        return currentScan.length;
    }
    
    /**
     * Returns the number of columns in the radar grid
     * 
     * @return the number of columns in the radar grid
     */
    public int getNumCols()
    {
        return currentScan[0].length;
    }
    
    /**
     * Returns the number of scans that have been performed since the radar object was constructed
     * 
     * @return the number of scans that have been performed since the radar object was constructed
     */
    public int getNumScans()
    {
        return numScans;
    }
    
    /**
     * Sets cells as falsely triggering detection based on the specified probability
     * 
     */
    private void injectNoise()
    {
        for(int row = 0; row < currentScan.length; row++)
        {
            for(int col = 0; col < currentScan[0].length; col++)
            {
                // each cell has the specified probablily of being a false positive
                if(Math.random() < noiseFraction)
                {
                    currentScan[row][col] = true;
                }
            }
        }
    }
    
}
