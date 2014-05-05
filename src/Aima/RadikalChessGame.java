package Aima;

import Model.Movement;
import java.util.List;

public class RadikalChessGame implements Game <RadikalChessState, Movement, String>{
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

    @Override
    public String[] getPlayers() {
        return null;
    }

    @Override
    public String getPlayer(RadikalChessState state) {
        return null;
    }

    @Override
    public List<Movement> getActions(RadikalChessState state) {
        return null;
    }

    @Override
    public RadikalChessState getResult(RadikalChessState state, Movement movement) {
        return null;
    }

    @Override
    public boolean isTerminal(RadikalChessState state) {
        return state.isTerminal();
    }

    @Override
    public double getUtility(RadikalChessState state, String player) {
        return 0;
    }
}