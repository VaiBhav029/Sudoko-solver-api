package com.vaibahv.sudoku_solve.service;

import org.springframework.stereotype.Service;

@Service
public class SolverService {
    private final int GRID_SIZE =9;

    public  boolean isNumberInRow(int[][] board,int number,int row )
    {
        for(int i=0;i<GRID_SIZE;i++)
        {
            if(board[row][i]==number)
                return true;
        }
        return  false;
    }

    public  boolean isNumberInColumn(int[][] board,int number,int column )
    {
        for(int i=0;i<GRID_SIZE;i++)
        {
            if(board[i][column]==number)
                return true;
        }
        return  false;
    }

    public  boolean isNumberInBox(int[][] board, int number, int row, int column)
    {
        //board[1][4] = 3
        //row =1 -->row =0
        //col =4 -->col = 3

        //row - row %3 -> 1-1%3 - > 1 - 1 = 0
        //col - col %3

        int boxRow = row - row % 3;
        int boxCol = column - column % 3;

        for(int i = boxRow; i< boxRow+3;i++)
        {
            for ( int j =boxCol ; j< boxCol+3;j++)
            {
                if( board[i][j] == number)
                    return true;
            }
        }

        return  false;

    }

    public  boolean isValid(int[][] board, int number , int row , int column)
    {
        return !isNumberInBox(board,number,row,column) && !isNumberInColumn(board,number,column) && !isNumberInRow(board,number,row);
    }

    public  boolean solveBoard(int[][] board)
    {
        for(int i=0;i<GRID_SIZE;i++)
        {
            for(int j=0;j<GRID_SIZE;j++)
            {
                if(board[i][j]==0)
                {
                    for(int tryNumber=1;tryNumber<=GRID_SIZE ;tryNumber++)
                    {
                        if(isValid(board,tryNumber,i,j))
                        {
                            board[i][j]= tryNumber;
                            if(solveBoard(board))
                                return true;
                            else{
                                board[i][j]=0;
                            }

                        }
                    }
                    return  false;
                }

            }
        }
        return true;
    }
}
