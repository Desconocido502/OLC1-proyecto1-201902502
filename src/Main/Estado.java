package Main;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author WINDOWS 10 PRO 
 *test class*
 */
public class Estado {

    public String name;
    public boolean is_final_state;
    public Map<String, Estado> next_states = new HashMap<>();

    public Estado(String name, boolean is_final_state) {
        this.name = name;
        this.is_final_state = is_final_state;
    }

    public Estado() {
        this.name = "0";
        this.is_final_state = false;
    }

    public void setData(String name, boolean final_state) {
        this.name = name;
        this.is_final_state = final_state;
    }

    public String getName() {
        return name;
    }

    public void Next(String lexeme, Estado state) {
        next_states.put(lexeme, state);
    }

    public Estado Transition(String lexeme) {
        for (Map.Entry<String, Estado> entry : next_states.entrySet()) {
            if (entry.getKey().equals(lexeme)) {
                return entry.getValue();
            }
        }

        return null;
    }

    public Estado TransitionByName(String name) {

        if (next_states.isEmpty()) {
            return this;
        }

        for (Map.Entry<String, Estado> entry : next_states.entrySet()) {
            if (entry.getValue().name == name) {
                return entry.getValue();
            }
        }

        return null;
    }

}
