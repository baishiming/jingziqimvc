package com.example.jingziqimvc.model;

import android.view.View;
import android.widget.Button;

/**
 * @author bsm
 * @name jingziqimvc
 * @class name：com.example.jingziqimvc.model
 * @class describe
 * @time 2020/3/19 10:37
 */
public class Board {
    private Cell[][] cells = new Cell[3][3];
    private Player winner;
    private GameState state;
    private Player currentTurn;

    public Board() {
    }

    public Player getWiner() {
        return winner;
    }

    public void restart(){
        clearCells();
        winner = null;
        currentTurn = Player.X;
        state = GameState.IN_PROGRESS;
    }

    private void clearCells() {
        for (int i=0; i<3;i++){
            for (int j=0;j<3;j++){
                cells[i][j] = new Cell();
            }
        }
    }


    public Player mark(int row, int col){
        Player playerThatMoved = null;
        if(isValid(row, col)){
            cells[row][col].setValue(currentTurn);
            playerThatMoved = currentTurn;

            if(isWinningMoveByPlayer(currentTurn,row,col)){
                state = GameState.FINISHED;
                winner = currentTurn;
            }else {
                //切换另一个棋手
                flipCurrentTurn();
            }
        }
        return playerThatMoved;
    }

    private boolean isValid(int row, int col) {
        if(state == GameState.FINISHED){
            return false;
        }else if(isOutOfBounds(row) || isOutOfBounds(col)) {
            return false;
        } else if(isCellValueAlreadySet(row,col)){
            return false;
        } else {
            return true;
        }
    }

    private boolean isOutOfBounds(int ids){
        return ids< 0 || ids>2;
    }

    private boolean isCellValueAlreadySet(int row,int col){
        return cells[row][col].getValue() !=null;
    }


    private boolean isWinningMoveByPlayer(Player player,int currentRow,int currentCol){
        return (
                cells[currentRow][0].getValue() == player //3行
                        &&cells[currentRow][1].getValue() == player
                        && cells[currentRow][2].getValue() == player
                        || cells[0][currentCol].getValue() == player //3列
                        && cells[1][currentCol].getValue() == player
                        && cells[2][currentCol].getValue() == player
                        || currentCol == currentRow
                        && cells[0][0].getValue() == player
                        && cells[1][1].getValue() == player
                        && cells[2][2].getValue() == player
                        || currentCol + currentRow == 2
                        && cells[0][2].getValue() == player
                        && cells[1][1].getValue() == player
                        && cells[2][0].getValue() == player

        );

    }


    private void flipCurrentTurn(){
        currentTurn = currentTurn == Player.X ? Player.O : Player.X ;
    }
}
