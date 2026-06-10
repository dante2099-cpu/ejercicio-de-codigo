import java.util.Random;

public class SumaEnteros {
    public static void main(String[] args) {
        Random random = new Random();
        
        int[] numeros = new int[10];
        for (int i = 0; i < numeros.length; i++) {
            numeros[i] = random.nextInt(21) - 10;
        }
        
        int pivote = random.nextInt(21) - 10;
        int mayores = 0, menores = 0, iguales = 0, suma = 0;
        
        System.out.print("Arreglo: ");
        for (int n : numeros) System.out.print(n + " ");
        
        System.out.println("\nPivote: " + pivote);
        
        System.out.print("\nMayores que " + pivote + " → ");
        boolean primero = true;
        for (int n : numeros) {
            if (n > pivote) {
                if (!primero) System.out.print(", ");
                System.out.print(n);
                primero = false;
                mayores++;
            }
            if (n < pivote) menores++;
            if (n == pivote) iguales++;
            suma += n;
        }
        if (mayores == 0) System.out.print("ninguno");
        
        System.out.print("\nMenores que " + pivote + " → ");
        primero = true;
        for (int n : numeros) {
            if (n < pivote) {
                if (!primero) System.out.print(", ");
                System.out.print(n);
                primero = false;
            }
        }
        if (menores == 0) System.out.print("ninguno");
        
        System.out.println("\nIgual que " + pivote + ": " + (iguales > 0 ? iguales : "ninguno"));
        System.out.println("Suma: " + suma);
        System.out.println("Promedio: " + (suma / numeros.length));
    }
}