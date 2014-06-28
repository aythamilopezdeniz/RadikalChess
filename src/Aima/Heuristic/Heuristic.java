package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.ChessPiece;
import Model.Movement;
import Model.PieceMoveRange;
import Model.Player;
import java.util.ArrayList;

public abstract class Heuristic {
    public abstract double getHeuristic(RadikalChessState state, Player player);
    
    public double threatenedAdversarialPieces(RadikalChessState radikalChessState, Player player){
        boolean whiteKingAlive = false, blackKingAlive = false;
        for (int i=0;i<radikalChessState.getChessBoard().getRow();i++) {
            for (int j=0;j<radikalChessState.getChessBoard().getColumn();j++) {
                if(radikalChessState.getChessBoard().getCell()[i][j].getChessPiece()!=null) {
                    if(radikalChessState.getChessBoard().getCell()[i][j].getChessPiece().
                            getName().equals("King")&&radikalChessState.getChessBoard().
                                    getCell()[i][j].getChessPiece().getColour().equals("White"))
                        whiteKingAlive = true;
                    if (radikalChessState.getChessBoard().getCell()[i][j].getChessPiece().
                            getName().equals("King")&&radikalChessState.getChessBoard().
                                    getCell()[i][j].getChessPiece().getColour().equals("Black"))
                        blackKingAlive = true;
                }
            }
        }
        if (whiteKingAlive && !blackKingAlive) {
            if (player.getPlayer().equals("White"))return 10000;
            else if (player.getPlayer().equals("Black"))return -10000;
        }
        if (!whiteKingAlive && blackKingAlive) {
            if (player.getPlayer().equals("Black"))return 10000;
            else if (player.getPlayer().equals("White"))return -10000;
        }
        return 0;
    }
    
    public double numberAttackOfPieces(RadikalChessState radikalChessState, 
            Player player, ChessPiece chessPiece) {
        double threatValue=0;
        ArrayList<Movement>action=new ArrayList<>();
        for (int i=0;i<radikalChessState.getChessBoard().getRow();i++) {
            for (int j=0;j<radikalChessState.getChessBoard().getColumn();j++) {
                if(radikalChessState.getChessBoard().getCell()[i][j].getChessPiece()!=null){
                    if(radikalChessState.getChessBoard().getCell()[i][j].getChessPiece().getColour().equals(
                            radikalChessState.getPlayer().getPlayer())){
                        action.addAll(PieceMoveRange.getInstance().selectMove(
                                radikalChessState.getChessBoard().getCell()[i][j].getChessPiece(), radikalChessState));
                        for (Movement movement : action) {
                            if(radikalChessState.destinationCell(movement).getChessPiece()!=null){
                                if(radikalChessState.destinationCell(movement).getChessPiece().getName().equals("King"))
                                    return Double.POSITIVE_INFINITY;
                                threatValue+=1;
                            }
                        }
                    }else{
                        RadikalChessState radikalChessStateClone=(RadikalChessState) radikalChessState.clone();
                        radikalChessStateClone.setPlayer(radikalChessStateClone.getPlayer().getPlayer().equals(
                                "White")?new Player("Black"):new Player("White"));
                        action.addAll(PieceMoveRange.getInstance().selectMove(radikalChessState.
                                getChessBoard().getCell()[i][j].getChessPiece(), radikalChessStateClone));
                        for (Movement movement : action) {
                            if(radikalChessState.destinationCell(movement).getChessPiece()!=null){
                                if(radikalChessState.destinationCell(movement).getChessPiece().getName().equals("King"))
                                    return Double.POSITIVE_INFINITY;
                                threatValue-=1;
                            }
                        }
                    }
                }
                action.clear();
            }
        }
        return threatValue;
    }
}