package practica_UT1;
/**
 * @author Iván López Benítez
 * */
import java.io.File;
import java.io.IOException;

public class LlamaALeerFichero {

	public static void main(String[] args) {
		//Si hay más de un argumento Y menos de 5
		if(args.length >= 1 && args.length <= 5) {
			
			//Declaraciones
            File salidaFile = new File("src//practica_UT1//salidaPracticaUT1.txt");
            File erroresFile = new File("src//practica_UT1//Errores.txt");
			int i = 0, totalAbsoluto = 0;
			
			//bucle principal del programa
			while(i<args.length) {
				
				try {
					//Creación del proceso, cada vez que inicia el while
					ProcessBuilder pb = new ProcessBuilder("java", "-cp", "bin", "practica_UT1.LeerFichero", args[i]);
					//Redirecciones
                    pb.redirectOutput(ProcessBuilder.Redirect.appendTo(salidaFile));
                    pb.redirectError(ProcessBuilder.Redirect.appendTo(erroresFile));				
					//Iniciamos el proceso
					Process proceso = pb.start();
					//Indicando el proceso que estamos ejecutando
					System.out.println("Ejecutando: " + args[i]);
					//Sacamos el valor final de la suma a un exitCode
					int exitCode = proceso.waitFor();
					//Sumamos el total de cada proceso
					totalAbsoluto += exitCode;
					
				} catch (IOException e) {
					e.printStackTrace();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				i++;

			}
			
			System.out.println("Total acumulado: " + totalAbsoluto);
			
		} else {
			//No se han introducido argumentos
			System.out.println("Introduce al menos un argumento");
		}
	}

}
