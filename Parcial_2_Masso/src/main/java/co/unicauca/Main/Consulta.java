package co.unicauca.Main;

import java.util.HashMap;
import java.util.Scanner;
import org.jpl7.Query;
import org.jpl7.Term;

/**
 *
 * @author Jorge
 */
public class Consulta {
    public static void calcularPrecioTotalListaProductos(){
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
                Query q2 = new Query(consulta,new Term[]{});

                if (q2.hasNext()) {
                    HashMap<String, Term> resultado = (HashMap<String, Term>) q2.nextSolution();
                    int precioTotal = resultado.get("PrecioTotal").intValue();
                    System.out.printf("El precio total de los productos es: %d\n", precioTotal);
                } else {
                    System.out.println("Error al calcular el precio total. Verifique los nombres de los productos.");
                }
    }
    
    public Consulta(){
        
    }
    
    
}