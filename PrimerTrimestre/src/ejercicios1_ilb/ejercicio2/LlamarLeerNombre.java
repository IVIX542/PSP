package ejercicios1_ilb.ejercicio2;

public class LlamarLeerNombre {
    //Dentro del mismo paquete crea una clase llamada LlamarLeerFicheros que llame al anterior programa, pasándole los argumentos
    //necesarios y que muestre el resultado por pantalla.
    public static void main(String[] args) {
        String[] argumentos = {"fichero.txt"};
        LeerNombre.main(argumentos);
    }
}
