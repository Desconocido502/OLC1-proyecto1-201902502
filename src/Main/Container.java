package Main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author EDUARDO
 */
public class Container {

    String id[], notacion[];
    String aceptaciones, nota, auxS;
    Hashtable container = new Hashtable();
    Hashtable next = new Hashtable();
    Hashtable nextNodes = new Hashtable();
    Hashtable newStates = new Hashtable();
    Hashtable nextStates = new Hashtable();
    public static ArrayList<String> lista_de_conjuntos = new ArrayList<String>();

    List<String> ListaE = new ArrayList<String>();

    public Container() {

    }

    public void addNext(String key, String value) {
        String tmp;
        if (next.get(key) != null) {
            tmp = next.get(key).toString();
            tmp = tmp + value;
            next.replace(key, tmp);
        } else {
            next.put(key, value);
        }
    }

    public void addNextN(String key, String value) {
        String tmp;
        if (nextNodes.get(key) != null) {
            tmp = nextNodes.get(key).toString();
            tmp = tmp + value;
            nextNodes.replace(key, value);
        } else {
            nextNodes.put(key, "\"" + value + "\"");
        }
        auxS = key + "---" + nextNodes.get(key).toString();
        //System.out.println(auxS);
    }

    String S = "S";
    String[] cadena;
    public static int n = 1;

    public void Transition(String Eo, String Ea) {
        try {
            String test = Eo.replace(",", "");
            String leaf, aux, leaf2;

            newStates.put(Eo, "S0");
            leaf = nextNodes.get(test).toString();
            aux = next.get(test).toString();
            Pattern pattern2 = Pattern.compile(".*" + Ea);
            Matcher matcher2 = pattern2.matcher(aux);

            if (matcher2.matches()) {
                cadena = aux.split(",");
                ListaE.add("S0" + "->" + "S1" + "[ label =" + leaf + "]");
                if (newStates.get(aux) == null) {
                    newStates.put(aux, S + n);
                    for (String cadena1 : cadena) {
                        if (!Ea.equals(cadena1)) {
                            leaf2 = nextNodes.get(cadena1).toString();
                            ListaE.add("S1" + "->" + "S1" + "[ label =" + leaf2 + "]");
                            lista_de_conjuntos.add(leaf2);
                            aceptaciones = "S1";
                        }
                    }
                }
            } else {
                ListaE.add("S0" + "->" + "S1" + "[ label =" + leaf + "]");
                lista_de_conjuntos.add(leaf);
                newStates.put(next.get(test), S + (n));
                Transition2(next.get(test).toString(), Ea); // Si el siguiente del estado inicial no tiene estado de aceptacion se nmanda el siguiente al metodo
            }
        } catch (Exception e) {
        }
    }

