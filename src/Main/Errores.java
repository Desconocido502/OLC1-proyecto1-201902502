package Main;

/**
 *
 * @author CarlosSoto
 */
public class Errores {

    private String tipo_de_error;
    private String valor_de_error;
    private int fila;
    private int columna;

    public Errores(String tipo_de_error, String valor_de_error, int fila, int columna) {
        this.tipo_de_error = tipo_de_error;
        this.valor_de_error = valor_de_error;
        this.fila = fila;
        this.columna = columna;
    }

    public String getTipo_de_error() {
        return tipo_de_error;
    }

    public void setTipo_de_error(String tipo_de_error) {
        this.tipo_de_error = tipo_de_error;
    }

    public String getValor_de_error() {
        return valor_de_error;
    }

    public void setValor_de_error(String valor_de_error) {
        this.valor_de_error = valor_de_error;
    }

    public int getFila() {
        return fila;
    }

    public void setFila(int fila) {
        this.fila = fila;
    }

    public int getColumna() {
        return columna;
    }

    public void setColumna(int columna) {
        this.columna = columna;
    }

}
