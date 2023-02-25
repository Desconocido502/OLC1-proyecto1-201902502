package Main;

import java.io.StringReader;

/**
 *
 * @author CarlosSoto
 */
public class EXREGAN {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //System.out.println(Types.ROOT);
        String cadena = "{\n"
                + "\n"
                + "<!\n"
                + "	Este es un comentario multilinea\n"
                + "	Si no da error, ya salio el proyecto\n"
                + "	( l ((\"_\"|( l | d )))*)\n"
                + "	(( d )+(\".\"( d )+))\n"
                + "Escribe una expresión: \n"
                + "ab(1|2)*\n"
                + "expr2: a.b.(1|2)*\n"
                + "Expresion Infija: (ab(1|2)*)\n"
                + "Expresion Postfija: ab 1 2 | *\n"
                + "\n"
                + "\n"
                + "\n"
                + "!>\n"
                + "CONJ: letra -> a~z; // declarando conjunto de letras desde a hasta z en minusculas\n"
                + "CONJ: digito -> 0~3; // creamos el conjunto de digitos solo para 0, 1, 2 y 3\n"
                + "\n"
                + "\n"
                + "\n"
                + "//agregamos Expresiones regulares\n"
                + "identificador -> . {letra} * | \"_\" | {letra} {digito};\n"
                + "decimales -> . +{digito} . \".\" + {digito};\n"
                + "\n"
                + "\n"
                + "\n"
                + "%%\n"
                + "%%\n"
                + "identificador :  \"hola_soy_id_1\"; //correcto\n"
                + "identificador :  \"HoLA\"; //incorrecto\n"
                + "decimales : \"301.59\"; //incorrecto\n"
                + "decimales: \"1200.31\";//correcto\n"
                + "\n"
                + "}";
        try {
            Analizadores.parser sintactico;
            sintactico = new Analizadores.parser(new Analizadores.Lexico(new StringReader(cadena)));
            sintactico.parse();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

}
