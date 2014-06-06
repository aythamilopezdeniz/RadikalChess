package Aima;

import Model.Cell;
import Model.ChessBoard;
import Model.Movement;
import Model.Player;
import Model.Position;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import UserInterface.CellPanel;

public class RadikalChessState implements Cloneable {

    private final ChessBoard chessBoard;
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
                if (!isEuclideanDistanceReduce(chessBoard, movement, player)) {
                    return false;
                }
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

    private Cell originCell(Movement movement) {
        return chessBoard.getCell()[movement.getOrigin().getRow()]
                [movement.getOrigin().getColumn()];
    }

    private Cell destinationCell(Movement movement) {
        return chessBoard.getCell()[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()];
    }

    private Movement createMovement(CellPanel firstClicked,
            CellPanel secondClicked) {
        return new Movement(firstClicked.getCell().getPosition(),
                secondClicked.getCell().getPosition());
    }

    public boolean isEuclideanDistanceReduce(ChessBoard chessBoard,
            Movement movement, Player player) {
        return new Position(movement.getDestination().getRow(),
                movement.getDestination().getColumn()).euclideanDistance(
                        chessBoard.searchPositionKing(player))
                < new Position(movement.getOrigin().getRow(),
                        movement.getOrigin().getColumn()).euclideanDistance(
                                chessBoard.searchPositionKing(player));
    }
    
    public boolean isManhattanDistanceReduce(ChessBoard chessBoard, 
            Movement movement, Player player){
        return new Position(movement.getDestination().getRow(),
                movement.getDestination().getColumn()).manhattanDistance(
                chessBoard.searchPositionKing(player))<
                new Position(movement.getOrigin().getRow(), 
                movement.getOrigin().getColumn()).manhattanDistance(
                chessBoard.searchPositionKing(player));
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        RadikalChessState radikalChessState=new RadikalChessState(
                (ChessBoard)chessBoard.clone(), player);
        return radikalChessState;
    }
}
