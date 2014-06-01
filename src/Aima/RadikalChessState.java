package Aima;

import Model.Cell;
import Model.ChessBoard;
import Model.ChessPiece;
import Model.Movement;
import Model.Player;
import Model.Position;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import UserInterface.CellPanel;
import java.util.ArrayList;

public class RadikalChessState implements Cloneable {
    private final ChessBoard chessBoard;
    private Player player;

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

    public void setPlayer(Player player) {
        this.player = player;
    }
    
    public boolean possibleMove(Movement movement, ArrayList<ChessPiece> allChessPieces){
        if(originCell(movement).getChessPiece().getColour().equals(player.getPlayer())){
            if(ProposeMoveAttack.getInstance().selectMoveAttack(
                    originCell(movement).getChessPiece(), movement, chessBoard)){
                updateChessPiece(allChessPieces, movement);
                allChessPieces.remove(destinationCell(movement).getChessPiece());
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                return true;
            }
            if(ProposeMove.getInstance().selectMove(
                    originCell(movement).getChessPiece(), movement, chessBoard)){
                if(!isEuclideanDistanceReduce(chessBoard, movement, player))
                    return false;
                updateChessPiece(allChessPieces, movement);
                destinationCell(movement).setChessPiece(originCell(movement).getChessPiece());
                originCell(movement).setChessPiece(null);
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                return true;
            }
        }
        return false;
    }

/*    public void possibleMove(CellPanel firstClicked, CellPanel secondClicked, 
            ChessBoardPanel boardPanel, ArrayList allChessPiece, Player player) {
        if(firstClicked.getCell().getChessPiece().getColour().equals(player.getPlayer())){
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    chessBoard)) {
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                ChessPiece piece=secondClicked.getCell().getChessPiece();
                boardPanel.updateChessPieceIcon(firstClicked, secondClicked, allChessPiece);
                allChessPiece.remove(piece);
            }
            if (ProposeMove.getInstance().selectMove(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    chessBoard)&&
                    isEuclideanDistanceReduce(chessBoard,
                            createMovement(firstClicked, secondClicked), player)) {
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                boardPanel.updateChessPieceIcon(firstClicked, secondClicked, allChessPiece);
            }
        }
    }*/


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
                movement.getDestination().getColumn
        ()).euclideanDistance(chessBoard.searchPositionKing(player))<
                new Position(movement.getOrigin().getRow(), 
                        movement.getOrigin().getColumn
        ()).euclideanDistance(chessBoard.searchPositionKing(player));
    }
    
    private void updateChessPiece(ArrayList<ChessPiece>allChessPieces, Movement movement){
        for (ChessPiece chessPiece : allChessPieces) {
            if(chessPiece.getName().equals(originCell(movement).getChessPiece().getName())&&
                    chessPiece.getColour().equals(originCell(movement).getChessPiece().getColour())&&
                    chessPiece.getPosition().equals(originCell(movement).getChessPiece().getPosition()))
                chessPiece.setPosition(destinationCell(movement).getPosition());
        }
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        RadikalChessState radikalChessState=new RadikalChessState((ChessBoard) chessBoard.clone(), player);
        return radikalChessState;
    }
}