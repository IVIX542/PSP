package multihilo.carrera_hilos;

public class Corredor implements Runnable{
    @Override
    public void run(){
        for(int i = 1; i <= 10; i++){
            String nombre = Thread.currentThread().getName(); //Obtenemos el nombre del hilo
            System.out.println(nombre + " va por la vuelta " + i); //Imprimimos el nombre del hilo y la vuelta en la que va

            //Hacemos una pausa aleatoria entre 100 y 500 milisegundos
            try {
                Thread.sleep((long)(Math.random()*400 + 100));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("--- " + Thread.currentThread().getName() + " ha terminado la carrera ---");
    }
}
