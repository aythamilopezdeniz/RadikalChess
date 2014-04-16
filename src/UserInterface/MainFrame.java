package UserInterface;

import Model.ChessPiece;
import java.awt.BorderLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class MainFrame extends JFrame {
    private final ChessPiece[] whiteChessPieces;
    private final ChessPiece[] blackChessPieces;

    public MainFrame(ChessPiece[] whiteChessPieces, ChessPiece[] blackChessPieces) {
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
        JComboBox size=new JComboBox(new String[]{"Size=6x4", "Size=8x6", "Size=10x8", "Size=12x10"});
        size.setSelectedIndex(0);
        return size;
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