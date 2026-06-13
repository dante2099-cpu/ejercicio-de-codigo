import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;
import java.util.Stack;

public class Cafeteria {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Queue<String> colaClientes = new LinkedList<>();
        Stack<String> pilaCancelados = new Stack<>();

        int opcion;

        System.out.println("=== Ejercicio 4: Cafetería ===");

        do {
            System.out.println("\n--- MENÚ ---");
            System.out.println("1. Llegar cliente");
            System.out.println("2. Atender cliente");
            System.out.println("3. Cancelar pedido");
            System.out.println("4. Mostrar cola");
            System.out.println("5. Mostrar cancelaciones");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    System.out.print("Nombre del cliente: ");
                    scanner.nextLine();
                    String nombreCliente = scanner.nextLine();
                    colaClientes.add(nombreCliente);
                    System.out.println("✓ Cliente '" + nombreCliente + "' ha llegado a la cola");
                    break;
                case 2:
                    if (colaClientes.isEmpty()) {
                        System.out.println("✗ No hay clientes en la cola");
                    } else {
                        String clienteAtendido = colaClientes.poll();
                        System.out.println("✓ Atendiendo cliente: " + clienteAtendido);
                    }
                    break;
                case 3:
                    if (colaClientes.isEmpty()) {
                        System.out.println("✗ No hay clientes en la cola para cancelar");
                    } else {
                        System.out.print("Nombre del cliente con pedido a cancelar: ");
                        String nombre = scanner.nextLine();
                        if (colaClientes.contains(nombre)) {
                            colaClientes.remove(nombre);
                            pilaCancelados.push(nombre + " - Pedido cancelado");
                            System.out.println("✓ Pedido de '" + nombre + "' cancelado y almacenado en pila");
                        } else {
                            System.out.println("✗ Cliente '" + nombre + "' no encontrado en la cola");
                        }
                    }
                    break;
                case 4:
                    System.out.println("\n--- Cola de clientes esperando ---");
                    if (colaClientes.isEmpty()) {
                        System.out.println("No hay clientes en la cola");
                    } else {
                        System.out.println("Total de clientes: " + colaClientes.size());
                        System.out.println("Cliente al frente: " + colaClientes.peek());
                        System.out.println("Clientes: " + colaClientes);
                    }
                    break;
                case 5:
                    System.out.println("\n--- Pedidos cancelados (pila) ---");
                    if (pilaCancelados.isEmpty()) {
                        System.out.println("No hay pedidos cancelados");
                    } else {
                        System.out.println("Total de cancelaciones: " + pilaCancelados.size());
                        System.out.println("Última cancelación (top de pila): " + pilaCancelados.peek());
                        System.out.println("Cancelaciones: " + pilaCancelados);
                    }
                    break;
                case 6:
                    System.out.println("✓ Exiting del sistema de cafetería");
                    break;
                default:
                    System.out.println("✗ Opción inválida");
            }
        } while (opcion != 6);
    }
}