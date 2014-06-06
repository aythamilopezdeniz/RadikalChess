package UserInterface;

import Aima.RadikalChessState;
import Control.RadikalChessControl;
import Model.ChessBoard;
import Model.ChessPiece;
import Model.Image;
import Model.Movement;
import Model.Pieces.Pawn;
import Model.Pieces.Queen;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {
    private final CellPanel[][] cellPanel;

    public ChessBoardPanel(int row, int column) {
        this.cellPanel = new CellPanel[row][column];
    }

    public CellPanel[][] getBoard() {
        return cellPanel;
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

    public void updateChessPiece(Movement movement) {
        destinationCellButton(movement).addPiece(originCellButton(movement));
        originCellButton(movement).removePiece();
    }
    
    public void checkPromotionedPawn(Movement movement, ArrayList<ChessPiece> allPieces, 
            RadikalChessState state) throws IOException {
        if (destinationCellButton(movement).getCell().getChessPiece() instanceof Pawn&&
                (destinationCellButton(movement).getCell().getChessPiece().getPosition().getRow()==0||
                destinationCellButton(movement).getCell().getChessPiece().getPosition().getRow()==
                cellPanel.length - 1)&&destinationCellButton(movement).getCell().getChessPiece() 
                instanceof Pawn) {
            for (ChessPiece chessPiece : allPieces) {
                if (chessPiece.getName().equals(destinationCellButton(
                        movement).getCell().getChessPiece().getName())&&chessPiece.getColour().equals(
                                destinationCellButton(movement).getCell().getChessPiece().getColour())&&
                        chessPiece.getPosition().equals(destinationCellButton(
                                movement).getCell().getChessPiece().getPosition())) {
                    chessPiece=new Queen("Queen", chessPiece.getPosition(), chessPiece.getColour());
                    chessPiece.setImage(new Image(new SwingBitmap(ImageIO.read(new File(
                            RadikalChessControl.filename+"/"+chessPiece.getColour()+"Queen"+".png")))));
                    destinationCellButton(movement).getCell().setChessPiece(chessPiece);
                    state.getChessBoard().getCell()[movement.getDestination().getRow()]
                            [movement.getDestination().getColumn()].setChessPiece(chessPiece);
                    destinationCellButton(movement).setIcon(new ImageIcon(((SwingBitmap)destinationCellButton(
                            movement).getCell().getChessPiece().getImage().getBitmap()).getBufferedImage()));
                    break;
                }
            }
        }
    }
    
    private CellPanel originCellButton(Movement movement){
        return cellPanel[movement.getOrigin().getRow()]
                [movement.getOrigin().getColumn()];
    }
    
    private CellPanel destinationCellButton(Movement movement){
        return cellPanel[movement.getDestination().getRow()]
                [movement.getDestination().getColumn()];
    }
}