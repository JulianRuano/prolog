/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package co.unicauca.Main;

import org.jpl7.Query;
import org.jpl7.Term;
import org.jpl7.Variable;

/**
 *
 * @author Jorge
 */
public class Main {

    public static void leerBaseConocimiento() {
        String conexion = "consult('parcial.pl')";
        Query con = new Query(conexion);
        if (!con.hasSolution()) {
            System.err.println("Failed to consult Prolog file.");
            System.exit(1);
        } else {
            System.out.println("El documento se ha cargado con exito");
        }
    }
    
    

    public static void main(String[] args) {
        leerBaseConocimiento();
        
//            // Agregar un nuevo hecho a la base de conocimiento
//        Query assertQuery = new Query("assertz", new Term[]{
//                new org.jpl7.Compound("pelicula", new Term[]{
//                        new org.jpl7.Atom("accion"),
//                        new org.jpl7.Atom("Rapidos y furiosos sin control")
//                })
//        });
//        if (assertQuery.hasSolution()) {
//            System.out.println("Nuevo hecho agregado a la base de conocimiento.");
//        } else {
//            System.err.println("Error al agregar el nuevo hecho a la base de conocimiento.");
//        }
        Variable X = new Variable("X");
        String generoBuscado = "accion";
        Query peliculasPorGenero = new Query("pelicula", new Term[]{new org.jpl7.Atom(generoBuscado), X});
        System.out.println("Películas de género " + generoBuscado + ":");
        while (peliculasPorGenero.hasMoreSolutions()) {
            System.out.println(peliculasPorGenero.nextSolution().get("X"));
        }
    }

}
