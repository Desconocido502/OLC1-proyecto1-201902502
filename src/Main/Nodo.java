package Main;

import Main.NodeType.Types;

/**
 *
 * @author CarlosSoto
 */
public class Nodo {

    public Nodo hizq;
    public Nodo hder;
    public Types type;
    public String lexema; //valor
    public int id;
    public int identificador;
    public String anulable;
    public String primero;
    public String ultimo;

    public Nodo(String lexema, Types type, Nodo hizq, Nodo hder, int id, int identificador, String anulable, String primero, String ultimo) {
        this.lexema = lexema;
        this.type = type;
        this.hizq = hizq;
        this.hder = hder;
        this.id = id;
        this.identificador = identificador;
        this.anulable = anulable;
        this.primero = primero;
        this.ultimo = ultimo;
    }

    public String getAnulable() {
        return anulable;
    }

    public String getPrimero() {
        return primero;
    }

    public void setPrimero(String primero) {
        this.primero = primero;
    }

    public String getUltimo() {
        return ultimo;
    }

    public void setUltimo(String ultimo) {
        this.ultimo = ultimo;
    }

    public void setAnulable(String anulable) {
        this.anulable = anulable;
    }

    public int getIdentificador() {
        return identificador;
    }

    public void setIdentificador(int identificador) {
        this.identificador = identificador;
    }

    public Nodo getHizq() {
        return hizq;
    }

    public void seHizq(Nodo hizq) {
        this.hizq = hizq;
    }

    public Nodo getHder() {
        return hder;
    }

    public void setHder(Nodo hder) {
        this.hder = hder;
    }

    public String getLexema() {
        return lexema;
    }

    public void setLexema(String lexema) {
        this.lexema = lexema;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getInternalCode() {
        String cadena;
        if (hizq == null && hder == null) {
            cadena = "nodo" + id + " [ label =< \n"
                    + "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n"
                    + "<tr><td>" + anulable + "</td></tr>\n"
                    + "<tr><td>" + primero + "</td><td>" + lexema + "</td><td>" + ultimo + "</td></tr>\n"
                    + "<tr><td>" + identificador + "</td></tr> \n"
                    + "</table>\n"
                    + ">];\n";
        } else {
            cadena = "nodo" + id + " [ label =< \n"
                    + "<table border='0' cellborder='1' color='blue' cellspacing='0'>\n"
                    + "<tr><td>" + anulable + "</td></tr>\n"
                    + "<tr><td>" + primero + "</td><td>" + lexema + "</td><td>" + ultimo + "</td></tr>\n"
                    + "<tr><td>" + identificador + "</td></tr> \n"
                    + "</table>\n"
                    + ">];\n";
        }
        if (hizq != null) {
            cadena = cadena + hizq.getInternalCode()
                    + "nodo" + id + "->nodo" + hizq.id + "\n";
        }
        if (hder != null) {
            cadena = cadena + hder.getInternalCode()
                    + "nodo" + id + "->nodo" + hder.id + "\n";
        }

        return cadena;
    }

}
