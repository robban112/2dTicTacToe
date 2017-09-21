import java.util.*;

public class Player {
    /**
     * Performs a move
     *
     * @param gameState
     *            the current state of the board
     * @param deadline
     *            time before which we must have returned
     * @return the next state the board is in after our move
     */
    public GameState play(final GameState gameState, final Deadline deadline) {
        Vector<GameState> nextStates = new Vector<GameState>();
        gameState.findPossibleMoves(nextStates);
        if (nextStates.size() == 0) {
            // Must play "pass" move if there are no other moves possible.
            return new GameState(gameState, new Move());
        }

        /**
         * Here you should write your algorithms to get the best next move, i.e.
         * the best next state. This skeleton returns a random move instead.
         */
        Random random = new Random();
        return nextStates.elementAt(random.nextInt(nextStates.size()));
    }

    GameState minimax(GameState state) {
        Vector<GameState> nextStates = new Vector<GameState>();
        state.findPossibleMoves(nextStates);

        GameState s = state;
        int max = -1;
        for (GameState nextState : nextStates) {
            int v = minsearch(nextState, Integer.MIN_VALUE, Integer.MAX_VALUE);
            if (v > max) {
                max = v;
                s = nextState;
            }
        }
        return s;
    }

    int maxsearch(GameState state, int alpha, int beta) {
        Vector<GameState> nextStates = new Vector<GameState>();
        state.findPossibleMoves(nextStates);

        if (state.isXWin())
            return 1;
        else if (state.isOWin())
            return 0;



        return 1;
    }

    int minsearch(GameState state, int alpha, int beta) {
        Vector<GameState> nextStates = new Vector<GameState>();
        state.findPossibleMoves(nextStates);

        if (state.isXWin())
            return 1;
        else if (state.isOWin())
            return 0;

        for (GameState nextState : nextStates) {
            int v = maxsearch(nextState, alpha, beta);
            if (v < beta) {
                beta = v;
            }
            if (v < alpha)
                break;
        }
        return beta;
    }

    int utility(GameState state) {

    }
}
