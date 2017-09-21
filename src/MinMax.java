import java.util.Vector;
/**
 * Class for running MinMax search.
 */
class MinMax {
  private GameState bestNextState;
  private int me;

  MinMax(GameState gameState) {
    bestNextState = gameState;
    me = gameState.getNextPlayer();
  }

  private int utilityForState(GameState state) {
    if (me == Constants.CELL_X)
      return state.isXWin() ? 1 : 0;
    else return state.isOWin() ? 1 : 0;
  }

  GameState getBestNextState() {
    maxsearch(bestNextState, Integer.MIN_VALUE, Integer.MAX_VALUE);
    return bestNextState;
  }

  private int maxsearch(GameState state, int alpha, int beta) {
    Vector<GameState> nextStates = new Vector<>();
    state.findPossibleMoves(nextStates);

    if (state.isEOG())
      return utilityForState(state);

    for (GameState nextState : nextStates) {
      int v = minsearch(nextState, alpha, beta);
      if (v > alpha) {
        alpha = v;
        bestNextState = nextState;
      }
      if (v >= beta)
        break;
    }

    return alpha;
  }

  private int minsearch(GameState state, int alpha, int beta) {
    Vector<GameState> nextStates = new Vector<>();
    state.findPossibleMoves(nextStates);

    if (state.isEOG())
      return utilityForState(state);

    for (GameState nextState : nextStates) {
      int v = maxsearch(nextState, alpha, beta);
      beta = Math.min(beta,v);
      if (v <= alpha)
        break;
    }
    return beta;
  }
}
