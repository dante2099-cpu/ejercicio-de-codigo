import java.util.LinkedList;
import java.util.Queue;

public class FilaPersonas {
    public static void main(String[] args) {
        // Crear cola (fila) de personasedLis
        Queue<String> fila = new LinkedList<>();

        fila.add("Ana");
        fila.add("Carlos");
        fila.add("María");
        fila.add("Juan");
        fila.add("Laura");

        System.out.println("=== Ejercicio 2: Simular una fila de personas ===");
        System.out.println("Fila después de agregar 5 personas:");
        System.out.println(fila);

        System.out.println("\nQuién está al frente: " + fila.peek());

        String primera = fila.poll();
        System.out.println("\nAtendiendo: " + primera);

        String segunda = fila.poll();
        System.out.println("Atendiendo: " + segunda);

        System.out.println("\nFila después de atender 2 personas:");
        System.out.println(fila);

        System.out.println("\nCola restante: " + fila);
        System.out.println("Quién está ahora al frente: " + fila.peek());
        System.out.println("Total de personas restantes: " + fila.size());
    }
}
