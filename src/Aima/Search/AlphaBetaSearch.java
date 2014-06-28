package Aima.Search;

import Aima.Game;
import Aima.Metrics;


public class AlphaBetaSearch<STATE, ACTION, PLAYER> implements
        AdversarialSearch<STATE, ACTION> {

    Game<STATE, ACTION, PLAYER> game;
    private int expandedNodes;
    private static int totalExpandedNodes, maxDepth;
    private double time;
    private static int turn=1;
    private static int totalTime=0;

    public static <STATE, ACTION, PLAYER> AlphaBetaSearch<STATE, ACTION, PLAYER> createFor(
            Game<STATE, ACTION, PLAYER> game, int difficulty) {
        maxDepth=difficulty;
        return new AlphaBetaSearch<STATE, ACTION, PLAYER>(game);
    }

    public AlphaBetaSearch(Game<STATE, ACTION, PLAYER> game) {
        this.game = game;
    }

    @Override
    public ACTION makeDecision(STATE state) {
        expandedNodes = 0;
        int currentDepth=0;
        ACTION result = null;
        double resultValue = Double.NEGATIVE_INFINITY;
        PLAYER player = game.getPlayer(state);
        System.out.println(turn+"º Turn");
        double t1=System.currentTimeMillis();
        for (ACTION action : game.getActions(state)) {
            double value = minValue(game.getResult(state, action), player,
                    currentDepth, Double.NEGATIVE_INFINITY, Double.POSITIVE_INFINITY);
            if (value > resultValue) {
                result = action;
                resultValue = value;
            }
        }
        time=System.currentTimeMillis()-t1;
        totalTime+=time;
        System.out.println("Tiempo "+totalTime);
        turn++;
        totalExpandedNodes += expandedNodes;
        System.out.println("Nodos totales expandidos "+totalExpandedNodes);
        return result;
    }

    public double maxValue(STATE state, PLAYER player, int currentDepth, double alpha, double beta) {
        expandedNodes++;
        if (game.isTerminal(state)||currentDepth>maxDepth) {
            return game.getUtility(state, player);
        }
        double value = Double.NEGATIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.max(value, minValue( //
                    game.getResult(state, action), player, currentDepth, alpha, beta));
            if (value >= beta) {
                return value;
            }
            alpha = Math.max(alpha, value);
        }
        return value;
    }

    public double minValue(STATE state, PLAYER player, int currentDepth, double alpha, double beta) {
        expandedNodes++;
        if (game.isTerminal(state)||currentDepth>maxDepth) {
            return game.getUtility(state, player);
        }
        double value = Double.POSITIVE_INFINITY;
        for (ACTION action : game.getActions(state)) {
            value = Math.min(value, maxValue( //
                    game.getResult(state, action), player, currentDepth, alpha, beta));
            if (value <= alpha) {
                return value;
            }
            beta = Math.min(beta, value);
        }
        return value;
    }

    @Override
    public Metrics getMetrics() {
        Metrics result = new Metrics();
        result.set("expandedNodes", expandedNodes);
        return result;
    }

    @Override
    public int getTotalExpandedNodes() {
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
