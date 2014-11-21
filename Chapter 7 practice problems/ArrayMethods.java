public class ArrayMethods
{
    private int[] values;
    public ArrayMethods(int[] initialValues)
    {
        this.values=initialValues;
    }
    
    public void swapFirstAndLast()
    {
        int tempValue = this.values[values.length];
        this.values[0] = this.values[values.length];
        this.values[values.length] = tempValue;
    }
    
    public void shiftRight()
    {
        for (int i = 0; i < this.values.length; i++)
        {
            if (i < (values.length-1))
            {
                this.values[i] = this.values[i + 1];
            }
            else
            {
                this.values[0] = this.values[i];
            }
        }
    }
    
    public static void rotateRight(int[] arr)
    {
       int last = arr.length - 1;
       int temp = arr[last];
       
       for (int i = last; i > 0; i--)
       {
           arr[i] = arr[i-1];
       }
       arr[0] = temp;
    }
    
    
    
    
    
    
}