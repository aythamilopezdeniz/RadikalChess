package UserInterface;

import Model.ChessPiece;
import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final ArrayList<ChessPiece> whiteChessPieces;
    private final ArrayList<ChessPiece> blackChessPieces;
    private int row = 6;
    private int column = 4;

    public MainFrame(ArrayList<ChessPiece> whiteChessPieces, 
            ArrayList<ChessPiece> blackChessPieces) {
        this.whiteChessPieces=whiteChessPieces;
        this.blackChessPieces=blackChessPieces;
        this.setTitle("RadikalChess");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.createComponent();
        this.pack();
    }

    private void createComponent() {
        this.add(createToolbar(), BorderLayout.NORTH);
        this.add(createBoardPanel(), BorderLayout.SOUTH);
    }

    private JPanel createToolbar() {
        JPanel panel=new JPanel();
        panel.add(createDifficulty());
        panel.add(createBoardSize());
        panel.add(createAlgorithm());
        panel.add(createPlayButton());
        panel.add(createResetButton());
        panel.add(createProposeMoveButton());
        return panel;
    }

    private JComboBox createDifficulty() {
        JComboBox difficulty=new JComboBox(new String[]{"Easy", "Medium", "Hard"});
        difficulty.setSelectedIndex(0);
        return difficulty;
    }

    private JComboBox createBoardSize() {
        final JComboBox size=new JComboBox(new String[]{"Size=6x4", "Size=8x6", "Size=10x8", "Size=12x10"});
        size.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                switch(size.getSelectedIndex()){
                    case 0:
                        setRow(6);
                        setColumn(4);
                        break;
                    case 1:
                        setRow(8);
                        setColumn(6);
                        break;
                    case 2:
                        setRow(10);
                        setColumn(8);
                        break;
                    case 3:
                        setRow(12);
                        setColumn(10);
                        break;
                    default:
                        break;
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {
            }

            @Override
            public void mouseExited(MouseEvent e) {
            }
        });
        size.setSelectedIndex(0);
        return size;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getColumn() {
        return column;
    }

    public void setColumn(int column) {
        this.column = column;
    }

    private JComboBox createAlgorithm() {
        JComboBox algorithm=new JComboBox(new String[]{"Minimax", "AlphaBeta"});
        algorithm.setSelectedIndex(0);
        return algorithm;
    }

    private JButton createPlayButton() {
        JButton play=new JButton("Play");
        return play;
    }

    private JButton createResetButton() {
        JButton reset=new JButton("Reset");
        return reset;
    }

    private JButton createProposeMoveButton() {
        JButton proposeMove=new JButton("Propose Move");
        return proposeMove;
    }

    private JPanel createBoardPanel() {
        JPanel boardPanel=new JPanel();
        return boardPanel;
    }
}