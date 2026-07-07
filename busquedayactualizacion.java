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
    // Busca un nodo por su dato, devuelve el nodo o null si no lo encuentra
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
    // Actualiza el dato de un nodo buscado por su valor actual
    public static boolean actualizar(Nodo inicio, String datoBuscado, String datoNuevo) {
        Nodo nodo = buscar(inicio, datoBuscado);
        if (nodo != null) {
            nodo.setDato(datoNuevo);
            return true;
        }
        return false;
    }
    // Elimina el primer nodo que contenga el dato indicado
    // Devuelve el nuevo inicio de la lista (puede cambiar si se elimina el primer nodo)
    public static Nodo eliminar(Nodo inicio, String dato) {
        if (inicio == null) {
            return null;
        }
        // Caso especial: el nodo a eliminar es el primero
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
        return inicio; // no se encontró el dato
    }
    public static void main(String[] args) {
        Nodo n1 = new Nodo("A");
        Nodo n2 = new Nodo("B");
        Nodo n3 = new Nodo("C");
        Nodo n4 = new Nodo("D");
        Nodo n5 = new Nodo("E");
        Nodo n6 = new Nodo("F");
        Nodo n7 = new Nodo("G");
        n1.setSiguienteNodo(n2);
        n2.setSiguienteNodo(n3);
        n3.setSiguienteNodo(n4);
        n4.setSiguienteNodo(n5);
        n5.setSiguienteNodo(n6);
        n6.setSiguienteNodo(n7);
        Nodo actual = n1;
        while (actual != null) {
            String siguienteDato = (actual.getSiguienteNodo() != null)
                    ? actual.getSiguienteNodo().getDato()
                    : "null";
            System.out.println("El nodo contiene el dato " + actual.getDato()
                    + " y el siguiente nodo es " + siguienteDato);
            actual = actual.getSiguienteNodo();
        }
        // Prueba de búsqueda
        Nodo encontrado = buscar(n1, "D");
        if (encontrado != null) {
            System.out.println("Nodo encontrado con dato: " + encontrado.getDato());
        } else {
            System.out.println("Nodo no encontrado");
        }
        // Prueba de actualización
        actualizar(n1, "D", "Z");
        System.out.println("Después de actualizar D -> Z:");
        actual = n1;
        while (actual != null) {
            String siguienteDato = (actual.getSiguienteNodo() != null)
                    ? actual.getSiguienteNodo().getDato()
                    : "null";
            System.out.println("El nodo contiene el dato " + actual.getDato()
                    + " y el siguiente nodo es " + siguienteDato);
            actual = actual.getSiguienteNodo();
        }
        // Prueba de eliminación
        n1 = eliminar(n1, "Z");
        System.out.println("Después de eliminar Z:");
        actual = n1;
        while (actual != null) {
            String siguienteDato = (actual.getSiguienteNodo() != null)
                    ? actual.getSiguienteNodo().getDato()
                    : "null";
            System.out.println("El nodo contiene el dato " + actual.getDato()
                    + " y el siguiente nodo es " + siguienteDato);
            actual = actual.getSiguienteNodo();
        }
    }
}
