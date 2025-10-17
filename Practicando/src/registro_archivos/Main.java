package registro_archivos;

//Clases
import java.io.File;

//Excepciones
import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		/**
		 * Tarea 1: Configuración del Entorno y Estructura de Ficheros (Clase)
		 * 	1. Definición de Rutas (Portabilidad): Defina una ruta base para su proyecto que será el directorio de trabajo.
		 * 	   Asegúrese de utilizar la forma recomendada para construir rutas que garantice la portabilidad, usando el separador de ficheros 
		 * 	   adecuado para el sistema operativo.
		 * 	2. Creación de Directorios: Utilice la clase File para crear dos nuevos directorios anidados si no existen: REGISTROS y dentro
		 * 	   de este, LOGS
		 * 	3. Verificación: Compruebe si el directorio LOGS se creó exitosamente.
		 * 
		 * Tarea 2: Ejecución del Proceso Externo
		 * 	1. Definición del Comando: Utilice la clase ProcessBuilder para configurar la ejecución de un comando del sistema operativo que
		 * 	   liste los archivos del directorio REGISTROS y sus subdirectorios.
		 * 		 Ayuda: Utilice el método command() de ProcessBuilder para definir el comando y sus argumentos como una lista de cadenas.
		 *  2. Lanzamiento del Proceso: Inicie la ejecución del proceso utilizando el método start(). Este método devolverá un objeto de la
		 *     clase Process.
		 * 
		 * Tarea 3: Captura y Persistencia de la Salida (Flujos de Caracteres)
		 * 	1. Captura de Salida Estándar (stdout): Acceda al flujo de entrada del objeto Process (el flujo que contiene la salida normal del
		 * 	   comando, stdout) usando getInputStream().
		 * 	2. Implementación del Flujo de Lectura: Envuelva este InputStream en un puente de flujos de bytes a caracteres (InputStreamReader)
		 * 	   y luego en un buffer de lectura (clase BufferedReader) para poder leer la salida línea a línea.
		 * 	3. Creación del Fichero de Log: Dentro del directorio LOGS, cree un nuevo archivo llamado Registro_Salida.txt.
		 * 	4. Implementación del Flujo de Escritura: Utilice un flujo de caracteres con buffer (FileWriter y PrintWriter o BufferedWriter)
		 * 	   para escribir en el archivo de log.
		 * 	5. Proceso de Transferencia: Mientras lea líneas del BufferedReader (usando readLine()), escríbalas inmediatamente en el archivo
		 * 	   Registro_Salida.txt (usando println() de PrintWriter).
		 * 
		 * Tarea 4: Control y Finalización
		 * 	1. Sincronización: Asegúrese de que el programa Java espere a que el proceso externo termine su ejecución antes de continuar
		 * 	   (o finalizar). Utilice el método waitFor() del objeto Process.
		 * 	2. Manejo de Errores: Incluya bloques try-catch para manejar las excepciones de E/S. Las operaciones de gestión de procesos
		 * 	   y flujos pueden lanzar IOException.
		 * 	3. Cierre de Flujos: Cierre todos los flujos (BufferedReader, FileWriter, etc.) utilizados para liberar los recursos del sistema.
		 * */
		
		//Tarea 1: Configuración del Entorno y Estructura de Ficheros (Clase)
		String nombredirTrabajo = "src//registro_archivos//"; //Carpeta principal
		
		//Carpeta registros
		String nombredirRegistros = nombredirTrabajo + "REGISTROS//";
		File dirRegistros = new File(nombredirRegistros);
		
		//Carpeta logs
		String nombreDirLogs = nombredirRegistros + "LOGS//";
		File dirLogs = new File(nombreDirLogs);
		
		
		if(!dirRegistros.exists()) {
			dirRegistros.mkdirs();
			System.out.println("Directorio creado en: " + dirRegistros.getAbsolutePath() + ".");			
		} else {
			System.out.println("Directorio REGISTROS ya existente en: " + dirRegistros.getAbsolutePath() + ".");	
		}
		
		if(!dirLogs.exists()){
			dirLogs.mkdirs();
		} else {
			System.out.println("Directorio LOGS ya existente en: " + dirLogs.getAbsolutePath() + ".");
		}
		
		
		//Tarea 2: Ejecución del Proceso Externo
		ProcessBuilder pb = new ProcessBuilder(); //Declaración del proceso
		
		pb.command("CMD.EXE", "/C", "DIR"); //Comando que se va a ejecutar
		pb.redirectError(new File(nombreDirLogs + "ErroresDeEjecucion.txt")); //Redirigir los errores del proceso a un archivo de errores
		
		File logs = new File(nombreDirLogs + "RegistroSalida.txt"); //Declarar el fichero de salida para los logs.
		pb.redirectOutput(logs); //Redireccionar la salida del proceso al fichero logs
		

		Process proceso = pb.start(); //Iniciar el proceso
		
		//Tarea 3: Captura y Persistencia de la Salida (Flujos de Caracteres)
		
		//Finalización del proceso
		int espera = proceso.waitFor();
		System.out.print("Finalización proceso: ");
		switch(espera) {
		case 0:
			System.out.println("Exitosa.");
			break;
		default:
			System.out.println("Fallida.");
		}

	}

}
