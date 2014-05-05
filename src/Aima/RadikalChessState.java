package Aima;

import Model.ChessBoard;

public class RadikalChessState implements Cloneable {
    private final ChessBoard chessBoard;

    public RadikalChessState(ChessBoard chessBoard) {
        this.chessBoard=chessBoard;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public boolean isTerminal() {
        return false;
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        RadikalChessState radikalChessState=new RadikalChessState(chessBoard);
        return radikalChessState;
    }
}