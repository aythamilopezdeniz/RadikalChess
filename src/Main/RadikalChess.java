package Main;

import Model.ChessPiece;
import Persistence.ChessPieceLoader;
import UserInterface.MainFrame;

public class RadikalChess {
    public static final String filename="";
    private MainFrame frame;

    public static void main(String[] args) {
        RadikalChess radikalChess=new RadikalChess();
        radikalChess.execute();
    }

    private void execute() {
        
        ChessPiece[] whiteChessPiece=new ChessPieceLoader(filename).load("White");
        ChessPiece[] blackChessPiece=new ChessPieceLoader(filename).load("Black");
        frame=new MainFrame(whiteChessPiece, blackChessPiece);
    }
}