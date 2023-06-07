package co.unicauca.Main;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author Jorge
 */
public class Consulta {

    static void calcularPrecioTotalListaProductos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese los nombres de los productos separados por comas: ");
        String productosStr = scanner.nextLine();
        String[] productosArr = productosStr.split(",");
        StringBuilder productosProlog = new StringBuilder("[");
        for (int i = 0; i < productosArr.length; i++) {
            productosProlog.append("'").append(productosArr[i].trim()).append("'");
            if (i < productosArr.length - 1) {
                productosProlog.append(",");
            }
        }
        productosProlog.append("]");

        String consulta = "calcularPrecioTotal(" + productosProlog + ", PrecioTotal)";
        Query q2 = new Query(consulta, new Term[]{});

        if (q2.hasNext()) {
            HashMap<String, Term> resultado = (HashMap<String, Term>) q2.nextSolution();
            int precioTotal = resultado.get("PrecioTotal").intValue();
            System.out.printf("El precio total de los productos es: %d\n", precioTotal);
        } else {
            System.out.println("Error al calcular el precio total. Verifique los nombres de los productos.");
        }
    }

    static void calcularPrecioCompra() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre del cliente que hizo la compra: ");
        String nombreCliente = scanner.nextLine();
        String consulta = String.format("calcularPrecio('%s', ListaPrecios)", nombreCliente);
        Query q = new Query(consulta);

        if (q.hasNext()) {
            Map<String, Term> resultado = q.nextSolution();
            Term[] listaPreciosTerm = resultado.get("ListaPrecios").toTermArray();
            List<Integer> listaPrecios = new ArrayList<>();

            for (Term precioTerm : listaPreciosTerm) {
                listaPrecios.add(precioTerm.intValue());
            }

            int precioTotal = listaPrecios.stream().mapToInt(Integer::intValue).sum();
            System.out.printf("El precio total de las compras de %s es: %d\n", nombreCliente, precioTotal);
        } else {
            System.out.println("Error al calcular el precio total de las compras. Verifique el nombre del cliente.");
        }
    }

    static void calcularGananciasSucursales() {
        String consulta = "findall(Sucursal-Ganancias, (sucursal(Sucursal, _), calcularGananciasSucursal(Sucursal, Ganancias)), ListaGananciasSucursales)";
        Query q = new Query(consulta);

        if (q.hasNext()) {
            Map<String, Term> resultado = q.nextSolution();
            Term[] listaGananciasSucursalesTerm = resultado.get("ListaGananciasSucursales").toTermArray();

            System.out.println("Ganancias de cada sucursal:");
            for (Term gananciasSucursalTerm : listaGananciasSucursalesTerm) {
                String sucursal = gananciasSucursalTerm.arg(1).toString();
                int ganancias = gananciasSucursalTerm.arg(2).intValue();
                System.out.printf("Sucursal %s: %d\n", sucursal, ganancias);
            }
        } else {
            System.out.println("Error al calcular las ganancias de las sucursales.");
        }
    }

    static void calcularPrecioTotalCategoria() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Ingrese el nombre de la categoria: ");
        String nombreCategoria = scanner.nextLine();
        String consulta = String.format("calcularPrecioCategoria('%s', PrecioTotal)", nombreCategoria);
        Query q = new Query(consulta);

        if (q.hasNext()) {
            Map<String, Term> resultado = q.nextSolution();
            int precioTotal = resultado.get("PrecioTotal").intValue();
            System.out.printf("El precio total de la categoría %s es: %d\n", nombreCategoria, precioTotal);
        } else {
            System.out.println("Error al calcular el precio total de la categoría.");
        }
    }

    static void productosPreferidosMujeres() {
        System.out.println("4. Calcular ganancias de cada una de las sucursales");
    }

    public Consulta() {

    }

}
