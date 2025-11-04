package practica_UT1;
/**
 * @author Iván López Benítez
 * @version 1.0
 * */

//Manejo de Ficheros
import java.io.BufferedReader;
import java.io.FileReader;
//Excepciones
import java.io.IOException;

public class LeerFichero {

	public static void main(String[] args) {
		String rutaFichero = args[0].toString();

        try  {
        	
        	FileReader fr = new FileReader(rutaFichero);
        	
        	BufferedReader br = new BufferedReader(fr);
        	
            String linea;
            int ventas = 0, compras = 0;
            System.out.println("\n--LEYENDO: " + rutaFichero + "\n");

            //Si línea no es null, lee. De lo contrario, sale del bucle.
            while ((linea = br.readLine()) != null) {
                System.out.println("Linea a comprobar: " + linea); // Imprime la línea actual
                
                if(linea.startsWith("Venta")) {
                	ventas = ventas + Integer.parseInt(linea.substring(6, linea.length()));                
                }
                if(linea.startsWith("Compra")) {
                	compras = compras + Integer.parseInt(linea.substring(7, linea.length())); 
                }
                
            }
            
            int salida = ventas-compras;
            System.exit(salida);
            System.out.println("\nSe ha salido ya de la lectura con valor: " + salida);
            
            br.close();
        
        } catch (IOException e) {
            // Captura posibles errores, como que el fichero no exista.
            System.err.println("Error al leer el fichero: " + e.getMessage());
        }
	}

}
