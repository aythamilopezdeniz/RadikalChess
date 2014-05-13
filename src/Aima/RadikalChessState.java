package Aima;

import Model.ChessBoard;
import Model.ChessPiece;
import Model.Movement;
import Model.Player;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import UserInterface.CellPanel;
import UserInterface.ChessBoardPanel;
import java.util.ArrayList;

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
    
    private boolean captureKing(ChessBoard chessBoard, Player player) {
        for (int i=0;i<chessBoard.getRow();i++) {
            for (int j=0;j<chessBoard.getColumn();j++) {
            }
        }
        return false;
    }

    public void possibleMove(CellPanel firstClicked, CellPanel secondClicked, 
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
                    /*ProposeMove.getInstance().isEuclideanDistanceReduced(allChessPiece
                            , createMovement(firstClicked, secondClicked),
                            firstClicked.getCell().getChessPiece())*/
                    ProposeMove.getInstance().isEuclideanDistanceReduce(chessBoard,
                            createMovement(firstClicked, secondClicked), player)) {
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                boardPanel.updateChessPieceIcon(firstClicked, secondClicked, allChessPiece);
            }
        }
    }
    
    private Movement createMovement(CellPanel firstClicked, 
            CellPanel secondClicked) {
        return new Movement(firstClicked.getCell().getPosition(), 
                secondClicked.getCell().getPosition());
    }
    
    @Override
    protected Object clone() throws CloneNotSupportedException {
        RadikalChessState radikalChessState=new RadikalChessState(chessBoard, player);
        return radikalChessState;
    }
}