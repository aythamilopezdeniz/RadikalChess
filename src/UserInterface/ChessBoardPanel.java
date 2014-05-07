package UserInterface;

import Model.ChessBoard;
import Model.Movement;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {
    private final CellPanel[][] cellPanel;
    private boolean buttonPressed;
    private CellPanel firstClicked;

    public ChessBoardPanel(int row, int column) {
        this.cellPanel = new CellPanel[row][column];
    }

    public CellPanel[][] getBoard() {
        return cellPanel;
    }

    public void possibleMove(CellPanel firstClicked, CellPanel secondClicked, 
            ChessBoardPanel boardPanel) {
        if (ProposeMoveAttack.getInstance().selectMoveAttack(
                firstClicked.getCell().getChessPiece(),
                createMovement(firstClicked, secondClicked),
                createBoard(boardPanel))) {
            secondClicked.addPiece(firstClicked);
            firstClicked.removePiece();
        }
        if (ProposeMove.getInstance().selectMove(
                firstClicked.getCell().getChessPiece(),
                createMovement(firstClicked, secondClicked),
                createBoard(boardPanel))) {
            secondClicked.addPiece(firstClicked);
            firstClicked.removePiece();
        }
    }

    private Movement createMovement(CellPanel firstClicked, 
            CellPanel secondClicked) {
        return new Movement(firstClicked.getCell().getPosition(), 
                secondClicked.getCell().getPosition());
    }

    private ChessBoard createBoard(ChessBoardPanel boardPanel) {
        ChessBoard chessBoard = new ChessBoard(cellPanel.length, cellPanel[0].length);
        for (int i = 0; i < boardPanel.getBoard().length; i++) {
            for (int j = 0; j < boardPanel.getBoard()[0].length; j++) {
                chessBoard.getCell()[i][j] = boardPanel.getBoard()[i][j].getCell();
            }
        }
        return chessBoard;
    }
}
