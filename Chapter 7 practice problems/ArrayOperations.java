public class ArrayOperations
{
    public static void main(String[] args)
    {
        double[] x = {8, 4 , 5 , 21 ,7 ,9 ,18, 2, 100};
        System.out.println(x.length);
        System.out.println(x[0]);
        System.out.println(x[8]);
        System.out.println(x[x.length-1]);
        for (int i = 0; i <= 8; i++)
        {
            System.out.println(x[i]);
        }
        
         for (int i = 0; i <= 8; i++)
        {
            System.out.print(i+"\t");
            System.out.println(x[i]);
        }
        
        for (int i = 8; i >= 0; i--)
        {
            System.out.print(i+"\t");
            System.out.println(x[i]);
        }
        
        for (double i : x)
        {
            System.out.println(i);
        }
    }
}