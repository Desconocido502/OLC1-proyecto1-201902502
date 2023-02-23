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

    String id[];
    String aceptaciones;
    String nota;
    String notacion[];
    String auxS;
    Hashtable contenedor = new Hashtable();
    Hashtable siguiente = new Hashtable();
    Hashtable siguienteNodos = new Hashtable();
    Hashtable EstadosNuevos = new Hashtable();
    Hashtable EstadosSiguiente = new Hashtable();
    public static ArrayList<String> listaConjuntos = new ArrayList<String>();

    List<String> ejemploLista = new ArrayList<String>();

    public Container() {

    }

    public void añadirSiguiente(String clave, String valor) {
        String aux;
        if (siguiente.get(clave) != null) {
            aux = siguiente.get(clave).toString();
            aux = aux + valor;
            siguiente.replace(clave, aux);
        } else {
            siguiente.put(clave, valor);
        }

        // auxS=clave+"---"+siguiente.get(clave).toString();
        //System.out.println(auxS);
    }

    public void añadirSiguienteN(String clave, String valor) {
        String aux;
        if (siguienteNodos.get(clave) != null) {
            aux = siguienteNodos.get(clave).toString();
            aux = aux + valor;
            siguienteNodos.replace(clave, aux);
        } else {
            siguienteNodos.put(clave, "\"" + valor + "\"");
        }

        auxS = clave + "---" + siguienteNodos.get(clave).toString();
        // System.out.println(auxS);
    }
    String S = "S";
    String[] cadena;
    public static int n = 1;

    public void Transicion(String Eo, String aceptacion) {
        try {

            String prueba = Eo.replace(",", "");
            String hoja;

            String aux;
            String hoja2;

            EstadosNuevos.put(Eo, "S0");

            hoja = siguienteNodos.get(prueba).toString();

            aux = siguiente.get(prueba).toString();
            Pattern pat2 = Pattern.compile(".*" + aceptacion);
            Matcher mat2 = pat2.matcher(aux);

            if (mat2.matches()) {

                cadena = aux.split(",");
                ejemploLista.add("S0" + "->" + "S1" + "[ label =" + hoja + "]");
                if (EstadosNuevos.get(aux) == null) {
                    EstadosNuevos.put(aux, S + n);

                    for (int j = 0; j < cadena.length; j++) {
                        if (!aceptacion.equals(cadena[j])) {
                            hoja2 = siguienteNodos.get(cadena[j]).toString();

                            ejemploLista.add("S1" + "->" + "S1" + "[ label =" + hoja2 + "]");
                            listaConjuntos.add(hoja2);

                            aceptaciones = "S1";
                        }

                    }
                }
            } else {
                ejemploLista.add("S0" + "->" + "S1" + "[ label =" + hoja + "]");
                listaConjuntos.add(hoja);
                EstadosNuevos.put(siguiente.get(prueba), S + (n));
                Transicion2(siguiente.get(prueba).toString(), aceptacion); //si el siguiente del estado inicial no tiene estado de aceptacion se manda el seiguiente al metodo

            }

            // ImprimirTprueba();
        } catch (Exception e) {
        }
    }

    public void Transicion2(String nuevo, String aceptacion) {
        try {

            String temp = nuevo;
            //System.out.println(nuevo+"ceptacion, "+ aceptacion);
            String[] prueba = temp.split(",");
            String hoja;
            String siguientes;
            String aux;
            String siguientes2;
            Pattern pat2 = Pattern.compile(".*" + aceptacion);
            Matcher mat2 = pat2.matcher(nuevo);
            if (mat2.matches()) {
                aceptaciones = S + n;
            }
            for (int i = 0; i < prueba.length; i++) {   //recorremos el nuevo estado

                siguientes = siguiente.get(prueba[i]).toString();  //obtenemos los siguientes de cada siguiente del estado
                //System.out.println(siguientes);
                hoja = siguienteNodos.get(prueba[i]).toString();
                if (EstadosNuevos.get(siguientes) != null) {  //si el siguiente ya pertenece a un estado solo se añade recursivamente la hoja
                    ejemploLista.add(S + n + "->" + S + n + "[ label =" + hoja + "]");
                    listaConjuntos.add(hoja);
                } else {
                    if (!prueba[i].equals(aceptacion)) {

                        ejemploLista.add(S + n + "->" + S + (n + 1) + "[ label =" + hoja + "]");
                        n++; //si el siguiente no existe entonces es un nuevo estado
                        EstadosNuevos.put(siguiente.get(prueba[i]), S + (n));
                        aux = siguiente.get(prueba[i]).toString();
                        //System.out.println(aux);
                        Transicion3(aux, aceptacion);

                    }

                }

            }
        } catch (Exception e) {
        }
    }

    public static ArrayList<String> getListaConjuntos() {
        return listaConjuntos;
    }

    public void Transicion3(String nuevo, String aceptacion) {
        try {

            String temp = nuevo;
            // System.out.println(nuevo+"ceptacion, "+ aceptacion);
            String[] prueba = temp.split(",");
            String hoja;
            String siguientes;
            String aux;

            Pattern pat2 = Pattern.compile(".*" + aceptacion);
            Matcher mat2 = pat2.matcher(nuevo);
            if (mat2.matches()) {
                aceptaciones = S + n;
            }
            for (int i = 0; i < prueba.length; i++) {   //recorremos el nuevo estado

                siguientes = siguiente.get(prueba[i]).toString();  //obtenemos los siguientes de cada siguiente del estado
                //System.out.println(siguientes);
                hoja = siguienteNodos.get(prueba[i]).toString();
                if (EstadosNuevos.get(siguientes) != null) {  //si el siguiente ya pertenece a un estado solo se añade recursivamente la hoja
                    ejemploLista.add(S + n + "->" + S + n + "[ label =" + hoja + "]");
                } else {
                    if (!prueba[i].equals(aceptacion)) {
                        ejemploLista.add(S + n + "->" + S + (n + 1) + "[ label =" + hoja + "]");
                        n++;//si el siguiente no existe entonces es un nuevo estado
                        EstadosNuevos.put(siguiente.get(prueba[i]), S + (n + 1));
                        aux = siguiente.get(prueba[i]).toString();
                        //System.out.println(aux);
                        Transicion2(aux, aceptacion);
                    }

                }

            }
        } catch (Exception e) {
        }
    }

    public void ImprimirTprueba() {
        Enumeration enumeration = EstadosNuevos.elements();
        Enumeration llaves = EstadosNuevos.keys();
        Enumeration enumeration2 = EstadosSiguiente.elements();
        Enumeration llaves2 = EstadosSiguiente.keys();
        while (enumeration.hasMoreElements()) {
            System.out.println("" + "hashtable valores: " + enumeration.nextElement());
        }

        while (llaves.hasMoreElements()) {
            System.out.println("" + "hashtable llaves: " + llaves.nextElement());
        }
        while (enumeration2.hasMoreElements()) {
            System.out.println("" + "hashtable valores N: " + enumeration2.nextElement());
        }

        while (llaves2.hasMoreElements()) {
            System.out.println("" + "hashtable llaves N: " + llaves2.nextElement());
        }

        for (int i = 0; i <= ejemploLista.size() - 1; i++) {
            System.out.println(ejemploLista.get(i));
        }

    }

    public void ImprimirS() {

        Enumeration enumeration = siguiente.elements();
        while (enumeration.hasMoreElements()) {
            System.out.println("" + "hashtable valores2: " + enumeration.nextElement());
        }
        Enumeration llaves = siguiente.keys();
        while (llaves.hasMoreElements()) {
            System.out.println("" + "hashtable llaves2: " + llaves.nextElement());
        }

    }

    public void clear() {
        siguiente.clear();
        siguienteNodos.clear();
        EstadosNuevos.clear();
        EstadosSiguiente.clear();
        ejemploLista.clear();

    }

    public void graficarSiguientes(String name) {
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
            Enumeration enumeration = siguiente.elements();
            Enumeration enumeration2 = siguienteNodos.elements();
            Enumeration llaves = siguiente.keys();
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
            //dirección donde se creara la imagen
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

    public void graficarTransiciones(String name) {
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
            Enumeration enumeration = EstadosNuevos.elements();

            Enumeration llaves = EstadosNuevos.keys();
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
            for (int i = 0; i <= ejemploLista.size() - 1; i++) {
                pw.println("<tr><td>" + ejemploLista.get(i).replace('>', ' ').replace("[", "").replace("]", "").replace("label", "-").replace("=", "") + "</td></tr>");
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

    public void graficarAutomatas(String name) {
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
            for (int i = 0; i <= ejemploLista.size() - 1; i++) {
                pw.println(ejemploLista.get(i) + ";");
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
