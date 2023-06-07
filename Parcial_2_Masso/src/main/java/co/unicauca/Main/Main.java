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

    public static void agregarHecho(String atomo1, String atomo2) {
        Query assertQuery = new Query("assertz", new Term[]{
            new org.jpl7.Compound(atomo1, new Term[]{
                new org.jpl7.Atom(atomo2),})
        });
        if (assertQuery.hasSolution()) {
            System.out.println("Nuevo hecho agregado a la base de conocimiento.");
        } else {
            System.err.println("Error al agregar el nuevo hecho a la base de conocimiento.");
        }
    }

    public static void MenuPrincipal() {
        Scanner scanner = new Scanner(System.in);
        OUTER:
        while (true) {
            System.out.println("\nMenú:");
            System.out.println("1. Consultar precio total de una lista");
            System.out.println("2. Consultar precio total de una compra");
            System.out.println("3. Calcular ganancias de cada una de las sucursales");
            System.out.println("4. Calcular el precio total de los productos de una categoria ");
           System.out.println("5. Mostrar los productos preferidos de las mujeres");
           System.out.println("6. Determinar si un cliente ha comprado un producto específico ");
           System.out.println("7. Saber si un cliente ha comprado un producto de cada categoria");
            System.out.println("8. Obtener una lista de productos comprados en una categoría específica por un cliente ");
            System.out.println("9. Obtener el total gastado por un cliente en una categoría específica ");


//            System.out.println("4. ");
//            System.out.println("4. ");
//            System.out.println("4. ");
//            System.out.println("4. ");
//            System.out.println("4. ");
            System.out.println("15. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();
            switch (opcion) {
                case 1 -> {
                    Consulta.calcularPrecioTotalListaProductos();
                }
                case 2 -> {
                    Consulta.calcularPrecioCompra();
                }
                case 3 -> {
                    Consulta.calcularGananciasSucursales();
                }
                case 4 -> {
                    Consulta.calcularPrecioTotalCategoria();
                }
                case 5->{
                    Consulta.productosPreferidosMujeres();
                }
                case 6->{
                    Consulta.clienteComproProductoEspecifico();
                }
                case 7->{
                    Consulta.clienteComproTodasCategorias();
                }
                case 8->{
                    Consulta.listaProductosCompradosCategoriaCliente();
                }
                case 9->{
                    Consulta.totalGastadoPorClienteEnCategoria();
                }
                case 15-> {
                    System.out.println("Saliendo...");
                    break OUTER;
                }
                default ->
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }
        scanner.close();
    }

    public static void main(String[] args) {
        leerBaseConocimiento();
        MenuPrincipal();
    }

}
