import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

public class curso3 {

    static class Curso {
        private int idCurso;
        private String clave;
        private String nombre;
        private String docente;
        private int cupoMaximo;
        private int numInscritos;

        public Curso(int idCurso, String clave, String nombre, String docente, int cupoMaximo) {
            this.idCurso = idCurso;
            this.clave = clave;
            this.nombre = nombre;
            this.docente = docente;
            this.cupoMaximo = cupoMaximo;
            this.numInscritos = 0;
        }

        public int getIdCurso() { return idCurso; }
        public String getClave() { return clave; }
        public String getNombre() { return nombre; }
        public String getDocente() { return docente; }
        public int getCupoMaximo() { return cupoMaximo; }
        public int getNumInscritos() { return numInscritos; }

        public boolean inscribirEstudiante() {
            if (numInscritos < cupoMaximo) { numInscritos++; return true; }
            return false;
        }

        public boolean darDeBajaEstudiante() {
            if (numInscritos > 0) { numInscritos--; return true; }
            return false;
        }

        public int getLugaresDisponibles() { return cupoMaximo - numInscritos; }

        public double getPorcentajeOcupacion() {
            return ((double) numInscritos / cupoMaximo) * 100;
        }

        @Override
        public String toString() {
            return "  ID      : " + idCurso + "\n" +
                    "  Clave   : " + clave + "\n" +
                    "  Nombre  : " + nombre + "\n" +
                    "  Docente : " + docente + "\n" +
                    "  Cupo    : " + cupoMaximo + " | Inscritos: " + numInscritos + " | Libres: " + getLugaresDisponibles();
        }
    }

    static class NodoSimple {
        Curso dato;
        NodoSimple siguiente;
        NodoSimple(Curso dato) { this.dato = dato; }
    }

    static class ListaSimple {
        NodoSimple cabeza;
        int tamano = 0;

        boolean isEmpty() { return cabeza == null; }

        void agregar(Curso c) {
            NodoSimple nuevo = new NodoSimple(c);
            if (cabeza == null) {
                cabeza = nuevo;
            } else {
                NodoSimple actual = cabeza;
                while (actual.siguiente != null) actual = actual.siguiente;
                actual.siguiente = nuevo;
            }
            tamano++;
        }

        void mostrar() {
            if (isEmpty()) { System.out.println("  (No hay cursos registrados)"); return; }
            NodoSimple actual = cabeza;
            int i = 1;
            while (actual != null) {
                System.out.println("\n  [" + i++ + "]");
                System.out.println(actual.dato);
                System.out.println("  ──────────────────────────────────────");
                actual = actual.siguiente;
            }
        }

        Curso buscarPorClave(String clave) {
            NodoSimple actual = cabeza;
            while (actual != null) {
                if (actual.dato.getClave().equalsIgnoreCase(clave)) return actual.dato;
                actual = actual.siguiente;
            }
            return null;
        }

        boolean existeId(int idCurso) {
            NodoSimple actual = cabeza;
            while (actual != null) {
                if (actual.dato.getIdCurso() == idCurso) return true;
                actual = actual.siguiente;
            }
            return false;
        }

        boolean eliminarPorClave(String clave) {
            if (cabeza == null) return false;
            if (cabeza.dato.getClave().equalsIgnoreCase(clave)) {
                cabeza = cabeza.siguiente;
                tamano--;
                return true;
            }
            NodoSimple actual = cabeza;
            while (actual.siguiente != null) {
                if (actual.siguiente.dato.getClave().equalsIgnoreCase(clave)) {
                    actual.siguiente = actual.siguiente.siguiente;
                    tamano--;
                    return true;
                }
                actual = actual.siguiente;
            }
            return false;
        }

        ArrayList<Curso> aArrayList() {
            ArrayList<Curso> lista = new ArrayList<>();
            NodoSimple actual = cabeza;
            while (actual != null) {
                lista.add(actual.dato);
                actual = actual.siguiente;
            }
            return lista;
        }
    }

