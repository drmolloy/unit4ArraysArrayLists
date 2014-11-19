import java.util.Scanner;
public class RandomDistribution
{
    public static void main()
    {
        System.out.print("how many numbers should be generated\t:");
        Scanner in = new Scanner(System.in);
        int maxGenerated = in.nextInt();
        System.out.print("What are the values possible\t:");
        int maxValue = in.nextInt();
        int[] counter = new int[maxValue];
        for ( int i = 0; i<maxValue; i++)
        {
            counter[i] = 0;
        }
        for (int i = 0; i < maxGenerated; i++)
        {
            int numberRolled =(int) (Math.random()*maxValue);
            counter[numberRolled] +=1;
        }
        
        for ( int i = 0; i<maxValue; i++)
        {
            System.out.print(i+"\t");
            System.out.println(counter[i]);
        }
    }
}