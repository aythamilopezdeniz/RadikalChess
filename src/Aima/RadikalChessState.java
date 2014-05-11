package Aima;

import Model.ChessBoard;
import Model.Player;
import Model.Position;

public class RadikalChessState implements Cloneable {
    private final ChessBoard chessBoard;
    private final Player player;

    public RadikalChessState(ChessBoard chessBoard, Player player) {
        this.chessBoard=chessBoard;
        this.player=player;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Player getPlayer() {
        return player;
    }

    public boolean isTerminal() {
        return false;
    }
    
    private boolean captureKing(ChessBoard chessBoard, Player player) {
        for (int i=0;i<chessBoard.getRow();i++) {
            for (int j=0;j<chessBoard.getColumn();j++) {
            }
        }
        return false;
    }
    
    private boolean isEuclideanDistanceReduce(Position origin, Position destination, Player player) {
        return new Position(destination.getRow(), destination.getColumn
        ()).euclideanDistance(chessBoard.searchPositionKing(player))<
                new Position(origin.getRow(), origin.getColumn
        ()).euclideanDistance(chessBoard.searchPositionKing(player));
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        RadikalChessState radikalChessState=new RadikalChessState(chessBoard, player);
        return radikalChessState;
    }
}