    static class NodoArbolCurso {
        Curso dato;
        NodoArbolCurso izquierda, derecha;
        NodoArbolCurso(Curso dato) { this.dato = dato; }
    }

    static class ArbolCursos {
        private NodoArbolCurso raiz;

        boolean isEmpty() { return raiz == null; }

        void insertar(Curso c) { raiz = insertarRecursivo(raiz, c); }

        private NodoArbolCurso insertarRecursivo(NodoArbolCurso nodo, Curso c) {
            if (nodo == null) return new NodoArbolCurso(c);
            if (c.getIdCurso() < nodo.dato.getIdCurso()) {
                nodo.izquierda = insertarRecursivo(nodo.izquierda, c);
            } else if (c.getIdCurso() > nodo.dato.getIdCurso()) {
                nodo.derecha = insertarRecursivo(nodo.derecha, c);
            }
            return nodo;
        }

        Curso buscar(int idCurso) { return buscarRecursivo(raiz, idCurso); }

        private Curso buscarRecursivo(NodoArbolCurso nodo, int idCurso) {
            if (nodo == null) return null;
            if (idCurso == nodo.dato.getIdCurso()) return nodo.dato;
            if (idCurso < nodo.dato.getIdCurso()) return buscarRecursivo(nodo.izquierda, idCurso);
            return buscarRecursivo(nodo.derecha, idCurso);
        }

        void mostrarInorden() {
            if (isEmpty()) {
                System.out.println("  (El árbol no tiene cursos registrados)");
                return;
            }
            inordenRecursivo(raiz);
        }

        private void inordenRecursivo(NodoArbolCurso nodo) {
            if (nodo == null) return;
            inordenRecursivo(nodo.izquierda);
            System.out.println();
            System.out.println(nodo.dato);
            System.out.println("  ──────────────────────────────────────");
            inordenRecursivo(nodo.derecha);
        }
    }

    static class GrafoCursos {
        private Map<Integer, List<int[]>> adyacencia = new HashMap<>();
        private Map<Integer, String> nombres = new HashMap<>();

        boolean isEmpty() { return adyacencia.isEmpty(); }

        boolean existeVertice(int idCurso) { return adyacencia.containsKey(idCurso); }

        void agregarVertice(Curso c) {
            adyacencia.putIfAbsent(c.getIdCurso(), new ArrayList<>());
            nombres.put(c.getIdCurso(), c.getNombre());
        }

        boolean crearRelacion(int origen, int destino, int peso) {
            if (!existeVertice(origen) || !existeVertice(destino)) return false;
            adyacencia.get(origen).add(new int[]{destino, peso});
            return true;
        }

        void mostrarListaAdyacencia() {
            if (isEmpty()) {
                System.out.println("  (No hay cursos registrados en el grafo)");
                return;
            }
            List<Integer> ids = new ArrayList<>(adyacencia.keySet());
            Collections.sort(ids);
            for (int id : ids) {
                StringBuilder sb = new StringBuilder();
                sb.append("  ").append(nombres.get(id)).append(" [id ").append(id).append("] -> ");
                List<int[]> relaciones = adyacencia.get(id);
                if (relaciones.isEmpty()) {
                    sb.append("(sin relaciones)");
                } else {
                    for (int[] r : relaciones) {
                        sb.append(nombres.get(r[0])).append(" (peso ").append(r[1]).append(")  ");
                    }
                }
                System.out.println(sb.toString());
            }
        }

        void mostrarMatrizAdyacencia() {
            if (isEmpty()) {
                System.out.println("  (No hay cursos registrados en el grafo)");
                return;
            }
            List<Integer> ids = new ArrayList<>(adyacencia.keySet());
            Collections.sort(ids);

            System.out.print("        ");
            for (int id : ids) System.out.printf("%6d", id);
            System.out.println();

            for (int fila : ids) {
                System.out.printf("  %5d", fila);
                for (int col : ids) {
                    int peso = 0;
                    for (int[] r : adyacencia.get(fila)) {
                        if (r[0] == col) peso = r[1];
                    }
                    System.out.printf("%6d", peso);
                }
                System.out.println();
            }
        }
    }

