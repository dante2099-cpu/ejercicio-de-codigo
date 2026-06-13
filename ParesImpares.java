import java.util.*;

public class ParesImpares {
    public static void main(String[] args) {
        Random random = new Random();

        Queue<Integer> colaOriginal = new LinkedList<>();

        for (int i = 0; i < 20; i++) {
            colaOriginal.add(random.nextInt(100) + 1);
        }

        System.out.println("=== Ejercicio 1: Números pares e impares ===");
        System.out.println("Cola original (20 números aleatorios):");
        System.out.println(colaOriginal);

        Queue<Integer> colaPares = new LinkedList<>();
        Queue<Integer> colaImpares = new LinkedList<>();

        for (Integer num : colaOriginal) {
            if (num % 2 == 0) {
                colaPares.add(num);
            } else {
                colaImpares.add(num);
            }
        }
        System.out.println("\nCola solo con números pares:");
        System.out.println(colaPares);
        System.out.println("Total de pares: " + colaPares.size());

        System.out.println("\nCola solo con números impares:");
        System.out.println(colaImpares);
        System.out.println("Total de impares: " + colaImpares.size());
    }
}