package Aima;

import Model.Cell;
import Model.ChessBoard;
import Model.Movement;
import Model.Pieces.Pawn;
import Model.Player;
import Model.Position;
import Model.ProposeMove;
import Model.ProposeMoveAttack;

public class RadikalChessState implements Cloneable {

    private ChessBoard chessBoard;
    private Player player;
    private int utility=0;

    public RadikalChessState(ChessBoard chessBoard, Player player) {
        this.chessBoard = chessBoard;
        this.player = player;
    }

    public ChessBoard getChessBoard() {
        return chessBoard;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public int getUtility() {
        return utility;
    }

    public boolean possibleMove(Movement movement) {
        if (originCell(movement).getChessPiece().getColour().equals(player.getPlayer())) {
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    originCell(movement).getChessPiece(), movement, chessBoard)) {
                originCell(movement).getChessPiece().setPosition(movement.getDestination());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                return true;
            }
            if (ProposeMove.getInstance().selectMove(
                    originCell(movement).getChessPiece(), movement, chessBoard)) {
                if(!isEuclideanDistanceReduce(movement.getOrigin(), movement.getDestination())&&
                        !(originCell(movement).getChessPiece() instanceof Pawn))
                    return false;
                originCell(movement).getChessPiece().setPosition(movement.getDestination());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                return true;
            }
        }
        return false;
    }

    public void mark(Movement movement) {
        originCell(movement).getChessPiece().setPosition(movement.getDestination());
        destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
        originCell(movement).setChessPiece(null);
        player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
    }

    public Cell originCell(Movement movement) {
        return chessBoard.getCell()[movement.getOrigin().getRow()]
                [movement.getOrigin().getColumn()];
    }

    public Cell destinationCell(Movement movement) {
        return chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()];
    }
    
    public boolean isEuclideanDistanceReduce(Position origin, Position destination){
        return (destination.euclideanDistance(chessBoard, player)<
                origin.euclideanDistance(chessBoard, player));
    }

    @Override
    public Object clone() {
        RadikalChessState radikalChessState = null;
        try {
            radikalChessState = (RadikalChessState) super.clone();
            radikalChessState.chessBoard=(ChessBoard) chessBoard.clone();
            radikalChessState.player=(Player) player.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return radikalChessState;
    }
}
