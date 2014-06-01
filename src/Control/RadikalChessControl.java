package Control;

import Model.ChessPiece;
import Persistence.ChessPieceLoader;
import UserInterface.MainFrame;
import java.util.ArrayList;

public class RadikalChessControl {
    public static final String filename="Piezas Ajedrez";

    public void execute() {
        ArrayList<ChessPiece> whiteChessPiece=new ChessPieceLoader(filename).load("White");
        ArrayList<ChessPiece> blackChessPiece=new ChessPieceLoader(filename).load("Black");
        ArrayList<ChessPiece> allChessPieces = new ArrayList<>();
        for (ChessPiece chessPiece : whiteChessPiece) {
            allChessPieces.add(chessPiece);
        }
        for (ChessPiece chessPiece : blackChessPiece) {
            allChessPieces.add(chessPiece);
        }
        new MainFrame(whiteChessPiece, blackChessPiece, allChessPieces);
    }
}