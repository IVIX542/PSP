package multihilo.cronometro_doble;

public class CronometroThread extends Thread{

    public CronometroThread(String nombre){
        super(nombre);
    }
    
    @Override
    public void run(){
        for(int i = 0; i<5;i++){
            try {
                String nombreHilo = this.getName();
                System.out.println(nombreHilo);
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Iniciando cronómetros...");

        Thread tic = new CronometroThread("tic");
        Thread tac = new CronometroThread("tac");

        tic.start();
        tac.start();

        System.out.println("Cronómetros iniciados");
    }
}
