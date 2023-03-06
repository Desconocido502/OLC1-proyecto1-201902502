package Main;

import static Main.NodeType.*;
import java.util.ArrayList;

/**
 *
 * @author WINDOWS 10 PRO
 */
public class AFND {

    private Estado initial_state;
    private ArrayList<Transicion> transiciones;

    private final String epsilon = "ε";

    public AFND(Nodo tree) {
        transiciones = Buscar(tree);
        //System.out.println("--------Codigo-------------");
        //System.out.println(tree.getCodigoInterno());
        //System.out.println("--------Fin-Codigo---------");
        transiciones.isEmpty();
    }

    private int num = 0;

    private int getNum() {
        num += 1;
        return num - 1;
    }

    private void setNum(int num) {
        this.num = num;
    }

    private ArrayList<Transicion> Buscar(Nodo node) {
        ArrayList<Transicion> t1 = new ArrayList<>(), t2 = new ArrayList<>(), t3 = new ArrayList<>();

        if (node.hizq != null) {
            t1 = Buscar(node.hizq);
            //System.out.println("t1>"+t1.get(0)+"<");
        }

        if (node.hder != null) {
            t2 = Buscar(node.hder);
            //System.out.println("t2>"+t2.get(0)+"<");
        }

        switch (node.type) {
            case CONCATENATION:
                int max = 0;
                for (Transicion t : t1) {
                    if (t.getFinalState() > max) {
                        max = t.getFinalState();
                    }
                }
                for (Transicion t : t2) {
                    t.setStates(t.getInitialState() + max, t.getFinalState() + max);
                }

                t3.addAll(t1);
                t3.addAll(t2);
                return t3;
            case DISYUNCTION:
                int min1 = 1000,
                 min2 = 1000,
                 max1 = 0,
                 max2 = 0;

                for (Transicion t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }
                for (Transicion t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }

                t3.add(new Transicion(min1 - 1, epsilon, min1));

                for (Transicion t : t2) {
                    t.setStates(t.getInitialState() + max1 + 1, t.getFinalState() + max1 + 1);
                }
                for (Transicion t : t2) {
                    if (t.getInitialState() < min2) {
                        min2 = t.getInitialState();
                    }
                    if (t.getFinalState() > max2) {
                        max2 = t.getFinalState();
                    }
                }

                t3.add(new Transicion(min1 - 1, epsilon, min2));
                t3.add(new Transicion(max1, epsilon, max2 + 1));
                t3.add(new Transicion(max2, epsilon, max2 + 1));

                t3.addAll(t1);
                t3.addAll(t2);

                return t3;
            case KLEENE_LOCK:
                min1 = 1000;
                max1 = 0;

                for (Transicion t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }

                t3.add(new Transicion(min1, epsilon, min1 + 1));

                for (Transicion t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }

                t3.add(new Transicion(max1 + 1, epsilon, max1 + 2));
                t3.add(new Transicion(min1, epsilon, max1 + 2));
                t3.add(new Transicion(max1 + 1, epsilon, min1 + 1));

                t3.addAll(t1);
                t3.addAll(t2);

                return t3;

            case POSITIVE_LOCK:
                min1 = 1000;
                max1 = 0;

                for (Transicion t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }
                //System.out.println("min1:" + String.valueOf(min1) + ", max1:" + String.valueOf(max1));
                t3.add(new Transicion(min1, epsilon, min1 + 1));

                for (Transicion t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }

                t3.add(new Transicion(max1 + 1, epsilon, max1 + 2));
                t3.add(new Transicion(max1 + 1, epsilon, min1 + 1));

                t3.addAll(t1);
                t3.addAll(t2);
                //System.out.println("t3->" + t3.get(0));
                return t3;

            case BOOLEAN_LOCK:
                min1 = 1000;
                max1 = 0;

                for (Transicion t : t1) {
                    if (t.getInitialState() < min1) {
                        min1 = t.getInitialState();
                    }
                    if (t.getFinalState() > max1) {
                        max1 = t.getFinalState();
                    }
                }

                t3.add(new Transicion(min1, epsilon, min1 + 1));

                for (Transicion t : t1) {
                    t.setStates(t.getInitialState() + 1, t.getFinalState() + 1);
                }

                t3.add(new Transicion(max1 + 1, epsilon, max1 + 2));
                t3.add(new Transicion(min1, epsilon, max1 + 2));

                t3.addAll(t1);
                t3.addAll(t2);

                return t3;

            default:
                Transicion tran = new Transicion(0, node.valor, 1);
                //System.out.println("-" + tran + "-");
                t3.add(tran);
                return t3;

        }
    }

    public Estado getInitialState() {
        return initial_state;
    }

    public ArrayList<Transicion> getTransitions() {
        return transiciones;
    }

    public String getLast() {
        int max = 0;
        for (Transicion t : transiciones) {
            if (t.getFinalState() > max) {
                max = t.getFinalState();
            }
        }

        return "S" + max;
    }
}
