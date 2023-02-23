package Main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 *
 * @author EDUARDO
 */
public class Comparacion {

    public static String list_of_names = "";
    public static String notconj = "";
    public static String expRegular = "";
    public static ArrayList<Encadenado> lista_de_cadenas = new ArrayList<Encadenado>();
    public static ArrayList<Expresiones> lista_de_expresiones = new ArrayList<Expresiones>();
    public static ArrayList<Conjuntos> lista_de_conjunto = new ArrayList<Conjuntos>();
    public static ArrayList<String> lista_de_conjuntos = new ArrayList<String>();

    Hashtable contenedor2 = new Hashtable();
    String aprobado = "aprobado";
    String salida, nota, expresion;
    String notacion[];
    Hashtable contenedor = new Hashtable();
    Hashtable cadena = new Hashtable();
    Hashtable Expresion = new Hashtable();
    Stack stack1 = new Stack();
    Stack stack2 = new Stack();

    public Comparacion() {
        try {
            lista_de_conjuntos  = Container.getListaConjuntos();
            for (int i = 0; i < lista_de_conjuntos.size(); i++) {
                for (int j = 0; j < lista_de_cadenas.size(); j++) {
                    String aux = lista_de_cadenas.get(i).getEncadenado();

                    for (int k = 0; k < aux.length(); k++) {
                        Pattern pat2 = Pattern.compile(lista_de_conjuntos.get(i));
                        Matcher mat2 = pat2.matcher(aux);
                        if (mat2.matches()) {
                            aprobado = "correcta";
                        } else {
                            aprobado = "incorrecta";
                        }

                    }
                }
            }
        } catch (Exception e) {
        }

    }

    public static ArrayList<Conjuntos> getListaDeConjuntos() {
        return lista_de_conjunto;
    }

    public void addConjunto(String id, String conjunto) {
        contenedor2.put(id, conjunto);
    }

    public void add(String p) {
        if (contenedor2.get(p) != null) {
            String tmp = contenedor2.get(p).toString();
            stack1.push(tmp);
        } else {
            stack1.push(p);
        }
    }

    public void add2(String p) {
        stack2.push(p);
    }

    public String pop() {
        return stack1.pop().toString();
    }

    public String pop2() {
        return stack2.pop().toString();
    }

    public void clearStacks() {
        stack1.clear();
        stack2.clear();
    }

    //Metodo a evaluar
    public void addExpresion(String id) {
        System.out.println("Expresion regular: " + stack1.peek());
        Expresion.put(id, stack1.peek().toString());
        stack1.clear();
    }

    public String Expresion() {
        return stack1.peek().toString();
    }

    public String Expresion2() {
        return stack2.peek().toString();
    }

    public void printLists() {
        for (int i = 0; i < lista_de_cadenas.size(); i++) {
            System.out.println("cadenas{ id: " + lista_de_cadenas.get(i).getId() + ", cadena: " + lista_de_cadenas.get(i).getEncadenado() + " }");
            //CODIGO DE VENTANA PROXIMAMENTE
        }

        for (int i = 0; i < lista_de_conjunto.size(); i++) {
            //System.out.println("conjuntos{ id: " + lista_de_conjunto.get(i).getId() + "conjunto: " + lista_de_conjunto.get(i).getNotacion() + " }");
        }

        for (int i = 0; i < lista_de_expresiones.size(); i++) {
            //System.out.println("Expresion{ id: " + lista_de_expresiones.get(i).getId() + "expresion: " + lista_de_expresiones.get(i).getExpresionRegular() + " }");
        }
    }

    public static ArrayList<Encadenado> getListaCadenas() {
        return lista_de_cadenas;
    }

    //Pendiente
    public void comparar() {

    }

    public void createJson() {
        FileWriter file = null;
        PrintWriter pw = null;

        try {
            file = new FileWriter("src/SALIDAS_201902502/" + "Salida" + ".json");
            pw = new PrintWriter(file);
            pw.println("[");
            for (int i = 0; i < lista_de_cadenas.size(); i++) {
                pw.println("{");
                pw.println("\"Valor\": \"" + lista_de_cadenas.get(i).getEncadenado().replace("\'", "").replace("\"", "") + "\",");
                pw.println("\"ExpresionRegular\": " + "\"" + lista_de_cadenas.get(i).getId() + "\",");
                pw.println("\"Resultado\": " + "\"" + aprobado + "\"");
                if (i < lista_de_cadenas.size()) {
                    pw.println("},");
                } else {
                    pw.println("}");
                }
            }
            pw.print("]");
        } catch (Exception e) {
            System.out.println("error, no se realizo el archivo" + e);
        } finally {
            try {
                if (file != null) {
                    file.close();
                }
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        }
        lista_de_cadenas.clear();
    }
}
