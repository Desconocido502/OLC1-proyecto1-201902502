
package Analizadores;
import java_cup.runtime.*;
import Main.*;

%%

%class Lexico
%cupsym sym
%cup 
%public
%unicode
%line 
%column
%ignorecase

%init{

%init}

blancos = [ \t\r\n\f]+
simb = [\! \" \# \$ \% \& \' \( \) \* \+ \, \- \. \/ \: \; \< \= \> \? \@ \[ \\ \] \^ \_ \{ \| \}]
simbEspeciales = ("\\""n"|"\\" "\'" |"\\" "\"")
letras = [a-zA-Z]
entero = [0-9]
letras2 = [a-zA-Z0-9,]

//Simbolos
allave = "{"
cllave = "}"
dospuntos = ":"
pcoma = ";"
or = "|"
concatenacion = "."
suma = "+"
resta = "-"
asterisco = "*"
interrogacion = "?"
mayor = ">"
porcentajes = "%"

//Reservada
comentario = ("//".*\n)|("//".*\r)
comentario2 = ("<" "!"[^\!]* "!"">")
id_conj= "CONJ"
c_conjuntoER = ([\"] {simb} [\"])|([\"] {letras} [\"])|([\"] {entero} [\"])|[\"][\"]
especial_conjER = {simbEspeciales}

// Id's
nombres = {letras}({letras}|"_"|{entero})*
notacionj = ({letras2}|{simb}|{simbEspeciales})"~"({letras2}|{simb}|{simbEspeciales} )
notacionj2 = ({letras2})+ //notacionj3
cadenarevi2 = (\" [^\"]* \")
id_conjuntoER="{" [a-zA-Z0-9_]+ "}"

%{
    public void AddError(String tipo, String lexema, int filla, int columna){
        /*Aqui va la clase de Errores*/
    }
%}

%%

{blancos} { }
{comentario} { }
{comentario2} { }

{id_conj} {
    System.out.println("Se reconocio el id conjunto, lexema: " + yytext());
    return new Symbol(sym.id_conj, yycolumn, yyline, yytext());
}
{concatenacion} {
    System.out.println("Se reconocio la concatenacion, lexema: " + yytext());
    return new Symbol(sym.concatenacion, yycolumn, yyline, yytext());
}
{suma} {
    System.out.println("Se reconocio la suma, lexema: " + yytext());
    return new Symbol(sym.suma, yycolumn, yyline, yytext());
}
{asterisco} {
    System.out.println("Se reconocio el asterisco, lexema: " + yytext());
    return new Symbol(sym.asterisco, yycolumn, yyline, yytext());
}
{or} {
    System.out.println("Se reconocio el or, lexema: " + yytext());
    return new Symbol(sym.or, yycolumn, yyline, yytext());
}
{interrogacion} {
    System.out.println("Se reconocio el interrogacion, lexema: " + yytext());
    return new Symbol(sym.interrogacion, yycolumn, yyline, yytext());
}
{allave} {
    System.out.println("Se reconocio llave de apertura, lexema: " + yytext());
    return new Symbol(sym.allave, yycolumn, yyline, yytext());
}
{id_conjuntoER} {
    System.out.println("Se reconocio el id conjunto ER, lexema: " + yytext());
    return new Symbol(sym.id_conjuntoER, yycolumn, yyline, yytext());
}
{nombres} {
    System.out.println("Se reconocio nombres, lexema: " + yytext());
    return new Symbol(sym.nombres, yycolumn, yyline, yytext());
}
{c_conjuntoER} {
    System.out.println("Se reconocio el c_conjuntoER, lexema: " + yytext());
    return new Symbol(sym.c_conjuntoER, yycolumn, yyline, yytext());
}
{especial_conjER} {
    System.out.println("Se reconocio el especial_conjER, lexema: " + yytext());
    return new Symbol(sym.especial_conjER, yycolumn, yyline, yytext());
}
{notacionj} {
    System.out.println("Se reconocio el notacionj, lexema: " + yytext());
    return new Symbol(sym.notacionj, yycolumn, yyline, yytext());
}
{notacionj2} {
    System.out.println("Se reconocio el notacionj2, lexema: " + yytext());
    return new Symbol(sym.notacionj2, yycolumn, yyline, yytext());
}
{cadenarevi2} {
    System.out.println("Se reconocio el cadenarevi2, lexema: " + yytext());
    return new Symbol(sym.cadenarevi2, yycolumn, yyline, yytext());
}
{dospuntos} {
    System.out.println("Se reconocio el dospuntos, lexema: " + yytext());
    return new Symbol(sym.dospuntos, yycolumn, yyline, yytext());
}
{pcoma} {
    System.out.println("Se reconocio el pcoma, lexema: " + yytext());
    return new Symbol(sym.pcoma, yycolumn, yyline, yytext());
}
{resta} {
    System.out.println("Se reconocio el resta, lexema: " + yytext());
    return new Symbol(sym.resta, yycolumn, yyline, yytext());
}
{mayor} {
    System.out.println("Se reconocio el mayor, lexema: " + yytext());
    return new Symbol(sym.mayor, yycolumn, yyline, yytext());
}
{porcentajes} {
    System.out.println("Se reconocio el porcentajes, lexema: " + yytext());
    return new Symbol(sym.porcentajes, yycolumn, yyline, yytext());
}
{cllave} {
    System.out.println("Se reconocio llave de cierra, lexema: " + yytext());
    return new Symbol(sym.cllave, yycolumn, yyline, yytext());
}

. {
    System.err.println("Error lexico: "+ yytext()+ "Linea: "+(yyline)+ "columna: "+(yycolumn));
}