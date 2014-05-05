package UserInterface;

import Model.ChessBoard;
import Model.ChessPiece;
import Model.Movement;
import Model.Position;
import Model.ProposeMove;
import Model.ProposeMoveAttack;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
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

    public void createCells(ArrayList<ChessPiece> whiteChessPiece,
            ArrayList<ChessPiece> blackChessPiece) {
        boolean blackFirst = true;
        for (int i = 0; i < cellPanel.length; i++) {
            for (int j = 0; j < cellPanel[0].length; j++) {
                CellPanel cell = new CellPanel(null, new Position(i, j));
                paintCell(blackFirst, j, cell);
                this.getBoard()[i][j] = cell;
                this.getBoard()[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source instanceof CellPanel) {
                            if (buttonPressed) {
                                possibleMove(firstClicked, 
                                        (CellPanel) e.getSource(), this);
                                buttonPressed = false;
                            } else {
                                buttonPressed = true;
                                firstClicked = (CellPanel) e.getSource();
                            }
                        }
                    }
                });
                this.add(cell);
            }
            blackFirst = !blackFirst;
        }
        placePieces(whiteChessPiece, blackChessPiece);
    }

    private void placePieces(ArrayList<ChessPiece> whiteChessPiece,
            ArrayList<ChessPiece> blackChessPiece) {
        for (ChessPiece chessPiece : whiteChessPiece) {
            this.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()].getCell().setChessPiece(chessPiece);
        }
        for (ChessPiece chessPiece : blackChessPiece) {
            this.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()].getCell().setChessPiece(chessPiece);
        }
    }

    private void paintCell(boolean blackFirst, int j, CellPanel cell) {
        if (blackFirst) {
            if (j % 2 == 0) {
                cell.setBackground(Color.DARK_GRAY);
            } else {
                cell.setBackground(Color.WHITE);
            }
        } else {
            if (j % 2 == 0) {
                cell.setBackground(Color.WHITE);
            } else {
                cell.setBackground(Color.DARK_GRAY);
            }
        }
    }
}
