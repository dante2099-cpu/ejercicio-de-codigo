import java.util.Scanner;
import java.util.Stack;


public class InvertirPalabra {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("=== Ejercicio 3: Invertir palabra usando pila ===");
        System.out.print("Solicitar una palabra: ");
        String palabra = scanner.nextLine().toUpperCase();

        Stack<Character> pila = new Stack<>();

        for (char letra : palabra.toCharArray()) {
            pila.push(letra);
        }

        String palabraInvertida = "";
        while (!pila.isEmpty()) {
            palabraInvertida += pila.pop();
        }

        System.out.println("\nPalabra original: " + palabra);
        System.out.println("Palabra invertida (usando pila): " + palabraInvertida);

        if (palabra.equals(palabraInvertida)) {
            System.out.println("\n✓ La palabra '" + palabra + "' es un PALÍNDROMO");
        } else {
            System.out.println("\n✗ La palabra '" + palabra + "' NO es un palíndromo");
        }

        scanner.close();
    }
}