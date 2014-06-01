package UserInterface;

import Model.Cell;
import Model.ChessPiece;
import Model.Image;
import Model.Position;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

public class CellPanel extends JButton {
    private Cell cell;

    public CellPanel(ChessPiece chessPiece, Position position) {
        this.cell=new Cell(chessPiece, position);
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public void removePiece(){
        this.cell.setChessPiece(null);
        this.setIcon(null);
    }
    
    public void addPiece(CellPanel firstClicked) {
        this.cell.setChessPiece(firstClicked.getCell().getChessPiece());
        this.setIcon(convertImageToIcon(firstClicked.getCell().getChessPiece().getImage()));
    }
    
    private Icon convertImageToIcon(Image image){
        return new ImageIcon(((SwingBitmap)image.getBitmap()).getBufferedImage());    
    }
}