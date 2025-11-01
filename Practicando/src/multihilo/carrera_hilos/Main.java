package multihilo.carrera_hilos;

public class Main {
    public static void main(String[] args) {
        Thread mario = new Thread(new Corredor(), "Mario");
        Thread luigi = new Thread(new Corredor(), "Luigi");
        Thread peach = new Thread(new Corredor(), "Peach");
        mario.start();
        luigi.start();
        peach.start();
        
    }
}
