//Buahin Samuel
//Intro to AI – ITCS 3153
//Program Assignment #1

/**
 * Created by sam on 2/13/17.
 */
import java.util.Random;

public class queen1 {
    public static int row=8;//number of rows
    public static int col=8;//number of columns
    public static int [][] mainboard = new int [row][col]; //this will hold the main board
    public static int restart=0;//counts the number of restarts
    public static int statechange=0;//holds the number of state changes
    public static int Firstheuristic=0;

    public static void main(String[]args)
    {
        setBoard();//sets the board to all 0's(initialize)
        randomGenerate();//generates a random board
        Firstheuristic=checkHeuristic(mainboard);//checs the boards heuristic
        System.out.println("\n\nCurrent h: " +Firstheuristic);//checks the heurstic of the new board
        displayBoard(mainboard);//displays the first board
        System.out.println("Neighbors found with lower h: " +checkNeighbors(mainboard));

        //if the first heuristic is ) it will jump the while loop and say solution found
       while(Firstheuristic!=0) {
           if(checkNeighbors(mainboard)==0){//if the neighbors with h are 0 then it will restart
               System.out.println("RESTART\n\n");//displays restart to the console
               restart++;//increases restart counter by 1
               setBoard();//sets the board to 0
               randomGenerate();//generates another random board
           }
           else {
                   setToNeightbor(mainboard);//if neighbors exists, it checks the neighbor with the lowest h and sets the
                   System.out.println("Setting new current state\n");//current state to that state.
                   statechange++;//increments stateschange counter by 1
                }

           Firstheuristic=checkHeuristic(mainboard);//checks the heuristics of the current state
           System.out.println("Current h: " +Firstheuristic);
           displayBoard(mainboard);//displays the board
           System.out.println("Neighbors found with lower h: " +checkNeighbors(mainboard));
       }//end of while loop

        //program comes here is solution is found
        System.out.println();
        displayBoard(mainboard);//displays the board
        System.out.println("Solution Found!");
        System.out.println("State changes: "+statechange);
        System.out.println("Restarts: "+restart);
    }
    ///end of main

    //generates a 2D array with random rows=1 on every column
    public static void randomGenerate() {
        int [] r;
        r=randInt();
        for (int i=0;i<row; i++) {mainboard[r[i]][i]= 1;}
    }

    //sets everything on the matrix board equal to 0
    public static void setBoard() {
        for(int i=0;i<row;i++) {
            for(int j=0;j<col;j++) {mainboard[i][j] = 0;}}
    }

//generates an array of 8 random numbers between 0 - 7
    public static int [] randInt() {
        Random rand = new Random();
        int[]rand8Array = new int[8];
        for (int i=0; i < 8; i++) {
            int rd = rand.nextInt((7 - 0) + 1) + 0;
            rand8Array[i] = rd;
        }return rand8Array;
    }

    //displays the board
    public static void displayBoard(int [][] b) {
        System.out.println("Current State");
        for(int i = 0; i < b.length; i++){
            for(int j = 0; j < b[0].length; j++){
                if(j == b[i].length-1){//removes the comma at the end of a line of array elements
                    System.out.print(b[i][j] +" ");
                }
                else{
                    System.out.print(b[i][j] + ",");
                }}
            System.out.println();
        }
    }

