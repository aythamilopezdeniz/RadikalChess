package Aima.Search;

import Aima.Game;
import Aima.Metrics;
import Aima.RadikalChessState;
import Model.Movement;

public class MinimaxSearch<STATE, ACTION, PLAYER> implements
		AdversarialSearch<STATE, ACTION> {
    private Game<STATE, ACTION, PLAYER> game;
    private static int totalExpandedNodes;
    private static int totalTime=0;
    private static int maxDepth;
    private int expandedNodes;
    private static int turn=1;
    private double time;
    
    public static <STATE, ACTION, PLAYER> MinimaxSearch<STATE, ACTION, PLAYER> createFor(
            Game<STATE, ACTION, PLAYER> game, int difficulty) {
        maxDepth=difficulty;
        return new MinimaxSearch<>(game);
    }
    
    public MinimaxSearch(Game<STATE, ACTION, PLAYER> game) {
        this.game=game;
    }
    
    @Override
    public ACTION makeDecision(STATE state) {
        expandedNodes=0;
        int currectDepth=0;
        ACTION result=null;
        double resultValue=Double.NEGATIVE_INFINITY;
        PLAYER player=game.getPlayer(state);
        System.out.println(turn+"º Turn");
        double t1=System.currentTimeMillis();
        for (ACTION action:game.getActions(state)) {
            double value=minValue(game.getResult(state, action),currectDepth, player);
            if (value>resultValue) {
                result=action;
                resultValue=value;
            }
            System.out.println(((RadikalChessState)state).originCell(
                    (Movement)action).getChessPiece().toString()+" "+value);
        }
        time=System.currentTimeMillis()-t1;
        totalTime+=time;
        System.out.println("Tiempo "+totalTime);
        turn++;
        totalExpandedNodes += expandedNodes;
        System.out.println("Nodos totales expandidos "+totalExpandedNodes);
        return result;
    }
    
    public double maxValue(STATE state, int currentDepth, PLAYER player) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(state)||currentDepth>4)
            return game.getUtility(state, player);
        double value=Double.NEGATIVE_INFINITY;
        for (ACTION action:game.getActions(state))
            value=Math.max(value,
                    minValue(game.getResult(state, action), currentDepth, player));
        return value;
    }
    
    public double minValue(STATE state, int currentDepth, PLAYER player) {
        expandedNodes++;
        currentDepth++;
        if (game.isTerminal(state)||currentDepth>4)
            return game.getUtility(state, player);
        double value=Double.POSITIVE_INFINITY;
        for (ACTION action:game.getActions(state))
            value=Math.min(value,
                    maxValue(game.getResult(state, action), currentDepth, player));
        return value;
    }

        @Override
	public Metrics getMetrics() {
		Metrics result = new Metrics();
		result.set("expandedNodes", expandedNodes);
		return result;
	}
	
        @Override
	public int getTotalExpandedNodes(){
	    return totalExpandedNodes;
	}

    @Override
    public double getTime() {
        return time;
    }

    @Override
    public int getExpandedNodes() {
        return expandedNodes;
    }
}