package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Player;

public class MediumHeuristic extends Heuristic {

    @Override
    public double getHeuristic(RadikalChessState state, Player player) {
        int heuristic=0;
        for (int i=0;i<state.getChessBoard().getRow();i++) {
            for (int j=0;j<state.getChessBoard().getColumn();j++) {
                if(state.getChessBoard().getCell()[i][j].getChessPiece()!=null&&
                        state.getPlayer().getPlayer().equals(
                        state.getChessBoard().getCell()[i][j].getChessPiece().getColour())){
                    heuristic+=state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                    try {
                        heuristic+=threatenedAdversarialPieces(state, player, state.
                                getChessBoard().getCell()[i][j].getChessPiece());
                    } catch (CloneNotSupportedException ex) {
                    }
                }else if(state.getChessBoard().getCell()[i][j].getChessPiece()!=null&&
                        !state.getPlayer().getPlayer().equals(
                        state.getChessBoard().getCell()[i][j].getChessPiece().getColour())){
                    heuristic-=state.getChessBoard().getCell()[i][j].getChessPiece().getValue();
                    try {
                        heuristic+=threatenedAdversarialPieces(state, player, state.
                                    getChessBoard().getCell()[i][j].getChessPiece());
                    } catch (CloneNotSupportedException ex) {
                    }
                }
            }
        }
        return heuristic;
    }
}