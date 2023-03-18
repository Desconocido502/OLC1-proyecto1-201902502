package Main;

/**
 *
 * @author WINDOWS 10 PRO
 */
public class Expresiones {

    private String id;
    private String expresionRegular;

    public Expresiones(String id, String expresionRegular) {
        this.id = id;
        this.expresionRegular = expresionRegular;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

}
