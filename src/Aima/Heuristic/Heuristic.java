package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Player;

public interface Heuristic {
    public int getHeuristic(RadikalChessState state, Player player);
}