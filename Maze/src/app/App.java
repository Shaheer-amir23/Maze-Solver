package app;

import java.io.*;
import java.util.Scanner;

public class App {
    public static void main(String[] args) throws FileNotFoundException {

        Scanner input = new Scanner(new File("Maze/Maze.txt"));
        String temp = input.nextLine();
        String[] temporary = temp.split(",");
        int row = Integer.parseInt(temporary[0]), startRow = 0;
        int column = Integer.parseInt(temporary[1]), startColumn = 0;
        char[][]floor = new char[row][column];

        while(input.hasNextLine()){
            for (int i = 0; i < floor.length; i++){
                floor[i] = input.nextLine().toCharArray();
            }
        }
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[0].length; j++) {
                if(floor[i][j] == 'e'){
                    startRow = i;
                    startColumn = j;
                }
            }
        }
        display(startRow, startColumn, floor);
        findPath( startRow, startColumn, floor);
        display(startRow, startColumn, floor);
        input.close();
    }
    public static void display(int Srow, int Scolumn, char[][] floor) {
        floor[Srow][Scolumn] = 'e';
        for (int i = 0; i < floor.length; i++) {
            for (int j = 0; j < floor[0].length; j++) {
                System.out.print(floor[i][j]);
            }
            System.out.println();
        }
        System.out.println();
    }

    // Initialize the DFS algorithm
    public static boolean findPath(int row, int column, char[][]floor) {
        if (row < 0 ||  row >= floor.length || column < 0 || column >= floor[0].length) {
            return false;
        }
        if (floor[row][column] == 'x') {
            return true;
        }
        if (floor[row][column] == '%' || floor[row][column] == '+') {
            return false;
        }
        floor[row][column] = '+';

        if (findPath( row - 1, column, floor)) {
            return true;
        }
        if (findPath( row + 1,column, floor)) {
            return true;
        }
        if (findPath(row, column - 1,floor)) {
            return true;
        }
        if (findPath(row, column + 1, floor)) {
            return true;
        }
        floor[row][column] = '.';

        return false;
    }
}