/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */
package co.unicauca.Main;

import java.util.HashMap;
import java.util.Scanner;
import org.jpl7.Query;
import org.jpl7.Term;

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
    
    public static void agregarHecho(String atomo1, String atomo2){
             Query assertQuery = new Query("assertz", new Term[]{
                new org.jpl7.Compound(atomo1, new Term[]{
                        new org.jpl7.Atom(atomo2),
                })
        });
        if (assertQuery.hasSolution()) {
            System.out.println("Nuevo hecho agregado a la base de conocimiento.");
        } else {
            System.err.println("Error al agregar el nuevo hecho a la base de conocimiento.");
        }
    }
    
    public static void MenuPrincipal(){
       Scanner scanner = new Scanner(System.in);
        OUTER:
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Consultar precio total de una lista");
            System.out.println("2. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> {
                       Consulta.calcularPrecioTotalListaProductos();            
               }
                case 2 -> {
                    System.out.println("Saliendo...");
                    break OUTER;
               }
                default -> System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        leerBaseConocimiento();
        MenuPrincipal();
    }

}