package Aima.Heuristic;

import Aima.RadikalChessState;
import Model.Player;
import java.util.Random;

public class RandomHeuristic extends Heuristic {

    @Override
    public double getHeuristic(RadikalChessState state, Player player) {
        Random random=new Random(System.currentTimeMillis());
        return random.nextInt();
    }
}