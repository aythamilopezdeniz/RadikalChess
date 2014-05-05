package UserInterface;

import Model.ChessPiece;
import java.awt.BorderLayout;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
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
        final JComboBox difficulty=new JComboBox(new String[]{"Easy", "Medium", "Hard"});
        difficulty.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()!=ItemEvent.SELECTED)return;
                if(difficulty.getSelectedItem().equals("Easy"))
                if(difficulty.getSelectedItem().equals("Medium"))
                if(difficulty.getSelectedItem().equals("Hard"))return;
            }
        });
        return difficulty;
    }

    private JComboBox createBoardSize() {
        final JComboBox size=new JComboBox(new String[]{"Size=6x4", "Size=8x6", "Size=10x8", "Size=12x10"});
        size.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()!=ItemEvent.SELECTED){
                    return;
                }
                if(size.getSelectedItem().equals("Size=6x4")){
                    setRow(6);
                    setColumn(4);
                }
                if(size.getSelectedItem().equals("Size=8x6")){
                    setRow(8);
                    setColumn(6);
                }
                if(size.getSelectedItem().equals("Size=10x8")){
                    setRow(10);
                    setColumn(8);
                }
                if(size.getSelectedItem().equals("Size=12x10")){
                    setRow(12);
                    setColumn(10);
                }
            }
        });
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
        final JComboBox algorithm=new JComboBox(new String[]{"MinimaxSearch", "AlphaBetaSearch"});
        algorithm.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if(e.getStateChange()!=ItemEvent.SELECTED)return;
                if(algorithm.getSelectedItem().equals("MinimaxSearch"))
                if(algorithm.getSelectedItem().equals("AlphaBetaSearch"))return;
            }
        });
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

    private ChessBoardPanel createBoardPanel() {
        ChessBoardPanel boardPanel=new ChessBoardPanel(row, column);
        boardPanel.createCells(whiteChessPieces, blackChessPieces);
        boardPanel.loadImages();
        return boardPanel;
    }
}