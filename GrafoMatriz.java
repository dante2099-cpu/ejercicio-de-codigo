import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class GrafoMatriz {
    private int numVertices;
    private List<String> vertices;
    private int[][] matrizAdyacencia;

    public GrafoMatriz(int numVertices) {
        this.numVertices = numVertices;
        this.vertices = new ArrayList<>();
        this.matrizAdyacencia = new int[numVertices][numVertices];
    }
    public void agregarVertice(String vertice) {
        if (vertices.size() < numVertices) {
            vertices.add(vertice);
            System.out.println("Vértice '" + vertice + "' agregado.");
        } else {
            System.out.println("No se pueden agregar más vértices, se alcanzó el límite.");
        }
    }
    public void agregarArista(String origen, String destino) {
        int idxOrigen = vertices.indexOf(origen);
        int idxDestino = vertices.indexOf(destino);

        if (idxOrigen == -1 || idxDestino == -1) {
            System.out.println("Uno o ambos vértices no existen.");
            return;
        }
        matrizAdyacencia[idxOrigen][idxDestino] = 1;
        matrizAdyacencia[idxDestino][idxOrigen] = 1; // Para grafo no dirigido
        System.out.println("Arista entre '" + vertices.get(idxOrigen) + "' y '" + vertices.get(idxDestino) + "' agregada.");
    }
    public void mostrarMatrizAdyacencia() {
        System.out.println("\nMatriz de Adyacencia:");

        StringBuilder header = new StringBuilder("  ");
        for (String v : vertices) {
            header.append(v).append(" ");
        }
        System.out.println(header.toString());

        for (int i = 0; i < matrizAdyacencia.length; i++) {
            StringBuilder fila = new StringBuilder(vertices.get(i) + " ");
            for (int valor : matrizAdyacencia[i]) {
                fila.append(valor).append(" ");
            }
            System.out.println(fila.toString());
        }
    }
    public List<String> getVertices() {
        return vertices;
    }
}
class GrafoMatrizApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("--- Creación de Grafo con Matriz de Adyacencia ---");
        int numVertices = 0;
        while (true) {
            System.out.print("Ingrese la cantidad de vértices que desea (mínimo 2): ");
            String entrada = scanner.nextLine().trim();
            try {
                numVertices = Integer.parseInt(entrada);
                if (numVertices < 2) {
                    System.out.println("La cantidad de vértices debe ser al menos 2.");
                    continue;
                }
                break;
            } catch (NumberFormatException e) {
                System.out.println("Entrada inválida. Por favor, ingrese un número entero.");
            }
        }
        GrafoMatriz grafo = new GrafoMatriz(numVertices);

        System.out.println("\n--- Ingreso de Vértices ---");
        for (int i = 0; i < numVertices; i++) {
            while (true) {
                System.out.print("Ingrese el nombre para el vértice " + (i + 1) + ": ");
                String vertice = scanner.nextLine().trim();
                if (!vertice.isEmpty() && !grafo.getVertices().contains(vertice)) {
                    grafo.agregarVertice(vertice);
                    break;
                } else if (vertice.isEmpty()) {
                    System.out.println("El nombre del vértice no puede estar vacío.");
                } else {
                    System.out.println("Ese vértice ya existe. Por favor, ingrese un nombre único.");
                }
            }
        }
        System.out.println("\n--- Ingreso de Relaciones (Aristas) ---");
        System.out.println("Ingrese las relaciones entre los vértices. Escriba 'fin' para terminar.");
        System.out.println("Ejemplo: VerticeA VerticeB");

        while (true) {
            System.out.print("Ingrese una relación (origen destino) o 'fin': ");
            String entrada = scanner.nextLine().trim();
            if (entrada.equalsIgnoreCase("fin")) {
                break;
            }
            String[] partes = entrada.split("\\s+");
            if (partes.length == 2) {
                String inputOrigen = partes[0].trim();
                String inputDestino = partes[1].trim();
                String realOrigen = null;
                String realDestino = null;
                for (String v : grafo.getVertices()) {
                    if (realOrigen == null && v.equalsIgnoreCase(inputOrigen)) {
                        realOrigen = v;
                    }
                    if (realDestino == null && v.equalsIgnoreCase(inputDestino)) {
                        realDestino = v;
                    }
                    if (realOrigen != null && realDestino != null) {
                        break;
                    }
                }
                if (realOrigen != null && realDestino != null) {
                    grafo.agregarArista(realOrigen, realDestino);
                } else {
                    System.out.println("Uno o ambos vértices ingresados no existen. Asegúrese de usar los nombres correctos.");
                }
            } else {
                System.out.println("Formato incorrecto. Por favor, ingrese dos vértices separados por un espacio.");
            }
        }
        grafo.mostrarMatrizAdyacencia();
        scanner.close();
    }
}