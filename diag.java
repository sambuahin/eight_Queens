/**
 * Created by sam on 2/15/17.
 */
import java.util.*;
public class diag {
    public static int[][] b = new int[][]
                   {{ 0, 0, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 1, 0, 0, 0 },
                    { 1, 1, 0, 0, 0, 0, 0, 0 },
                    { 0, 0, 0, 1, 0, 0, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 0, 1 },
                    { 0, 0, 0, 0, 0, 1, 0, 0 },
                    { 0, 0, 0, 0, 0, 0, 1, 0 },
                    { 0, 0, 1, 0, 0, 0, 0, 0 }};
    public static int [] bb = new int [] {7,3,6,45,6};

   // public static HashMap hm = new HashMap();


    public static void main(String[]args)
    {
      queen1 g=new queen1();




        int [][]ss=new int [b.length][b[0].length];





        System.out.println("show original");
        dis(b);

        System.out.println("Heuristic:  " +g.checkHeuristic(b));
        System.out.println("Neighbors with higher h: "+ g.checkNeighbors(b));

       //g.setToNeightbor(b);

        //int [][] hhh = new int [8][8];
        g.setToNeightbor(b);
        dis(b);
        System.out.println("Heuristic:  " +g.checkHeuristic(b));
        dis(b);



        for(int l = 0;l<b.length;l++){
            if(l==b.length-1)
                System.out.printf("'%s'",b[l]);
            else
                System.out.printf("'%s',",b[l]);
        }



    }


    public static void dis2(int [] f)
    {
        for (int i=0;i<f.length;i++)
        {
            System.out.print(f[i] + " ");
        }
    }

    public static int lowest(int [] k)
    {
        int low=k[0];
        for (int i=0;i<k.length;i++)
        {
            if (k[i]<low)
            {
               low=k[i];
            }
        }
        return low;
    }

    public static void dis(int [][] b) {
        for (int i = 0; i < b.length; i++) {
            for (int j = 0; j < b[0].length; j++) {
                System.out.print(b[i][j] + " ");
            }
            System.out.println();
        }
    }


    public static int [][] changed( int [][] as, int u)
    {
            for (int i=0;i<as.length;i++)
            {as[i][u] = 1;}
            return as;
    }

    public static int [][] set1(int [][] ss, int row, int col)
    {
        ss[row][col]=9;
        return ss;
    }
    public static void copy(int[][]main, int[][]copy)
    {
        for (int y = 0; y < main.length; y++)
        {
            for (int x = 0; x < main[0].length; x++)
            {
                copy[y][x] = main[y][x];
            }
        }
    }

}
