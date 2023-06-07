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


    static void menuBaseConocimiento(Scanner scanner) {

        boolean continuar=true;
        while(continuar){
            System.out.println("\nMenú base de conocimiento:");
            System.out.println("1. Agregar cliente");
            System.out.println("2. Agregar categoria");
            System.out.println("3. Agregar producto");
            System.out.println("4. Agregar sucursal");
            System.out.println("5. Agregar compra");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            int opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    BaseConocimiento.agregarCliente();
                    break;

                case 2:
                    BaseConocimiento.agregarCategoria();
                    break;

                case 3:
                    BaseConocimiento.agregarProducto();
                    break;

                case 4:
                    BaseConocimiento.agregarSucursal();
                    break;

                case 5:
                    BaseConocimiento.agregarCompra();
                    break;

                case 6:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }
        };
    }
    public static void menuConsultas(Scanner scanner) {

        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenú consultas:");
            System.out.println("1. Consultar precio total de una lista");
            System.out.println("2. Consultar precio total de una compra");
            System.out.println("3. Calcular ganancias de cada una de las sucursales");
            System.out.println("4. Calcular el precio total de los productos de una categoria ");
           System.out.println("5. Mostrar los productos preferidos de las mujeres");
           System.out.println("6. Determinar si un cliente ha comprado un producto específico ");
           System.out.println("7. Saber si un cliente ha comprado un producto de cada categoria");
            System.out.println("8. Obtener una lista de productos comprados en una categoría específica por un cliente ");
            System.out.println("9. Obtener el total gastado por un cliente en una categoría específica ");
            System.out.println("10. Obtener el total gastado por un cliente en una sucursal específica ");
            System.out.println("11. Salir");
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
                case 10->{
                    Consulta.totalGastadoPorClienteEnSucursal();
                }
                case 11-> {
                    System.out.println("Saliendo...");
                    continuar = false;

                }
                default ->
                    System.out.println("Opción no válida. Intente nuevamente.");
            }
        }

    }

    static void menuPrincipal(Scanner scanner) {
        int opcion=0;
        boolean continuar = true;
        while (continuar) {
            System.out.println("\nMenú consultas:");
            System.out.println("1. Consultar hechos");
            System.out.println("2. Agregar hechos");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion=scanner.nextInt();

            switch (opcion) {
                case 1: {
                    menuConsultas(scanner);
                    break;
                }
                case 2: {
                    menuBaseConocimiento(scanner);
                    break;
                }
                case 3:
                    continuar = false;
                    break;
                default:
                    System.out.println("Opción inválida");
                    break;
            }

        }
        ;
    }




    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        leerBaseConocimiento();
        menuPrincipal(scanner);
        scanner.close();
    }

}
