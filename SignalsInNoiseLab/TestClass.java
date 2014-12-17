import java.util.Scanner; 
import static org.junit.Assert.*; 
import org.junit.After; 
import org.junit.Before; 
import org.junit.Test; 
 
 
 
/** 
 
* Write a description of test class TestClass here. 
 
*  
 
* @author Duncan Molloy
 
* @version 12/15/14 
 
*/ 
 
public class TestClass 
 
{ 
    @Test 
     
    public void TestRadar1() 
     
    { 
     
        Radar radar = new Radar(100,100); 
     
        for(int i = 0; i < 300; i++) 
     
        { 
            radar.scan();  
        } 
     
        int[] findMax = radar.findMax(); 
        assertEquals(1, findMax[0]); 
        assertEquals(1, findMax[1]); 
     
    } 
     
     
     
    @Test 
     
    public void TestRadar2() 
     
    { 
        Radar radar = new Radar(100,100);  
        for(int i = 0; i < 300; i++) 
        { 
            radar.scan(); 
        } 
        int[] findMax = radar.findMax(); 
        assertEquals(2, findMax[0]); 
        assertEquals(2, findMax[1]); 
     
    } 
} 
