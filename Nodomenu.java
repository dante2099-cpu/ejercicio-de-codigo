import java.util.Scanner;

public class Nodo {
    private String dato;
    private Nodo siguienteNodo;
    public Nodo(String dato) {
        this.dato = dato;
        this.siguienteNodo = null;
    }
    public String getDato() {
        return dato;
    }
    public void setDato(String dato) {
        this.dato = dato;
    }
    public Nodo getSiguienteNodo() {
        return siguienteNodo;
    }
    public void setSiguienteNodo(Nodo siguienteNodo) {
        this.siguienteNodo = siguienteNodo;
    }
    public static Nodo buscar(Nodo inicio, String dato) {
        Nodo actual = inicio;
        while (actual != null) {
            if (actual.getDato().equals(dato)) {
                return actual;
            }
            actual = actual.getSiguienteNodo();
        }
        return null;
    }
    public static boolean actualizar(Nodo inicio, String datoBuscado, String datoNuevo) {
        Nodo nodo = buscar(inicio, datoBuscado);
        if (nodo != null) {
            nodo.setDato(datoNuevo);
            return true;
        }
        return false;
    }
    public static Nodo agregar(Nodo inicio, String dato) {
        Nodo nuevo = new Nodo(dato);
        if (inicio == null) {
            return nuevo;
        }
        Nodo actual = inicio;
        while (actual.getSiguienteNodo() != null) {
            actual = actual.getSiguienteNodo();
        }
        actual.setSiguienteNodo(nuevo);
        return inicio;
    }

    public static Nodo eliminar(Nodo inicio, String dato) {
        if (inicio == null) {
            return null;
        }
        if (inicio.getDato().equals(dato)) {
            return inicio.getSiguienteNodo();
        }
        Nodo actual = inicio;
        while (actual.getSiguienteNodo() != null) {
            if (actual.getSiguienteNodo().getDato().equals(dato)) {
                actual.setSiguienteNodo(actual.getSiguienteNodo().getSiguienteNodo());
                return inicio;
            }
            actual = actual.getSiguienteNodo();
        }
        return inicio;
    }
    public static void mostrar(Nodo inicio) {
        if (inicio == null) {
            System.out.println("La lista está vacía.");
            return;
        }
        Nodo actual = inicio;
        while (actual != null) {
            String siguienteDato = (actual.getSiguienteNodo() != null)
                    ? actual.getSiguienteNodo().getDato()
                    : "null";
            System.out.println("El nodo contiene el dato " + actual.getDato()
                    + " y el siguiente nodo es " + siguienteDato);
            actual = actual.getSiguienteNodo();
        }
    }
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Nodo inicio = null;

        while (true) {
            System.out.println("\n--- MENU LISTA ENLAZADA ---");
            System.out.println("1. Agregar nodo");
            System.out.println("2. Buscar nodo");
            System.out.println("3. Actualizar nodo");
            System.out.println("4. Eliminar nodo");
            System.out.println("5. Mostrar lista");
            System.out.print("Elige una opción: ");
            int opcion = Integer.parseInt(sc.nextLine());

            switch (opcion) {
                case 1:
                    System.out.print("Dato a agregar: ");
                    String datoAgregar = sc.nextLine();
                    inicio = agregar(inicio, datoAgregar);
                    System.out.println("Nodo agregado.");
                    break;

                case 2:
                    System.out.print("Dato a buscar: ");
                    String datoBuscar = sc.nextLine();
                    Nodo encontrado = buscar(inicio, datoBuscar);
                    if (encontrado != null) {
                        System.out.println("Nodo encontrado con dato: " + encontrado.getDato());
                    } else {
                        System.out.println("Nodo no encontrado.");
                    }
                    break;

                case 3:
                    System.out.print("Dato actual a buscar: ");
                    String datoViejo = sc.nextLine();
                    System.out.print("Nuevo dato: ");
                    String datoNuevo = sc.nextLine();
                    boolean actualizado = actualizar(inicio, datoViejo, datoNuevo);
                    System.out.println(actualizado ? "Nodo actualizado." : "Nodo no encontrado.");
                    break;

                case 4:
                    System.out.print("Dato a eliminar: ");
                    String datoEliminar = sc.nextLine();
                    inicio = eliminar(inicio, datoEliminar);
                    System.out.println("Nodo eliminado (si existía).");
                    break;

                case 5:
                    mostrar(inicio);
                    break;

                default:
                    System.out.println("Opción no válida.");
            }
        }
    }
}