    //checks heuristic
    //heuristic was checked horizontally, diagonally from left to right and diagonally from right to left.
    public static int checkHeuristic(int [][] bd) {
        int heuristic=0;
        //check horizontal left to right
        //counts all the 1's and if it more than 1 it subtracts 1 and adds it to the heuristic value
        for(int i=0;i<bd.length; i++) {
            int c=0;//inside counter
            for (int j=0; j<bd[0].length; j++) {if(bd[i][j]==1) {c++;}}
            while (c>1) {heuristic = heuristic + c-1; break;}
        }

        ///check diagonal from left to right down
        //counts all the 1's and if it more than 1 it subtracts 1 and adds it to the heuristic value
        int row, col;
        int rowCount = bd.length;
        int columnCount = bd[0].length;

        for (int k = 0; k < rowCount; k++) {
            int c=0;
            for (row = k, col = 0; row >= 0 && col < columnCount; row--, col++) {
                if(bd[row][col]==1) {c++;}
            }
            while (c>1) {heuristic = heuristic + c-1; break;}
        }
        //second half check
        //counts all the 1's and if it more than 1 it subtracts 1 and adds it to the heuristic value
        for (int k = 1; k < columnCount; k++) {
            int c=0;
            for (row = rowCount - 1, col = k; row >= 0 && col < columnCount; row--, col++) {
                if(bd[row][col]==1) {c++;}
            }
            while (c>1) {heuristic = heuristic + c-1; break;}
        }

        ///// check from left to right up
        //counts all the 1's and if it more than 1 it subtracts 1 and adds it to the heuristic value
        for (int i = bd.length - 1; i > 0; i--) {
            int c=0;
            for (int j = 0, x = i; x <= bd.length - 1; j++, x++) {
                if(bd[x][j]==1) {c++;}
            }
            while (c>1) {heuristic = heuristic + c-1; break;}
        }

        //second half check
        //counts all the 1's and if it more than 1 it subtracts 1 and adds it to the heuristic value
        for (int i = 0; i <= bd.length - 1; i++) {
            int c=0;
            for (int j = 0, y = i; y <= bd.length - 1; j++, y++) {
                if(bd[j][y]==1) {c++;}
            }
            while (c>1) {heuristic = heuristic + c-1; break;}
        }return heuristic;
    }

    //this one will change the column of a matrix to 0s
    public static int [][] zeroOut( int [][] as, int u) {
        for (int i=0;i<as.length;i++)
        {as[i][u] = 0;}
        return as;
    }

 //   this will set the data to 1
    public static int [][] set1(int [][] ss, int rows, int cols) {
        ss[rows][cols]=1;
        return ss;
    }

    //makes a copy of an array
    public static void copyray(int[][]main, int[][]copy) {
        for (int y = 0; y < main.length; y++) {
            for (int x = 0; x < main[0].length; x++) {
                copy[y][x] = main[y][x];
            }}
    }

    //this method checks for the neighbor with the lowest h value
    //and returns an int
    public static int checkNeighbors(int [][] mn) {
    int low=0;
    for (int i=0;i<mn.length;i++) {
        for(int j=0;j<mn[0].length;j++) {
            int[][]copym=new int[mn.length][mn[0].length];
            copyray(mn, copym);//make a copy of board
            zeroOut(copym, i);//zero out one column at a time
            set1(copym, j, i);//set one row of the column to 1 at a time

            if (checkHeuristic(copym)<checkHeuristic(mn))
            { low++; }
        }}return low;
    }

    ///this will replace the current board with one of the neighbours with the lowest h
    public static void setToNeightbor(int [][] mn) {
        int lowestN = checkHeuristic(mn);//set the lowest heuristic to this
        int[][] tempBoard = new int[mn.length][mn[0].length];
        copyray(mn, tempBoard);//make a copy of the matrix board
        for (int r = 0; r < mn.length; r++) {
            for (int j = 0; j < mn[0].length; j++) {
                int[][] copym = new int[mn.length][mn[0].length];
                copyray(mn, copym);//copy the board into another so we dnt mess it up
                zeroOut(copym, r);//zero out the each column
                set1(copym, j, r);//set the zeroed out column to 1 one at a time

                if (checkHeuristic(copym) < lowestN) {//we check for the matrix with the least h value
                    lowestN=checkHeuristic(copym);
                    copyray(copym, tempBoard);//we copy the matrix into temp if it has a lower h value
                }}}
        copyray(tempBoard, mn);//when found we copy that matrix into the mainBoard
    }
        /////end of all the methods
}