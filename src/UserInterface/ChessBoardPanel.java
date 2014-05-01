package UserInterface;

import javax.swing.JPanel;

public class ChessBoardPanel extends JPanel {
    private CellPanel[][] cellPanel;

    public ChessBoardPanel(int row, int column) {
        this.cellPanel=new CellPanel[row][column];
    }

    public CellPanel[][] getBoard() {
        return cellPanel;
    }
}