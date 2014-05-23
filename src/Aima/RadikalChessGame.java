package Aima;

import Aima.Heuristic.HeuristicAttack;
import Model.Movement;
import Model.Pieces.King;
import Model.Player;
import java.util.ArrayList;
import java.util.List;

public class RadikalChessGame implements Game <RadikalChessState, Movement, Player>{
    private RadikalChessState initialState;
    private RadikalChessState actualState;

    public RadikalChessGame(RadikalChessState radikalChessState) {
        try {
            this.initialState=(RadikalChessState) radikalChessState.clone();
            this.actualState=(RadikalChessState) radikalChessState.clone();
        } catch (CloneNotSupportedException ex) {
        }
    }

    public RadikalChessState getActualState() {
        return actualState;
    }

    @Override
    public RadikalChessState getInitialState() {
        return initialState;
    }

    public void setActualState(RadikalChessState actualState) {
        this.actualState = actualState;
    }

    @Override
    public List<Movement> getActions(RadikalChessState state) {
        ArrayList<Movement> actions=new ArrayList<>();
        return actions;
    }

    @Override
    public RadikalChessState getResult(RadikalChessState state, Movement movement) {
        RadikalChessState radikalChessState = null;
        try {
            radikalChessState = (RadikalChessState) state.clone();
        } catch (CloneNotSupportedException ex) {
        }
        return radikalChessState;
    }

    @Override
    public boolean isTerminal(RadikalChessState state) {
        int numberKing=0;
        for (int i=0;i<state.getChessBoard().getRow();i++) {
            for (int j=0;j<state.getChessBoard().getColumn();j++) {
                if(state.getChessBoard().getCell()[i][j].getChessPiece() instanceof King)
                    numberKing++;
            }
        }
        return numberKing<2;
    }

   @Override
    public Player[] getPlayers() {
        return null;
    }

    @Override
    public Player getPlayer(RadikalChessState state) {
        return state.getPlayer();
    }

    @Override
    public double getUtility(RadikalChessState state) {
        HeuristicAttack heuristicAttack=new HeuristicAttack();
        return heuristicAttack.getHeuristic(state);
    }
}