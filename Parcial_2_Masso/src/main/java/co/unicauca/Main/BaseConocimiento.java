package co.unicauca.Main;

import org.jpl7.Query;

import java.util.Scanner;

public class BaseConocimiento {

    static void agregarCliente(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del cliente: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el genero del cliente: ");
        String genero = scanner.nextLine();


        Query query = new Query("assertz", new org.jpl7.Term[]{
                new org.jpl7.Compound("cliente", new org.jpl7.Term[]{
                        new org.jpl7.Atom(nombre),
                        new org.jpl7.Atom(genero)
                })
        });
        if (query.hasSolution()) {
            System.out.println("Nuevo cliente agregado a la base de conocimiento.");
        } else {
            System.err.println("Error al agregar el nuevo cliente a la base de conocimiento.");
        }
    }
    //agregar categoria
    static void agregarCategoria(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la categoria: ");
        String nombre = scanner.nextLine();

        Query query = new Query("assertz", new org.jpl7.Term[]{
                new org.jpl7.Compound("categoria", new org.jpl7.Term[]{
                        new org.jpl7.Atom(nombre)
                })
        });
        if (query.hasSolution()) {
            System.out.println("Nueva categoria agregada a la base de conocimiento.");
        } else {
            System.err.println("Error al agregar la nueva categoria a la base de conocimiento.");
        }
    }

    //agregar producto
    static void agregarProducto(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre del producto: ");
        String nombre = scanner.nextLine();

        System.out.println("Ingrese el precio del producto: ");
        String precio = scanner.nextLine();

        System.out.println("Ingrese la categoria del producto: ");
        String categoria = scanner.nextLine();

        Query query = new Query("assertz", new org.jpl7.Term[]{
                new org.jpl7.Compound("producto", new org.jpl7.Term[]{
                        new org.jpl7.Atom(nombre),
                        new org.jpl7.Atom(precio),
                        new org.jpl7.Atom(categoria)
                })
        });
        if (query.hasSolution()) {
            System.out.println("Nuevo producto agregado a la base de conocimiento.");
        } else {
            System.err.println("Error al agregar el nuevo producto a la base de conocimiento.");
        }
    }

    //agregar sucursal
    static void agregarSucursal(){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Ingrese el nombre de la sucursal: ");
        String nombre = scanner.nextLine();


        Query query = new Query("assertz", new org.jpl7.Term[]{
                new org.jpl7.Compound("sucursal", new org.jpl7.Term[]{
                        new org.jpl7.Atom(nombre)
                })
        });

        if (query.hasSolution()) {
            System.out.println("Nueva sucursal agregada a la base de conocimiento.");
        } else {
            System.err.println("Error al agregar la nueva sucursal a la base de conocimiento.");
        }
    }

    static void agregarCompra(){
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del cliente: ");
        String cliente = scanner.nextLine();
        System.out.print("Ingrese los productos comprados (separados por comas): ");
        String productosComprados = scanner.nextLine();

        System.out.print("Ingrese el nombre de la sucursal donde se realizó la compra: ");
        String sucursalCompra = scanner.nextLine();
        StringBuilder productosStr = new StringBuilder("[");
        String [] prodComprados= productosComprados.split(",");
        for (int i = 0; i < prodComprados.length; i++) {
            productosStr.append(prodComprados[i].trim());
            if (i < prodComprados.length - 1) {
                productosStr.append(",");
            }
        }
        productosStr.append("]");

        Query query = new Query("assertz(compra(" + cliente + "," + productosStr + "," + sucursalCompra + "))");
        if (query.hasSolution()) {
            System.out.println("Compra agregada.");
        } else {
            System.out.println("No se pudo agregar la compra.");
        }

    }


    }



