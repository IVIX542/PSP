package multihilo.esperando_comida;

public class Cocinero implements Runnable {
    @Override
    public void run(){
        String plato = Thread.currentThread().getName();
        System.out.println("Cocinando: " + plato);

        try{
            if(plato.equals("Sopa")){
                Thread.sleep(3000);
            } else if(plato.equals("Filete")){
                Thread.sleep(5000);
            }else if(plato.equals("Postre")){
                Thread.sleep(2000);
            }
        }catch(InterruptedException e){
            System.out.println("Error de interrupción: " + e.getMessage());
        }

        System.out.println("El plato " + plato + " está listo!");
    }
}