    static class Ordenamientos {
        static final int POR_ID = 1;
        static final int POR_CLAVE = 2;
        static final int POR_NOMBRE = 3;
        static final int POR_CUPO = 4;
        static final int POR_INSCRITOS = 5;

        static String nombreCriterio(int criterio) {
            switch (criterio) {
                case POR_ID: return "ID";
                case POR_CLAVE: return "Clave";
                case POR_NOMBRE: return "Nombre";
                case POR_CUPO: return "Cupo máximo";
                case POR_INSCRITOS: return "Número de inscritos";
                default: return "Desconocido";
            }
        }

        private static int comparar(Curso a, Curso b, int criterio) {
            switch (criterio) {
                case POR_ID: return Integer.compare(a.getIdCurso(), b.getIdCurso());
                case POR_CLAVE: return a.getClave().compareTo(b.getClave());
                case POR_NOMBRE: return a.getNombre().compareTo(b.getNombre());
                case POR_CUPO: return Integer.compare(a.getCupoMaximo(), b.getCupoMaximo());
                case POR_INSCRITOS: return Integer.compare(a.getNumInscritos(), b.getNumInscritos());
                default: return 0;
            }
        }

        private static void intercambiar(List<Curso> lista, int i, int j) {
            Curso temp = lista.get(i);
            lista.set(i, lista.get(j));
            lista.set(j, temp);
        }