    public void Transition2(String Eo, String Ea) {
        try {
            String tmp = Eo;
            String[] test = tmp.split(",");
            String leaf, nexts, aux;
            Pattern pattern2 = Pattern.compile(".*" + Ea);
            Matcher matcher2 = pattern2.matcher(Eo);
            if (matcher2.matches()) {
                aceptaciones = S + n;
            }
            for (String test1 : test) {
                //Recorremos el nuevo estado
                nexts = next.get(test1).toString();
                leaf = nextNodes.get(test1).toString();
                if (newStates.get(nexts) != null) {
                    ListaE.add(S + n + "->" + S + n + "[ label =" + leaf + "]");
                    lista_de_conjuntos.add(leaf);
                } else {
                    if (!test1.equals(Ea)) {
                        ListaE.add(S + n + "->" + S + (n + 1) + "[ label =" + leaf + "]");
                        n++; //Si el siguiente no existe entonces es un nuevo estado
                        newStates.put(next.get(test1), S + (n));
                        aux = next.get(test1).toString();
                        Transition3(aux, Ea);
                    }
                }
            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<String> getListaDeConjuntos() {
        return lista_de_conjuntos;
    }

    public void Transition3(String Eo, String Ea) {
        try {
            String tmp = Eo;
            String[] test = tmp.split(",");
            String leaf, nexts, aux;

            Pattern pattern2 = Pattern.compile(".*" + Ea);
            Matcher matcher2 = pattern2.matcher(Eo);

            if (matcher2.matches()) {
                aceptaciones = S + n;
            }

            for (String test1 : test) {
                //Se recorre el nuevo estado
                nexts = next.get(test1).toString(); //Se obtienen los siguientes de cada siguiente en el estado
                leaf = nextNodes.get(test1).toString();
                if (newStates.get(nexts) != null) {
                    //Si el siguiente ya pertenece a un estado solo se añade recursivamente a la hoja
                    ListaE.add(S + n + "->" + S + n + "[ label =" + leaf + "]");
                } else {
                    if (!test1.equals(Ea)) {
                        ListaE.add(S + n + "->" + S + (n + 1) + "[ label =" + leaf + "]");
                        n++; //Si el siguiente no existe, entonces es un nuevo estado
                        newStates.put(next.get(test1), S + (n + 1));
                        aux = next.get(test1).toString();
                        Transition2(aux, Ea);
                    }
                }
            }

        } catch (Exception e) {
        }
    }

    public void printTest() {
        Enumeration enumeration = newStates.elements();
        Enumeration keys = newStates.keys();
        Enumeration enumeration2 = nextStates.elements();
        Enumeration keys2 = nextStates.keys();

        while (enumeration.hasMoreElements()) {
            System.out.println("" + "hashtable valores: " + enumeration.nextElement());
        }

        while (keys.hasMoreElements()) {
            System.out.println("" + "hashtable llaves: " + keys.nextElement());
        }

        while (enumeration2.hasMoreElements()) {
            System.out.println("" + "hashtable valores N: " + enumeration2.nextElement());
        }

        while (keys2.hasMoreElements()) {
            System.out.println("" + "hashtable llaves N: " + keys2.nextElement());
        }

        for (int i = 0; i < ListaE.size(); i++) {
            System.out.println(ListaE.get(i));
        }
    }

    public void PrintS() {
        Enumeration enumeration = next.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println("" + "hashtable valores2: " + enumeration.nextElement());
        }
        Enumeration keys = next.keys();
        while (keys.hasMoreElements()) {
            System.out.println("" + "hashtable llaves2 : " + keys.nextElement());
        }
    }

    public void clear() {
        next.clear();
        nextNodes.clear();
        newStates.clear();
        nextStates.clear();
        ListaE.clear();
    }

    public void graphNext(String name) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/SIGUIENTES_201902502/" + name + ".dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph G{");
            pw.println("tbl [");
            pw.println("shape=plaintext");
            pw.println("label=<");
            pw.println("<table color='orange' cellspacing='0'>");
            pw.println("<tr><td>Hojas</td><td>Nodos</td><td>Siguientes</td></tr>");
            Enumeration enumeration = next.elements();
            Enumeration enumeration2 = nextNodes.elements();
            Enumeration llaves = next.keys();
            while (llaves.hasMoreElements()) {
                pw.println("<tr><td>" + enumeration2.nextElement() + "</td><td>" + llaves.nextElement() + "</td><td>" + enumeration.nextElement() + "</td></tr>");
            }
            pw.println("</table>");
            pw.println(">];");
            pw.println("}");
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo" + e);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //para compilar el archivo dot y obtener la imagen
        try {
            //dirección doonde se ecnuentra el compilador de graphviz
            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            //dirección del archivo dot
            String fileInputPath = "src/SIGUIENTES_201902502/" + name + ".dot";
            //dirección donde se creara la magen
            String fileOutputPath = "src/SIGUIENTES_201902502/" + name + ".jpg";
            //tipo de conversón
            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    public void graphTransitions(String name) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/TRANSICIONES_201902502/" + name + ".dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph G{");
            pw.println("tbl [");
            pw.println("shape=plaintext");
            pw.println("label=<");
            pw.println("<table color='orange' cellspacing='0'>");
            pw.println("<tr><td>Estados</td><td>Conjuntos</td></tr>");
            Enumeration enumeration = newStates.elements();

            Enumeration llaves = newStates.keys();
            while (llaves.hasMoreElements()) {
                pw.println("<tr><td>" + enumeration.nextElement() + "</td><td>" + llaves.nextElement() + "</td></tr>");
            }
            pw.println("</table>");
            pw.println(">];");
            pw.println("tb2 [");
            pw.println("shape=plaintext");
            pw.println("label=<");
            pw.println(" <table color='pink' border='0' cellborder='1' cellpadding='10' cellspacing='0'>");
            pw.println("<tr><td>Transiciones</td></tr>");
            for (int i = 0; i <= ListaE.size() - 1; i++) {
                pw.println("<tr><td>" + ListaE.get(i).replace('>', ' ').replace("[", "").replace("]", "").replace("label", "-").replace("=", "") + "</td></tr>");
            }
            pw.println("</table>");

            pw.println(">];");
            pw.println("}");
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo" + e);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //para compilar el archivo dot y obtener la imagen
        try {
            //dirección doonde se ecnuentra el compilador de graphviz
            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            //dirección del archivo dot
            String fileInputPath = "src/TRANSICIONES_201902502/" + name + ".dot";
            //dirección donde se creara la magen
            String fileOutputPath = "src/TRANSICIONES_201902502/" + name + ".jpg";
            //tipo de conversón
            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }

    public void graphAutomatas(String name) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/AFD_201902502/" + name + ".dot");
            pw = new PrintWriter(fichero);
            pw.println("digraph finite_state_machine {");
            pw.println("rankdir=LR;");
            pw.println("size=\"8,5\"");
            pw.println("node [shape = doublecircle];" + aceptaciones + ";");
            pw.println("node [shape = circle];");
            for (int i = 0; i <= ListaE.size() - 1; i++) {
                pw.println(ListaE.get(i) + ";");
            }
            pw.println("}");
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo" + e);
        } finally {
            try {
                if (null != fichero) {
                    fichero.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        //para compilar el archivo dot y obtener la imagen
        try {
            //dirección doonde se ecnuentra el compilador de graphviz
            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            //dirección del archivo dot
            String fileInputPath = "src/AFD_201902502/" + name + ".dot";
            //dirección donde se creara la magen
            String fileOutputPath = "src/AFD_201902502/" + name + ".jpg";
            //tipo de conversón
            String tParam = "-Tjpg";
            String tOParam = "-o";

            String[] cmd = new String[5];
            cmd[0] = dotPath;
            cmd[1] = tParam;
            cmd[2] = fileInputPath;
            cmd[3] = tOParam;
            cmd[4] = fileOutputPath;

            Runtime rt = Runtime.getRuntime();

            rt.exec(cmd);

        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
        }
    }
}
