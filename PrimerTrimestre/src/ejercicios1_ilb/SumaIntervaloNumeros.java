package ejercicios1_ilb;

public class SumaIntervaloNumeros {
    public static void main(String[] args) {

        //a) Crea un programa llamado  SumaIntervaloNumeros que reciba dos números enteros en el main() y sume todo el intervalo 
        //que hay entre ellos. Muestra el resultado por pantalla.
        
        int num1 = Integer.parseInt(args[0]);
        int num2 = Integer.parseInt(args[1]);

        if(num1 > num2) {
            int temp = num1;
            num1 = num2;
            num2 = temp;
        } else if(num2 > num1) {
            int temp = num2;
            num2 = num1;
            num1 = temp;
        } else{
            System.out.println("Los números son iguales");
        }

        int suma = 0;
        for(int i = num1; i <= num2; i++) {
            suma += i;
        }


    }
}