        static void bubbleSortDirecto(List<Curso> lista, int criterio) {
            int n = lista.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (comparar(lista.get(j), lista.get(j + 1), criterio) > 0) {
                        intercambiar(lista, j, j + 1);
                    }
                }
            }
        }

        static void bubbleSortInverso(List<Curso> lista, int criterio) {
            int n = lista.size();
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - 1 - i; j++) {
                    if (comparar(lista.get(j), lista.get(j + 1), criterio) < 0) {
                        intercambiar(lista, j, j + 1);
                    }
                }
            }
        }

        static void insercionDirecta(List<Curso> lista, int criterio) {
            int n = lista.size();
            for (int i = 1; i < n; i++) {
                Curso actual = lista.get(i);
                int j = i - 1;
                while (j >= 0 && comparar(lista.get(j), actual, criterio) > 0) {
                    lista.set(j + 1, lista.get(j));
                    j--;
                }
                lista.set(j + 1, actual);
            }
        }
        static void seleccionDirecta(List<Curso> lista, int criterio) {
            int n = lista.size();
            for (int i = 0; i < n - 1; i++) {
                int menor = i;
                for (int j = i + 1; j < n; j++) {
                    if (comparar(lista.get(j), lista.get(menor), criterio) < 0) menor = j;
                }
                if (menor != i) intercambiar(lista, i, menor);
            }
        }
    }
    static class Busquedas {
        private static int pasosSecuencial;
        private static int pasosBinaria;

        static int getPasosSecuencial() { return pasosSecuencial; }
        static int getPasosBinaria() { return pasosBinaria; }

        private static int comparar(Curso c, int criterio, Object valor) {
            switch (criterio) {
                case Ordenamientos.POR_ID: return Integer.compare(c.getIdCurso(), (Integer) valor);
                case Ordenamientos.POR_CLAVE: return c.getClave().compareTo((String) valor);
                case Ordenamientos.POR_NOMBRE: return c.getNombre().compareTo((String) valor);
                case Ordenamientos.POR_CUPO: return Integer.compare(c.getCupoMaximo(), (Integer) valor);
                case Ordenamientos.POR_INSCRITOS: return Integer.compare(c.getNumInscritos(), (Integer) valor);
                default: return 0;
            }
        }
        static Curso secuencial(List<Curso> lista, int criterio, Object valor) {
            pasosSecuencial = 0;
            for (Curso c : lista) {
                pasosSecuencial++;
                if (comparar(c, criterio, valor) == 0) return c;
            }
            return null;
        }
        static Curso binaria(List<Curso> listaOrdenada, int criterio, Object valor) {
            pasosBinaria = 0;
            int izquierda = 0;
            int derecha = listaOrdenada.size() - 1;

            while (izquierda <= derecha) {
                pasosBinaria++;
                int medio = (izquierda + derecha) / 2;
                int cmp = comparar(listaOrdenada.get(medio), criterio, valor);

                if (cmp == 0) return listaOrdenada.get(medio);
                if (cmp < 0) izquierda = medio + 1;
                else derecha = medio - 1;
            }
            return null;
        }
    }

    static ListaSimple listaCursos = new ListaSimple();
    static ArbolCursos arbol = new ArbolCursos();
    static GrafoCursos grafo = new GrafoCursos();
    static Stack<String> historial = new Stack<>();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE CURSOS – UTC 3.0  ║");
        System.out.println("║   Estructura de Datos  |  Parcial 3       ║");
        System.out.println("╚══════════════════════════════════════════╝");

        int opcion;
        do {
            System.out.println("\n══════════════════════════════════════════");
            System.out.println("  MENÚ PRINCIPAL");
            System.out.println("══════════════════════════════════════════");
            System.out.println("  1.  Agregar curso");
            System.out.println("  2.  Mostrar cursos");
            System.out.println("  3.  Eliminar curso");
            System.out.println("  4.  Inscribir estudiante");
            System.out.println("  5.  Dar de baja estudiante");
            System.out.println("  6.  Insertar cursos en árbol binario");
            System.out.println("  7.  Buscar curso en árbol binario");
            System.out.println("  8.  Mostrar recorrido inorden del árbol");
            System.out.println("  9.  Crear relación entre cursos (grafo)");
            System.out.println("  10. Mostrar grafo (matriz y lista de adyacencia)");
            System.out.println("  11. Ordenar cursos con Bubble Sort directo");
            System.out.println("  12. Ordenar cursos con Bubble Sort inverso");
            System.out.println("  13. Ordenar cursos con inserción directa");
            System.out.println("  14. Ordenar cursos con selección directa");
            System.out.println("  15. Búsqueda secuencial");
            System.out.println("  16. Búsqueda binaria");
            System.out.println("  17. Mostrar historial de acciones");
            System.out.println("  18. Salir");
            System.out.println("══════════════════════════════════════════");
            System.out.print("  Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:  agregarCurso();               break;
                case 2:  mostrarCursos();               break;
                case 3:  eliminarCurso();               break;
                case 4:  inscribirEstudiante();         break;
                case 5:  darDeBajaEstudiante();         break;
                case 6:  insertarEnArbol();             break;
                case 7:  buscarEnArbol();               break;
                case 8:  mostrarInordenArbol();         break;
                case 9:  crearRelacionGrafo();          break;
                case 10: mostrarGrafo();                break;
                case 11: ordenar(1);                    break;
                case 12: ordenar(2);                    break;
                case 13: ordenar(3);                    break;
                case 14: ordenar(4);                    break;
                case 15: busquedaSecuencial();          break;
                case 16: busquedaBinaria();             break;
                case 17: mostrarHistorial();            break;
                case 18: System.out.println("\n  ¡Hasta luego!\n"); break;
                default: System.out.println("\n  [!] Opción no válida.");
            }
        } while (opcion != 18);

        scanner.close();
    }

    static void agregarCurso() {
        System.out.println("\n── AGREGAR CURSO ──────────────────────────");
        System.out.print("  ID del curso: ");
        int id = leerEntero();
        if (listaCursos.existeId(id)) {
            System.out.println("  [!] Ya existe un curso con ese ID.");
            return;
        }

        System.out.print("  Clave   : ");
        String clave = scanner.nextLine().trim().toUpperCase();
        if (clave.isEmpty()) {
            System.out.println("  [!] La clave no puede estar vacía.");
            return;
        }
        if (listaCursos.buscarPorClave(clave) != null) {
            System.out.println("  [!] Ya existe un curso con esa clave.");
            return;
        }

        System.out.print("  Nombre  : ");
        String nombre = scanner.nextLine().trim();

        System.out.print("  Docente : ");
        String docente = scanner.nextLine().trim();

        System.out.print("  Cupo máximo: ");
        int cupo = leerEnteroPositivo();

        Curso c = new Curso(id, clave, nombre, docente, cupo);
        listaCursos.agregar(c);
        grafo.agregarVertice(c);
        historial.push("Se agregó el curso " + nombre + " [" + clave + ", id " + id + "]");
        System.out.println("  ✔ Curso agregado exitosamente.");
    }

    static void mostrarCursos() {
        System.out.println("\n── LISTA DE CURSOS ─────────────────────────");
        listaCursos.mostrar();
    }

    static void eliminarCurso() {
        System.out.println("\n── ELIMINAR CURSO ─────────────────────────");
        System.out.print("  Clave del curso a eliminar: ");
        String clave = scanner.nextLine().trim().toUpperCase();

        Curso c = listaCursos.buscarPorClave(clave);
        if (c == null) { System.out.println("  [!] Curso no encontrado."); return; }

        System.out.print("  ¿Confirmar eliminación de \"" + c.getNombre() + "\"? (s/n): ");
        String conf = scanner.nextLine().trim().toLowerCase();

        if (conf.equals("s")) {
            listaCursos.eliminarPorClave(clave);
            historial.push("Se eliminó el curso " + c.getNombre() + " [" + clave + "]");
            System.out.println("  ✔ Curso eliminado.");
        } else {
            System.out.println("  Operación cancelada.");
        }
    }

    static void inscribirEstudiante() {
        System.out.println("\n── INSCRIBIR ESTUDIANTE ───────────────────");
        if (listaCursos.isEmpty()) { System.out.println("  [!] No hay cursos registrados."); return; }

        System.out.print("  Clave del curso: ");
        String clave = scanner.nextLine().trim().toUpperCase();
        Curso c = listaCursos.buscarPorClave(clave);
        if (c == null) { System.out.println("  [!] Curso no encontrado."); return; }

        if (c.getLugaresDisponibles() == 0) {
            System.out.println("  [!] El curso está lleno (cupo: " + c.getCupoMaximo() + ")");
            return;
        }

        c.inscribirEstudiante();
        historial.push("Se inscribió un estudiante en " + c.getNombre() + " [" + clave + "]");
        System.out.println("  ✔ Estudiante inscrito.");
        System.out.println("    Inscritos: " + c.getNumInscritos() + " / " + c.getCupoMaximo()
                + "  |  Libres: " + c.getLugaresDisponibles());
    }

    static void darDeBajaEstudiante() {
        System.out.println("\n── DAR DE BAJA ESTUDIANTE ─────────────────");
        if (listaCursos.isEmpty()) { System.out.println("  [!] No hay cursos registrados."); return; }

        System.out.print("  Clave del curso: ");
        String clave = scanner.nextLine().trim().toUpperCase();
        Curso c = listaCursos.buscarPorClave(clave);
        if (c == null) { System.out.println("  [!] Curso no encontrado."); return; }

        if (c.getNumInscritos() == 0) {
            System.out.println("  [!] El curso no tiene estudiantes inscritos.");
            return;
        }

        c.darDeBajaEstudiante();
        historial.push("Se dio de baja un estudiante en " + c.getNombre() + " [" + clave + "]");
        System.out.println("  ✔ Baja registrada.");
        System.out.println("    Inscritos: " + c.getNumInscritos() + " / " + c.getCupoMaximo()
                + "  |  Libres: " + c.getLugaresDisponibles());
    }

    static void insertarEnArbol() {
        System.out.println("\n── INSERTAR CURSOS EN ÁRBOL BINARIO ───────");
        if (listaCursos.isEmpty()) { System.out.println("  [!] No hay cursos registrados."); return; }

        for (Curso c : listaCursos.aArrayList()) {
            arbol.insertar(c);
        }
        historial.push("Se insertaron los cursos en el árbol binario");
        System.out.println("  ✔ Cursos insertados en el árbol.");
    }

    static void buscarEnArbol() {
        System.out.println("\n── BUSCAR CURSO EN ÁRBOL BINARIO ──────────");
        if (arbol.isEmpty()) { System.out.println("  [!] El árbol está vacío. Inserte primero (opción 6)."); return; }

        System.out.print("  ID del curso a buscar: ");
        int id = leerEntero();

        Curso c = arbol.buscar(id);
        if (c != null) {
            System.out.println("\n  Curso encontrado:\n" + c);
            historial.push("Se buscó en el árbol el curso con id " + id);
        } else {
            System.out.println("  [!] No se encontró un curso con ID: " + id);
        }
    }

    static void mostrarInordenArbol() {
        System.out.println("\n── RECORRIDO INORDEN DEL ÁRBOL ────────────");
        arbol.mostrarInorden();
    }

    static void crearRelacionGrafo() {
        System.out.println("\n── CREAR RELACIÓN ENTRE CURSOS (GRAFO) ────");
        if (listaCursos.isEmpty()) { System.out.println("  [!] No hay cursos registrados."); return; }

        System.out.print("  ID del curso origen (prerrequisito): ");
        int origen = leerEntero();
        System.out.print("  ID del curso destino: ");
        int destino = leerEntero();
        System.out.print("  Peso de la relación (dificultad/prioridad): ");
        int peso = leerEntero();

        boolean creada = grafo.crearRelacion(origen, destino, peso);
        if (creada) {
            historial.push("Se creó una relación entre los cursos " + origen + " -> " + destino);
            System.out.println("  ✔ Relación creada.");
        } else {
            System.out.println("  [!] Uno de los IDs no existe en el grafo.");
        }
    }
    static void mostrarGrafo() {
        System.out.println("\n── LISTA DE ADYACENCIA ────────────────────");
        grafo.mostrarListaAdyacencia();
        System.out.println("\n── MATRIZ DE ADYACENCIA ───────────────────");
        grafo.mostrarMatrizAdyacencia();
    }
    static int elegirCriterio() {
        System.out.println("  Ordenar por: 1.ID  2.Clave  3.Nombre  4.Cupo  5.Inscritos");
        System.out.print("  Criterio: ");
        return leerEntero();
    }
    static void ordenar(int metodo) {
        System.out.println("\n── ORDENAR CURSOS ──────────────────────────");
        if (listaCursos.isEmpty()) { System.out.println("  [!] No hay cursos registrados."); return; }
        int criterio = elegirCriterio();
        ArrayList<Curso> cursos = listaCursos.aArrayList();
        String metodoNombre = "";
        switch (metodo) {
            case 1: Ordenamientos.bubbleSortDirecto(cursos, criterio); metodoNombre = "Bubble Sort directo"; break;
            case 2: Ordenamientos.bubbleSortInverso(cursos, criterio); metodoNombre = "Bubble Sort inverso"; break;
            case 3: Ordenamientos.insercionDirecta(cursos, criterio); metodoNombre = "Inserción directa"; break;
            case 4: Ordenamientos.seleccionDirecta(cursos, criterio); metodoNombre = "Selección directa"; break;
        }
        System.out.println("\n  Resultado (" + metodoNombre + ", criterio: " + Ordenamientos.nombreCriterio(criterio) + "):");
        for (Curso c : cursos) {
            System.out.println();
            System.out.println(c);
        }
        historial.push("Se ordenaron los cursos con " + metodoNombre + " por " + Ordenamientos.nombreCriterio(criterio));
    }
    static Object leerValorBusqueda(int criterio) {
        if (criterio == Ordenamientos.POR_CLAVE || criterio == Ordenamientos.POR_NOMBRE) {
            System.out.print("  Valor a buscar (texto): ");
            return scanner.nextLine().trim();
        } else {
            System.out.print("  Valor a buscar (número): ");
            return leerEntero();
        }
    }
    static void busquedaSecuencial() {
        System.out.println("\n── BÚSQUEDA SECUENCIAL ────────────────────");
        if (listaCursos.isEmpty()) { System.out.println("  [!] No hay cursos registrados."); return; }
        int criterio = elegirCriterio();
        Object valor = leerValorBusqueda(criterio);
        ArrayList<Curso> cursos = listaCursos.aArrayList();
        Curso encontrado = Busquedas.secuencial(cursos, criterio, valor);
        System.out.println("\n  Pasos realizados: " + Busquedas.getPasosSecuencial());
        if (encontrado != null) {
            System.out.println("  Curso encontrado:\n" + encontrado);
        } else {
            System.out.println("  [!] No se encontró ningún curso con ese valor.");
        }
        historial.push("Búsqueda secuencial (" + Busquedas.getPasosSecuencial() + " pasos)");
    }
    static void busquedaBinaria() {
        System.out.println("\n── BÚSQUEDA BINARIA ───────────────────────");
        if (listaCursos.isEmpty()) { System.out.println("  [!] No hay cursos registrados."); return; }
        int criterio = elegirCriterio();
        ArrayList<Curso> cursos = listaCursos.aArrayList();
        Ordenamientos.insercionDirecta(cursos, criterio);
        System.out.println("  (Cursos ordenados automáticamente por " + Ordenamientos.nombreCriterio(criterio) + " antes de buscar)");
        Object valor = leerValorBusqueda(criterio);
        Curso encontrado = Busquedas.binaria(cursos, criterio, valor);
        System.out.println("\n  Pasos realizados: " + Busquedas.getPasosBinaria());
        if (encontrado != null) {
            System.out.println("  Curso encontrado:\n" + encontrado);
        } else {
            System.out.println("  [!] No se encontró ningún curso con ese valor.");
        }
        System.out.println("\n  [Funcionalidad extra] Comparación de pasos:");
        System.out.println("    Búsqueda secuencial habría tomado hasta " + cursos.size() + " pasos en el peor caso.");
        System.out.println("    Búsqueda binaria tomó " + Busquedas.getPasosBinaria() + " pasos.");

        historial.push("Búsqueda binaria (" + Busquedas.getPasosBinaria() + " pasos)");
    }
    static void mostrarHistorial() {
        System.out.println("\n── HISTORIAL DE ACCIONES (Pila) ───────────");
        if (historial.isEmpty()) {
            System.out.println("  (Sin acciones registradas)");
            return;
        }
        Stack<String> copia = new Stack<>();
        copia.addAll(historial);
        int total = copia.size();
        System.out.println("  Total de acciones: " + total + "\n");
        int i = total;
        while (!copia.isEmpty()) {
            System.out.println("  [" + i-- + "] " + copia.pop());
        }
    }
    static int leerEntero() {
        while (true) {
            try {
                return Integer.parseInt(scanner.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.print("  [!] Ingrese un número válido: ");
            }
        }
    }
    static int leerEnteroPositivo() {
        int v;
        do {
            v = leerEntero();
            if (v <= 0) System.out.print("  [!] Debe ser mayor a 0: ");
        } while (v <= 0);
        return v;
    }
}