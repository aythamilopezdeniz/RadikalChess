package UserInterface;

import Model.Cell;
import Model.ChessPiece;
import Model.Image;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CellPanel extends JButton {
    private Cell cell;
    private final ArrayList<ChessPiece> whiteChessPiece;
    private final ArrayList<ChessPiece> blackChessPiece;

    public CellPanel(Cell cell, ArrayList<ChessPiece> whiteChessPiece,
            ArrayList<ChessPiece> blackChessPiece) {
        this.cell=cell;
        this.whiteChessPiece=whiteChessPiece;
        this.blackChessPiece=whiteChessPiece;
    }
    private void loadImages(ChessBoardPanel boardPanel) {
        for (ChessPiece chessPiece : whiteChessPiece) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                                 [chessPiece.getPosition().getColumn()].setIcon(
                                 convertImageToImageIcon(chessPiece.getImage()));
        }
        for (ChessPiece chessPiece : blackChessPiece) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                                 [chessPiece.getPosition().getColumn()].setIcon(
                                 convertImageToImageIcon(chessPiece.getImage()));
        }
    }
    
    private Icon convertImageToImageIcon(Image image){
        return new ImageIcon(((SwingBitmap) image.getBitmap()).getBufferedImage());
    }
}