package UserInterface;

import Aima.RadikalChessGame;
import Aima.RadikalChessState;
import Aima.Search.AdversarialSearch;
import Aima.Search.AlphaBetaSearch;
import Aima.Search.MinimaxSearch;
import Model.Cell;
import Model.ChessBoard;
import Model.ChessPiece;
import Model.Image;
import Model.Movement;
import Model.Player;
import Model.Position;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class MainFrame extends JFrame {

    private final ArrayList<ChessPiece> whiteChessPieces,
            blackChessPieces, allChessPieces;
    private final Player player = new Player("White");
    private RadikalChessState radikalChessState;
    private RadikalChessGame radikalChessGame=new RadikalChessGame();
    private JTextField nodesExpanded, time, pathCost;
    private ChessBoardPanel chessBoardPanel;
    private int numberOfMovementsSearch=0;
    private final int offsetColumn=0;
    private JTextField nodesExamine;
    private int numberOfMovements=0;
    private CellPanel firstClicked;
    private JTextArea actionsPanel;
    private boolean buttonPressed;
    private final int offsetRow=0;
    private JComboBox difficulty;
    private JComboBox algorithm;
    //private JTextArea movements;
    private int column=4;
    private int level=0;
    private int row=6;

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
        createSplitPane();
        fillBoard();
        this.setMinimumSize(new Dimension(700,700));
        this.pack();
        this.setLocationRelativeTo(null);
    }
    
    private void createSplitPane() {
        JSplitPane split=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,
                createBoardPanel(), createScrollPane());
        split.setResizeWeight(1);
        split.setDividerLocation(350);
        split.setEnabled(false);
        this.getContentPane().add(split);
    }
    
    private JScrollPane createScrollPane() {
        JScrollPane scroll=new JScrollPane(createActionsPanel());
        scroll.setBounds(30, 30, 200, 200);
        scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        return scroll;
    }
    
    private JTextArea createActionsPanel() {
        actionsPanel=new JTextArea("Historial of moves [Row, Column]:\n");
        actionsPanel.setLineWrap(true);
        actionsPanel.setWrapStyleWord(true);
        actionsPanel.setEditable(false);
        actionsPanel.setMinimumSize(new Dimension(300,300));
        return actionsPanel;
    }


    private void createComponent() {
        this.add(createToolbar(), BorderLayout.NORTH);
        this.add(createResult(), BorderLayout.SOUTH);
    }

    private JPanel createToolbar() {
        JPanel panel = new JPanel();
        panel.add(createDifficulty());
        panel.add(createAlgorithm());
        panel.add(createProposeMoveButton());
        return panel;
    }

    private JComboBox createDifficulty() {
        difficulty = new JComboBox(new String[]{"Easy", "Medium", "Hard"});
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
        algorithm = new JComboBox(new String[]{"MinimaxSearch", "AlphaBetaSearch"});
        algorithm.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                if (e.getStateChange() != ItemEvent.SELECTED) {
                    return;
                }
                if (algorithm.getSelectedItem().equals("MinimaxSearch")) {
                }
                if (algorithm.getSelectedItem().equals("AlphaBetaSearch")) {
                }
            }
        });
        return algorithm;
    }

    private JButton createProposeMoveButton() {
        JButton proposeMove = new JButton("Propose Move");
        proposeMove.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (!radikalChessGame.isTerminal(radikalChessState)) {
                    algorithm.setEnabled(false);
                    difficulty.setEnabled(false);
                    AdversarialSearch<RadikalChessState, Movement> search;
                    Movement action;
                    if (difficulty.getSelectedIndex() == 0)
                        level = 3;
                    else if (difficulty.getSelectedIndex() == 1)
                        level = 4;
                    else
                        level = 5;
                    if (algorithm.getSelectedIndex() == 0) {
                        search = MinimaxSearch.createFor(radikalChessGame, level);
                    } else {
                        search = AlphaBetaSearch.createFor(radikalChessGame, level);
                    }
                    Player actualPlayer = new Player(radikalChessState.getPlayer().getPlayer());
                    action = search.makeDecision(radikalChessState);
                    radikalChessState.setPlayer(actualPlayer);
                    radikalChessState.mark(action);
                    numberOfMovementsSearch++;
                    numberOfMovements++;
                    chessBoardPanel.updateChessPiece(newMovement(action.getOrigin(), action.getDestination()));
                    updateStatistics(search);
                    updateMovement(action);
                    try {
                        chessBoardPanel.checkPromotionedPawn(newMovement(action.getOrigin(), 
                                action.getDestination()), allChessPieces, radikalChessState);
                    } catch (IOException ex) {
                    }
                }
                System.out.println();
            }
        });
        return proposeMove;
    }
    
        private void updateStatistics(AdversarialSearch<RadikalChessState, Movement> search) {
        updateNodesExpanded(search);
        updateTime(search);
        updatePathCost();
    }

    private void updateNodesExpanded(AdversarialSearch<RadikalChessState, Movement> search) {
        nodesExpanded.setText(String.valueOf(search.getExpandedNodes()));
    }

    private void updateTime(AdversarialSearch<RadikalChessState, Movement> search) {
        time.setText(String.valueOf(search.getTime() + " s"));
    }

    private void updatePathCost() {
        pathCost.setText(String.valueOf(numberOfMovementsSearch));
    }

    private void updateMovement(Movement movement) {
        actionsPanel.setText(actionsPanel.getText() + numberOfMovements + ". "
                + radikalChessState.getChessBoard().getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getColour() + " "
                + radikalChessState.getChessBoard().getCell()[movement.getDestination().getRow()]
                        [movement.getDestination().getColumn()].getChessPiece().getName() + " from " + "["
                + movement.getOrigin().getRow() + "," + movement.getOrigin().getColumn()
                + "] to " + "[" + movement.getDestination().getRow() + ","
                + movement.getDestination().getColumn() + "]\n");
    }

    private ChessBoardPanel createBoardPanel() {
        chessBoardPanel = new ChessBoardPanel(row, column);
        chessBoardPanel.setLayout(new GridLayout(row, column));
        createCells();
        loadImages();
        return chessBoardPanel;
    }

    public void createCells() {
        boolean blackFirst = true;
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                CellPanel cell = new CellPanel(null, new Position(i, j));
                paintCell(blackFirst, j, cell);
                chessBoardPanel.getBoard()[i][j] = cell;
                chessBoardPanel.getBoard()[i][j].addActionListener(new ActionListener() {
                    private CellPanel secondClicked;

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        Object source = e.getSource();
                        if (source instanceof CellPanel) {
                            if (!radikalChessGame.isTerminal(radikalChessState)){
                                if (buttonPressed) {
                                    secondClicked = (CellPanel) e.getSource();
                                    if (!firstClicked.getCell().getPosition().equals(
                                            secondClicked.getCell().getPosition())) {
                                        if (radikalChessState.possibleMove(
                                                newMovement(firstClicked.getCell().getPosition(),
                                                        secondClicked.getCell().getPosition()))) {
                                                chessBoardPanel.updateChessPiece(newMovement(
                                                        firstClicked.getCell().getPosition(),
                                                        secondClicked.getCell().getPosition()));
                                                numberOfMovements++;
                                                updateMovement(newMovement(firstClicked.getCell().getPosition(), 
                                                        secondClicked.getCell().getPosition()));
                                        }
                                        try {
                                            chessBoardPanel.checkPromotionedPawn(newMovement(
                                                    firstClicked.getCell().getPosition(),
                                                    secondClicked.getCell().getPosition()),
                                                    allChessPieces, radikalChessState);
                                        } catch (IOException ex) {
                                        }
                                    }
                                    buttonPressed = false;
                                } else if (((CellPanel) e.getSource()).getCell().getChessPiece() != null) {
                                    buttonPressed = true;
                                    firstClicked = (CellPanel) e.getSource();
                                }
                            }
                        }
                    }
                });
                chessBoardPanel.add(cell);
            }
            blackFirst = !blackFirst;
        }
        placePieces();
    }

    private Movement newMovement(Position origin, Position destination) {
        return new Movement(origin, destination);
    }

    private void placePieces() {
        for (ChessPiece chessPiece : whiteChessPieces) {
            chessBoardPanel.getBoard()[chessPiece.getPosition().getRow()+offsetRow]
                    [chessPiece.getPosition().getColumn()+offsetColumn].getCell().setChessPiece(chessPiece);
        }
        for (ChessPiece chessPiece : blackChessPieces) {
            chessBoardPanel.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()+offsetColumn].getCell().setChessPiece(chessPiece);
        }
    }

    private void paintCell(boolean blackFirst, int j, CellPanel cell) {
        if(blackFirst) {
            if(j%2==0)cell.setBackground(Color.DARK_GRAY);
            else cell.setBackground(Color.WHITE);
        } else {
            if(j%2==0)cell.setBackground(Color.WHITE);
            else cell.setBackground(Color.DARK_GRAY);
        }
    }

    private void loadImages() {
        for (ChessPiece chessPiece : whiteChessPieces) {
            chessBoardPanel.getBoard()[chessPiece.getPosition().getRow()+offsetRow]
                    [chessPiece.getPosition().getColumn()+offsetColumn].setIcon(
                    convertImageToImageIcon(chessPiece.getImage()));
        }
        for (ChessPiece chessPiece : blackChessPieces) {
            chessBoardPanel.getBoard()[chessPiece.getPosition().getRow()]
                    [chessPiece.getPosition().getColumn()+offsetColumn].setIcon(
                    convertImageToImageIcon(chessPiece.getImage()));
        }
    }

    private Icon convertImageToImageIcon(Image image) {
        return new ImageIcon(((SwingBitmap) image.getBitmap()).getBufferedImage());
    }

    private void fillBoard() {
        ChessBoard chessBoard = new ChessBoard(row, column);
        for (int i = 0; i < row; i++) {
            for (int j = 0; j < column; j++) {
                chessBoard.getCell()[i][j]=new Cell(
                        chessBoardPanel.getBoard()[i][j].getCell().getChessPiece(), new Position(i, j));
            }
        }
        radikalChessState = new RadikalChessState(chessBoard, player);
    }

    private JPanel createResult() {
        JPanel message = new JPanel();
        message.add(createNodesExpandedPanel(), FlowLayout.LEFT);
        message.add(createNodesExaminePanel(), FlowLayout.LEFT);
        message.add(createPathCostPanel(), FlowLayout.RIGHT);
        return message;
    }

    private JPanel createNodesExpandedPanel() {
        JPanel resultNodesExpanded = new JPanel();
        nodesExpanded = new JTextField(5);
        nodesExpanded.setEditable(false);
        resultNodesExpanded.setLayout(new FlowLayout(FlowLayout.LEFT));
        resultNodesExpanded.add(new JLabel("Number of expanded nodes:"));
        resultNodesExpanded.add(nodesExpanded);
        return resultNodesExpanded;
    }

    private JPanel createNodesExaminePanel() {
        JPanel resultNodesExamine = new JPanel();
        time = new JTextField(5);
        time.setEditable(false);
        resultNodesExamine.setLayout(new FlowLayout(FlowLayout.LEFT));
        resultNodesExamine.add(new JLabel("Time:"));
        resultNodesExamine.add(time);
        return resultNodesExamine;
    }

    private JPanel createPathCostPanel() {
        JPanel resultPathCost = new JPanel();
        pathCost = new JTextField(3);
        pathCost.setEditable(false);
        resultPathCost.setLayout(new FlowLayout(FlowLayout.LEFT));
        resultPathCost.add(new JLabel("Number of movements:"));
        resultPathCost.add(pathCost);
        return resultPathCost;
    }
}
