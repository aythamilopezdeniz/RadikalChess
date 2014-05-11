package UserInterface;

import Model.ChessBoard;
import Model.ChessPiece;
import Model.Movement;
import Model.Player;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import java.util.ArrayList;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {
    private final CellPanel[][] cellPanel;

    public ChessBoardPanel(int row, int column) {
        this.cellPanel = new CellPanel[row][column];
    }

    public CellPanel[][] getBoard() {
        return cellPanel;
    }

    public void possibleMove(CellPanel firstClicked, CellPanel secondClicked, 
            ChessBoardPanel boardPanel, ArrayList allChessPiece, Player player) {
        if(firstClicked.getCell().getChessPiece().getColour().equals(player.getPlayer())){
            if (ProposeMoveAttack.getInstance().selectMoveAttack(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    createBoard(boardPanel))) {
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                ChessPiece piece=secondClicked.getCell().getChessPiece();
                updateChessPieceIcon(firstClicked, secondClicked, allChessPiece);
                allChessPiece.remove(piece);
            }
            if (ProposeMove.getInstance().selectMove(
                    firstClicked.getCell().getChessPiece(),
                    createMovement(firstClicked, secondClicked),
                    createBoard(boardPanel))&&
                    ProposeMove.getInstance().isEuclideanDistanceReduced(allChessPiece
                            , createMovement(firstClicked, secondClicked),
                            firstClicked.getCell().getChessPiece())) {
                player.setPlayer((player.getPlayer().equals("White"))?"Black":"White");
                updateChessPieceIcon(firstClicked, secondClicked, allChessPiece);
            }
        }
    }
    
    private void updateChessPieceIcon(CellPanel firstClicked, CellPanel secondClicked,
            ArrayList<ChessPiece>allPieces){
        for (ChessPiece chessPiece : allPieces) {
            if (chessPiece.getName().equals(firstClicked.getCell().getChessPiece().getName())&&
                    chessPiece.getColour().equals(firstClicked.getCell().getChessPiece().getColour()))
                chessPiece.setPosition(secondClicked.getCell().getPosition());
        }
        secondClicked.addPiece(firstClicked);
        firstClicked.removePiece();
    }
    
    private Movement createMovement(CellPanel firstClicked, 
            CellPanel secondClicked) {
        return new Movement(firstClicked.getCell().getPosition(), 
                secondClicked.getCell().getPosition());
    }

    private ChessBoard createBoard(ChessBoardPanel boardPanel) {
        ChessBoard chessBoard=new ChessBoard(cellPanel.length, cellPanel[0].length);
        for (int i=0;i<boardPanel.getBoard().length; i++) {
            for (int j=0;j<boardPanel.getBoard()[0].length;j++) {
                chessBoard.getCell()[i][j]=boardPanel.getBoard()[i][j].getCell();
            }
        }
        return chessBoard;
    }
}
