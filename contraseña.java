import java.util.Scanner;

final class Main {
    Main() {
    }

    void main() {
        Scanner scanner = new Scanner(System.in);
        String contraseñaCorrecta = "scary movie";

        String contraseña;
        do {
            IO.print("Ingresa tu contraseña: ");
            contraseña = scanner.nextLine();
            if (!contraseña.equals(contraseñaCorrecta)) {
                IO.println("Contraseña incorrecta. Intenta de nuevo.\n");
            }
        } while(!contraseña.equals(contraseñaCorrecta));

        IO.println("¡Contraseña correcta! Acceso concedido.");
    }
}