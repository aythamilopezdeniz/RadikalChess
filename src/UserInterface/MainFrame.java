package UserInterface;

import Aima.RadikalChessState;
import Model.ChessBoard;
import Model.ChessPiece;
import Model.Image;
import Model.Player;
import Model.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

    private final ArrayList<ChessPiece> whiteChessPieces,
            blackChessPieces, allChessPieces;
    private final Player player = new Player("White");
    private RadikalChessState radikalChessState;
//    private RadikalChessGame radikalChessGame=new RadikalChessGame(radikalChessState);
    private ChessBoardPanel boardPanel;
    private JTextField nodesExpanded;
    private CellPanel secondClicked;
    private JTextField nodesExamine;
    private CellPanel firstClicked;
    private boolean buttonPressed;
    private int column = 4;
    private int row = 6;
    private JTextField pathCost;

    public MainFrame(ArrayList<ChessPiece> whiteChessPieces,
            ArrayList<ChessPiece> blackChessPieces,
            ArrayList<ChessPiece> allChessPieces) {
        this.whiteChessPieces = whiteChessPieces;
        this.blackChessPieces = blackChessPieces;
        this.allChessPieces = allChessPieces;
        this.setTitle("RadikalChess");
        this.setVisible(true);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.createComponent();
        fillBoard();
        this.pack();
        this.setLocationRelativeTo(null);
    }

    private void createComponent() {
        this.add(createToolbar(), BorderLayout.NORTH);
        this.add(createBoardPanel(), BorderLayout.CENTER);
        this.add(createResult(), BorderLayout.SOUTH);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createDifficulty());
        panel.add(createBoardSize());
        panel.add(createAlgorithm());
        panel.add(createPlayButton());
        panel.add(createResetButton());
        panel.add(createProposeMoveButton());
        return panel;
    }

    private JComboBox createDifficulty() {
        final JComboBox difficulty = new JComboBox(new String[]{"Easy", "Medium", "Hard"});
        difficulty.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                if (difficulty.getSelectedItem().equals("Easy")) {
                    if (difficulty.getSelectedItem().equals("Medium")) {
                        if (difficulty.getSelectedItem().equals("Hard")) {
                            return;
                        }
                    }
                }
            }
        });
        return difficulty;
    }

    private JComboBox createBoardSize() {
        final JComboBox size = new JComboBox(new String[]{"Size=6x4", "Size=8x6", "Size=10x8", "Size=12x10"});
        size.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                if (size.getSelectedItem().equals("Size=6x4")) {
                    setRow(6);
                    setColumn(4);
                    createBoardPanel();
                }
                if (size.getSelectedItem().equals("Size=8x6")) {
                    setRow(8);
                    setColumn(6);
                    createBoardPanel();
                }
                if (size.getSelectedItem().equals("Size=10x8")) {
                    setRow(10);
                    setColumn(8);
                    createBoardPanel();
                }
                if (size.getSelectedItem().equals("Size=12x10")) {
                    setRow(12);
                    setColumn(10);
                    createBoardPanel();
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
        final JComboBox algorithm = new JComboBox(new String[]{"MinimaxSearch", "AlphaBetaSearch"});
        algorithm.addItemListener(new ItemListener() {

            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                if (algorithm.getSelectedItem().equals("MinimaxSearch")) {
                    if (algorithm.getSelectedItem().equals("AlphaBetaSearch")) {
                        return;
                    }
                }
            }
        });
        return algorithm;
    }

    private JButton createPlayButton() {
        JButton play = new JButton("Play");
        return play;
    }

    private JButton createResetButton() {
        JButton reset = new JButton("Reset");
        return reset;
    }

    private JButton createProposeMoveButton() {
        JButton proposeMove = new JButton("Propose Move");
        return proposeMove;
    }

    private ChessBoardPanel createBoardPanel() {
        boardPanel = new ChessBoardPanel(row, column);
        boardPanel.setLayout(new GridLayout(row, column));
        createCells();
        loadImages();
        return boardPanel;
    }

    public void createCells() {
        boolean blackFirst = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                CellPanel cell = new CellPanel(null, new Position(i, j));
                paintCell(blackFirst, j, cell);
                boardPanel.getBoard()[i][j] = cell;
                boardPanel.getBoard()[i][j].addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source instanceof CellPanel) {
//                            if (!radikalChessGame.isTerminal(radikalChessState))
                                if (buttonPressed) {
                                secondClicked = (CellPanel) e.getSource();
                                if (!firstClicked.getCell().getPosition().equals(
                                        secondClicked.getCell().getPosition())) {
                                    radikalChessState.possibleMove(firstClicked,
                                            (CellPanel) e.getSource(), boardPanel,
                                            allChessPieces, player);
                                }
                                buttonPressed = false;
                            } else if (((CellPanel)e.getSource()).getCell().getChessPiece()!=null) {
                                buttonPressed = true;
                                firstClicked = (CellPanel) e.getSource();
                            }
                        }
                    }
                });
                boardPanel.add(cell);
            }
            blackFirst = !blackFirst;
        }
        placePieces();
    }

    private void placePieces() {
        for (ChessPiece chessPiece : whiteChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()].getCell().setChessPiece(chessPiece);
        }
        for (ChessPiece chessPiece : blackChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()].getCell().setChessPiece(chessPiece);
        }
    }

    private void paintCell(boolean blackFirst, int j, CellPanel cell) {
        if (blackFirst) {
            if(j%2==0)cell.setBackground(Color.DARK_GRAY);
            else cell.setBackground(Color.WHITE);
        } else {
            if(j%2==0)cell.setBackground(Color.WHITE);
            else cell.setBackground(Color.DARK_GRAY);
        }
    }

    private void loadImages() {
        for (ChessPiece chessPiece : whiteChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()].setIcon(
                    convertImageToImageIcon(chessPiece.getImage()));
        }
        for (ChessPiece chessPiece : blackChessPieces) {
            boardPanel.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()].setIcon(
                    convertImageToImageIcon(chessPiece.getImage()));
        }
    }

    private Icon convertImageToImageIcon(Image image) {
        return new ImageIcon(((SwingBitmap) image.getBitmap()).getBufferedImage());
    }
    
    private void fillBoard() {
        ChessBoard chessBoard=new ChessBoard(row, column);
        for (int i=0;i<row;i++) {
           for (int j=0;j<column;j++) {
                chessBoard.getCell()[i][j]=boardPanel.getBoard()[i][j].getCell();
           }
        }
        radikalChessState=new RadikalChessState(chessBoard, player);
    }

    private JPanel createResult() {
        JPanel message=new JPanel();
        message.add(createNodesExpandedPanel(), FlowLayout.LEFT);
        message.add(createNodesExaminePanel(), FlowLayout.LEFT);
        message.add(createPathCostPanel(), FlowLayout.RIGHT);
        return message;
    }

    private JPanel createNodesExpandedPanel() {
        JPanel resultNodesExpanded=new JPanel();
        nodesExpanded=new JTextField(5);
        nodesExpanded.setEditable(false);
        resultNodesExpanded.setLayout(new FlowLayout(FlowLayout.LEFT));
        resultNodesExpanded.add(new JLabel("Nº de nodos expandidos:"));
        resultNodesExpanded.add(nodesExpanded);
        return resultNodesExpanded;
    }

    private JPanel createNodesExaminePanel() {
        JPanel resultNodesExamine=new JPanel();
        nodesExamine=new JTextField(5);
        nodesExamine.setEditable(false);
        resultNodesExamine.setLayout(new FlowLayout(FlowLayout.LEFT));
        resultNodesExamine.add(new JLabel("Nº de nodos visitados:"));
        resultNodesExamine.add(nodesExamine);
        return resultNodesExamine;
    }

    private JPanel createPathCostPanel() {
        JPanel resultPathCost=new JPanel();
        pathCost=new JTextField(3);
        pathCost.setEditable(false);
        resultPathCost.setLayout(new FlowLayout(FlowLayout.LEFT));
        resultPathCost.add(new JLabel("Coste del camino:"));
        resultPathCost.add(pathCost);
        return resultPathCost;
    }
}
