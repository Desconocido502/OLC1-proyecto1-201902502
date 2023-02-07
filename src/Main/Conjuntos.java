package Main;

/**
 *
 * @author CarlosSoto
 */
public class Conjuntos {

    private String id;
    private String notacion;

    public Conjuntos(String id, String notacion) {
        this.id = id;
        this.notacion = notacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNotacion() {
        return notacion;
    }

    public void setNotacion(String notacion) {
        this.notacion = notacion;
    }

}
