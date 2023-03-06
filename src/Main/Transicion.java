package Main;

/**
 *
 * @author WINDOWS 10 PRO
 */
public class Transicion {

    public String initialState;
    public String transition;
    public String finalState;

    public Transicion(String initialState, String transition, String finalState) {
        this.initialState = initialState;
        this.transition = transition;
        this.finalState = finalState;
    }

    public Transicion(int initialState, String transition, int finalState) {
        this.initialState = "S" + initialState;
        this.transition = transition;
        this.finalState = "S" + finalState;
    }

    public void setStates(int initialState, int finalState) {
        this.initialState = "S" + initialState;
        this.finalState = "S" + finalState;
    }

    public void setFinalState(int finalState) {
        this.finalState = "S" + finalState;
    }

    public int getInitialState() {
        return Integer.parseInt(initialState.substring(1));
    }

    public int getFinalState() {
        return Integer.parseInt(finalState.substring(1));
    }

    public boolean compare(int initialState, String transition) {
        return this.initialState.equals(initialState) && this.transition.equals(transition);
    }

    @Override
    public String toString() {
        return String.valueOf(this.initialState) + " —" + this.transition + "→ " + String.valueOf(this.finalState);
    }
}
