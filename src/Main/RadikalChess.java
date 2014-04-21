package Main;

import Model.ChessPiece;
import Persistence.ChessPieceLoader;
import UserInterface.MainFrame;
import java.util.ArrayList;

public class RadikalChess {
    public static final String filename="C:\\Users\\ENTRAR\\Desktop\\Fichas";
    private MainFrame frame;

    public static void main(String[] args) {
        RadikalChess radikalChess=new RadikalChess();
        radikalChess.execute();
    }

    private void execute() {
        
        ArrayList<ChessPiece> whiteChessPiece=new ChessPieceLoader(filename).load("White");
        ArrayList<ChessPiece> blackChessPiece=new ChessPieceLoader(filename).load("Black");
        frame=new MainFrame(whiteChessPiece, blackChessPiece);
    }
}