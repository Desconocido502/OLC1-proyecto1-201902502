package Main;

import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 *
 * @author EDUARDO
 */
public class Reporte {

    public static ArrayList<Errores> listaErrores = new ArrayList<Errores>();

    public void ReporteHTML(String nombre) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/ERRORES_201902502/" + nombre + ".html");
            pw = new PrintWriter(fichero);
            pw.println("<!DOCTYPE html>");
            pw.println("<html>");
            pw.println("<head>");
            pw.println("<meta charset=\"utf-8\">");
            pw.println("<link rel=\"stylesheet\" href=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css\">");
            pw.println("<script src=\"https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>");
            pw.println("<script src=\"https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js\"></script>");
            pw.println("<link href=\"https://fonts.googleapis.com/css?family=Kaushan+Script\" rel=\"stylesheet\" type=\"text/css\">");
            pw.println("<link href=\"estilo.css\" type=\"text/css\" rel=\"stylesheet\" media=\"\">");
            pw.println("</head>");
            pw.println("<body>");
            pw.println("<center>");
            pw.println("<header>");
            pw.println("REPORTE DE ERRORES");
            pw.println("</header>");
            pw.println("<div>");
            pw.println("<table class=\"table table-dark\">");
            pw.println("<thead>");
            pw.println("<tr>");
            pw.println("<th scope=\"col\">#</th>");
            pw.println("<th scope=\"col\">Tipo Error</th>");
            pw.println("<th scope=\"col\">Valor</th>");
            pw.println("<th scope=\"col\">Fila</th>");
            pw.println("<th scope=\"col\">Columna</th>");
            pw.println("</tr>");
            pw.println("</thead>");
            pw.println("<tbody>");
            for (int i = 0; i < listaErrores.size(); i++) {
                pw.print("<tr>");
                pw.print("<td>");
                pw.print(i);
                pw.print("</td>");
                pw.print("<td>");
                pw.print(listaErrores.get(i).getTipo_de_error());
                pw.print("</td>");
                pw.print("<td>");
                pw.print(listaErrores.get(i).getValor_de_error());
                pw.print("</td>");
                pw.print("<td>");
                pw.print(listaErrores.get(i).getFila());
                pw.print("</td>");
                pw.print("<td>");
                pw.print(listaErrores.get(i).getColumna());
                pw.print("</td>");
                pw.println("<tr>");
            }

            pw.println("</tbody>");
            pw.println("</table>");
            pw.println("</div>");
            pw.println("</center>");
            pw.println("</body>");
            pw.println("</html>");

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

    }

    public void graphAFND(Nodo tree, String name) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter("src/AFND_201902502/" + name + ".dot");
            pw = new PrintWriter(fichero);
            AFND afnd = new AFND(tree);

            String s = " digraph G {\n"
                    + "    node [shape=circle fontsize=13 fontname = \"helvetica\"];\n"
                    + "    nodesep=0.4;\n"
                    + "    ranksep=0.5;\n"
                    + "    rankdir=LR;\n\n";

            System.out.println("\n--------------------------------------------------\n");
            for (Transicion t : afnd.getTransitions()) {
                s += t.initialState + " -> " + t.finalState + " [label=\"" + t.transition.replace("\"", "") + "\"];\n";
                //System.out.println("S" + t.initialState + " -> " + "S" + t.finalState + " [label=\"" + t.transition.replace("\"", "") + "\"];");
            }
            //System.out.println(afnd.getLast());
            System.out.println("\n--------------------------------------------------\n");
            s += afnd.getLast() + " [shape=doublecircle];\n";
            s += "}";

            pw.println(s);
            pw.close();

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
        }//Fin try-catch

        //para compilar el archivo dot y obtener la imagen
        try {
            //dirección donde se encuentra el compilador de graphviz
            String dotPath = "C:\\Program Files\\Graphviz\\bin\\dot.exe";
            //dirección del archivo dot
            String fileInputPath = "src/AFND_201902502/" + name + ".dot";
            //dirección donde se creara la magen
            String fileOutputPath = "src/AFND_201902502/" + name + ".jpg";
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
