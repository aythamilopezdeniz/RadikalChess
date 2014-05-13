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
    
    public void updateChessPieceIcon(CellPanel firstClicked, CellPanel secondClicked,
            ArrayList<ChessPiece>allPieces){
        for (ChessPiece chessPiece : allPieces) {
            if (chessPiece.getName().equals(firstClicked.getCell().getChessPiece().getName())&&
                    chessPiece.getColour().equals(firstClicked.getCell().getChessPiece().getColour()))
                chessPiece.setPosition(secondClicked.getCell().getPosition());
        }
        secondClicked.addPiece(firstClicked);
        firstClicked.removePiece();
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
