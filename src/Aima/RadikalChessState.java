package Aima;

import Model.ChessBoard;
import Model.Position;

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
    
    private boolean isEuclideanDistanceReduce(Position origin, Position destination){
        return new Position(destination.getRow(), destination.getColumn()).euclideanDistance(origin)<
                new Position(origin.getRow(), origin.getColumn()).euclideanDistance(destination);
    }
}