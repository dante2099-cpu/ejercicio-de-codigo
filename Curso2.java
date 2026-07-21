import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.Scanner;

public class Curso2 {

    static class curso {
        private String clave;
        private String nombre;
        private String docente;
        private int cupoMaximo;
        private int numInscritos;

        public curso(String clave, String nombre, String docente, int cupoMaximo) {
            this.clave = clave;
            this.nombre = nombre;
            this.docente = docente;
            this.cupoMaximo = cupoMaximo;
            this.numInscritos = 0;
        }

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
            return "  Clave   : " + clave + "\n" +
                    "  Nombre  : " + nombre + "\n" +
                    "  Docente : " + docente + "\n" +
                    "  Cupo    : " + cupoMaximo + " | Inscritos: " + numInscritos + " | Libres: " + getLugaresDisponibles();
        }
    }

    static class NodoSimple {
        curso dato;
        NodoSimple siguiente;
        NodoSimple(curso dato) { this.dato = dato; }
    }

    static class ListaSimple {
        NodoSimple cabeza;
        int tamano = 0;

        boolean isEmpty() { return cabeza == null; }

        void agregar(curso c) {
            NodoSimple nuevo = new NodoSimple(c);
            if (cabeza == null) { cabeza = nuevo; }
            else {
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

        curso buscarPorClave(String clave) {
            NodoSimple actual = cabeza;
            while (actual != null) {
                if (actual.dato.getClave().equalsIgnoreCase(clave)) return actual.dato;
                actual = actual.siguiente;
            }
            return null;
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

        ArrayList<curso> aArrayList() {
            ArrayList<curso> lista = new ArrayList<>();
            NodoSimple actual = cabeza;
            while (actual != null) { lista.add(actual.dato); actual = actual.siguiente; }
            return lista;
        }
    }

    static class NodoDoble {
        curso dato;
        NodoDoble anterior, siguiente;
        NodoDoble(curso dato) { this.dato = dato; }
    }

    static class ListaDoble {
        NodoDoble cabeza, cola;

        boolean isEmpty() { return cabeza == null; }

        void agregarFinal(curso c) {
            NodoDoble nuevo = new NodoDoble(c);
            if (cabeza == null) { cabeza = cola = nuevo; }
            else {
                cola.siguiente = nuevo;
                nuevo.anterior = cola;
                cola = nuevo;
            }
        }

        void mostrarInicioFin() {
            if (isEmpty()) { System.out.println("  (No hay cursos registrados)"); return; }
            NodoDoble actual = cabeza;
            int i = 1;
            while (actual != null) {
                System.out.println("\n  [" + i++ + "]");
                System.out.println(actual.dato);
                actual = actual.siguiente;
            }
        }

        void mostrarFinInicio() {
            if (isEmpty()) { System.out.println("  (No hay cursos registrados)"); return; }
            NodoDoble actual = cola;
            int i = 1;
            while (actual != null) {
                System.out.println("\n  [" + i++ + "]");
                System.out.println(actual.dato);
                actual = actual.anterior;
            }
        }
    }

    static ListaSimple listaCursos = new ListaSimple();
    static Stack<String> historial = new Stack<>();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE CURSOS – UTC 2.0  ║");
        System.out.println("║   Estructura de Datos  |  Parcial 2       ║");
        System.out.println("╚══════════════════════════════════════════╝");

        int opcion;
        do {
            System.out.println("\n══════════════════════════════════════════");
            System.out.println("  MENÚ PRINCIPAL");
            System.out.println("══════════════════════════════════════════");
            System.out.println("  1.  Agregar curso");
            System.out.println("  2.  Mostrar cursos");
            System.out.println("  3.  Buscar curso por clave");
            System.out.println("  4.  Eliminar curso");
            System.out.println("  5.  Inscribir estudiante a curso");
            System.out.println("  6.  Dar de baja estudiante (aleatorio)");
            System.out.println("  7.  Mostrar cursos de inicio a fin");
            System.out.println("  8.  Mostrar cursos de fin a inicio");
            System.out.println("  9.  Navegador de cursos (carrusel)");
            System.out.println("  10. Contar cursos (recursivo)");
            System.out.println("  11. Buscar curso (recursivo)");
            System.out.println("  12. Mostrar historial de acciones");
            System.out.println("  13. Estadísticas [Funcionalidad extra]");
            System.out.println("  14. Salir");
            System.out.println("══════════════════════════════════════════");
            System.out.print("  Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:  agregarCurso();                   break;
                case 2:  mostrarCursos();                  break;
                case 3:  buscarCursoPorClave();            break;
                case 4:  eliminarCurso();                  break;
                case 5:  inscribirEstudiante();            break;
                case 6:  darDeBajaEstudianteRandom();      break;
                case 7:  mostrarInicioFin();                break;
                case 8:  mostrarFinInicio();                break;
                case 9:  navegadorCarrusel();               break;
                case 10: contarCursosMenu();                break;
                case 11: buscarCursoRecursivoMenu();        break;
                case 12: mostrarHistorial();               break;
                case 13: mostrarEstadisticas();            break;
                case 14: System.out.println("\n  ¡Hasta luego!\n"); break;
                default: System.out.println("\n  [!] Opción no válida.");
            }
        } while (opcion != 14);

        scanner.close();
    }

    static void agregarCurso() {
        System.out.println("\n── AGREGAR CURSO ──────────────────────────");
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

        listaCursos.agregar(new curso(clave, nombre, docente, cupo));
        historial.push("Se agregó el curso " + nombre + " [" + clave + "]");
        System.out.println("  ✔ Curso agregado exitosamente.");
    }

    static void mostrarCursos() {
        System.out.println("\n── LISTA DE CURSOS (lista simple) ─────────");
        listaCursos.mostrar();
    }

    static void mostrarInicioFin() {
        System.out.println("\n── CURSOS DE INICIO A FIN (lista doble) ───");
        construirListaDoble().mostrarInicioFin();
    }

    static void mostrarFinInicio() {
        System.out.println("\n── CURSOS DE FIN A INICIO (lista doble) ───");
        construirListaDoble().mostrarFinInicio();
    }

    static ListaDoble construirListaDoble() {
        ListaDoble doble = new ListaDoble();
        NodoSimple actual = listaCursos.cabeza;
        while (actual != null) {
            doble.agregarFinal(actual.dato);
            actual = actual.siguiente;
        }
        return doble;
    }

    static void navegadorCarrusel() {
        System.out.println("\n── NAVEGADOR DE CURSOS (CARRUSEL) ─────────");
        if (listaCursos.isEmpty()) {
            System.out.println("  [!] No hay cursos registrados.");
            return;
        }

        ListaDoble doble = construirListaDoble();
        NodoDoble actual = doble.cabeza;

        int op;
        do {
            System.out.println("\n  Curso actual:");
            System.out.println(actual.dato);
            System.out.println("\n  1. Siguiente");
            System.out.println("  2. Anterior");
            System.out.println("  3. Salir del navegador");
            System.out.print("  Opción: ");
            op = leerEntero();

            switch (op) {
                case 1:
                    if (actual.siguiente != null) actual = actual.siguiente;
                    else System.out.println("  [!] Ya está en el último curso.");
                    break;
                case 2:
                    if (actual.anterior != null) actual = actual.anterior;
                    else System.out.println("  [!] Ya está en el primer curso.");
                    break;
                case 3:
                    System.out.println("  Saliendo del navegador...");
                    break;
                default:
                    System.out.println("  [!] Opción no válida.");
            }
        } while (op != 3);
    }

    static int contarCursosRecursivo(NodoSimple nodo) {
        if (nodo == null) return 0;
        return 1 + contarCursosRecursivo(nodo.siguiente);
    }

    static void contarCursosMenu() {
        System.out.println("\n── CONTAR CURSOS (RECURSIVO) ──────────────");
        System.out.println("  Total de cursos: " + contarCursosRecursivo(listaCursos.cabeza));
    }

    static curso buscarClaveRecursivo(NodoSimple nodo, String clave) {
        if (nodo == null) return null;
        if (nodo.dato.getClave().equalsIgnoreCase(clave)) return nodo.dato;
        return buscarClaveRecursivo(nodo.siguiente, clave);
    }

    static void buscarCursoRecursivoMenu() {
        System.out.println("\n── BUSCAR CURSO (RECURSIVO) ────────────────");
        if (listaCursos.isEmpty()) {
            System.out.println("  [!] No hay cursos registrados.");
            return;
        }
        System.out.print("  Ingrese la clave: ");
        String clave = scanner.nextLine().trim().toUpperCase();

        curso c = buscarClaveRecursivo(listaCursos.cabeza, clave);
        if (c != null) {
            System.out.println("\n  Curso encontrado:\n" + c);
            historial.push("Se consultó (recursivo) el curso " + c.getNombre() + " [" + clave + "]");
        } else {
            System.out.println("  [!] No se encontró el curso: " + clave);
        }
    }

    static void buscarCursoPorClave() {
        System.out.println("\n── BUSCAR CURSO ───────────────────────────");
        System.out.print("  Ingrese la clave: ");
        String clave = scanner.nextLine().trim().toUpperCase();

        curso c = listaCursos.buscarPorClave(clave);
        if (c != null) {
            System.out.println("\n  Curso encontrado:\n" + c);
            historial.push("Se consultó el curso " + c.getNombre() + " [" + clave + "]");
        } else {
            System.out.println("  [!] No se encontró el curso: " + clave);
        }
    }

    static void inscribirEstudiante() {
        System.out.println("\n── INSCRIBIR ESTUDIANTE ───────────────────");

        if (listaCursos.isEmpty()) {
            System.out.println("  [!] No hay cursos registrados.");
            return;
        }

        System.out.println("  ¿Cómo desea inscribir?");
        System.out.println("  1. Aleatorio (curso al azar)");
        System.out.println("  2. Elegir curso manualmente");
        System.out.print("  Opción: ");
        int modo = leerEntero();

        ArrayList<curso> cursos = listaCursos.aArrayList();

        if (modo == 1) {
            ArrayList<curso> disponibles = new ArrayList<>();
            for (curso c : cursos) {
                if (c.getLugaresDisponibles() > 0) disponibles.add(c);
            }
            if (disponibles.isEmpty()) {
                System.out.println("  [!] Todos los cursos están llenos.");
                return;
            }
            curso seleccionado = disponibles.get(random.nextInt(disponibles.size()));
            seleccionado.inscribirEstudiante();
            historial.push("Inscripción aleatoria en " + seleccionado.getNombre()
                    + " [" + seleccionado.getClave() + "]");
            System.out.println("  ✔ Estudiante inscrito aleatoriamente en:");
            System.out.println("    Curso    : " + seleccionado.getNombre()
                    + " [" + seleccionado.getClave() + "]");
            System.out.println("    Inscritos: " + seleccionado.getNumInscritos()
                    + " / " + seleccionado.getCupoMaximo()
                    + "  |  Libres: " + seleccionado.getLugaresDisponibles());

        } else if (modo == 2) {
            System.out.println("\n  Cursos disponibles:\n");
            for (int i = 0; i < cursos.size(); i++) {
                curso c = cursos.get(i);
                System.out.printf("  [%d] %-20s | Libres: %d / %d%n",
                        i + 1, c.getNombre(), c.getLugaresDisponibles(), c.getCupoMaximo());
            }
            System.out.print("\n  Seleccione número de curso: ");
            int idx = leerEntero();
            if (idx < 1 || idx > cursos.size()) {
                System.out.println("  [!] Número de curso inválido.");
                return;
            }
            curso seleccionado = cursos.get(idx - 1);
            if (seleccionado.getLugaresDisponibles() == 0) {
                System.out.println("  [!] El curso está lleno (cupo: " + seleccionado.getCupoMaximo() + ")");
                return;
            }
            System.out.print("  ¿Cuántos estudiantes desea inscribir? ");
            int cantidad = leerEnteroPositivo();
            int inscritos = 0;
            for (int i = 0; i < cantidad; i++) {
                if (!seleccionado.inscribirEstudiante()) break;
                inscritos++;
            }
            historial.push("Se inscribieron " + inscritos + " estudiante(s) en "
                    + seleccionado.getNombre() + " [" + seleccionado.getClave() + "]");
            System.out.println("  ✔ Estudiantes inscritos: " + inscritos);
            if (inscritos < cantidad)
                System.out.println("  [!] Solo se pudieron inscribir " + inscritos + " (cupo lleno).");
            System.out.println("    Curso    : " + seleccionado.getNombre()
                    + " [" + seleccionado.getClave() + "]");
            System.out.println("    Inscritos: " + seleccionado.getNumInscritos()
                    + " / " + seleccionado.getCupoMaximo()
                    + "  |  Libres: " + seleccionado.getLugaresDisponibles());

        } else {
            System.out.println("  [!] Opción no válida.");
        }
    }

    static void darDeBajaEstudianteRandom() {
        System.out.println("\n── DAR DE BAJA ESTUDIANTE (ALEATORIO) ─────");

        if (listaCursos.isEmpty()) {
            System.out.println("  [!] No hay cursos registrados.");
            return;
        }

        ArrayList<curso> conInscritos = new ArrayList<>();
        for (curso c : listaCursos.aArrayList()) {
            if (c.getNumInscritos() > 0) conInscritos.add(c);
        }

        if (conInscritos.isEmpty()) {
            System.out.println("  [!] Ningún curso tiene estudiantes inscritos.");
            return;
        }

        curso seleccionado = conInscritos.get(random.nextInt(conInscritos.size()));
        seleccionado.darDeBajaEstudiante();

        historial.push("Baja aleatoria en " + seleccionado.getNombre()
                + " [" + seleccionado.getClave() + "]");

        System.out.println("  ✔ Baja registrada aleatoriamente en:");
        System.out.println("    Curso    : " + seleccionado.getNombre()
                + " [" + seleccionado.getClave() + "]");
        System.out.println("    Inscritos: " + seleccionado.getNumInscritos()
                + " / " + seleccionado.getCupoMaximo()
                + "  |  Libres: " + seleccionado.getLugaresDisponibles());
    }

    static void eliminarCurso() {
        System.out.println("\n── ELIMINAR CURSO ─────────────────────────");
        System.out.print("  Clave del curso a eliminar: ");
        String clave = scanner.nextLine().trim().toUpperCase();

        curso c = listaCursos.buscarPorClave(clave);
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

    static void mostrarEstadisticas() {
        System.out.println("\n── ESTADÍSTICAS Y REPORTES ────────────────");
        ArrayList<curso> cursos = listaCursos.aArrayList();
        if (cursos.isEmpty()) {
            System.out.println("  (No hay cursos para analizar)");
            return;
        }

        int totalInscritos = 0, totalCupo = 0;
        curso masLleno = cursos.get(0);
        curso masVacio = cursos.get(0);

        for (curso c : cursos) {
            totalInscritos += c.getNumInscritos();
            totalCupo += c.getCupoMaximo();
            if (c.getPorcentajeOcupacion() > masLleno.getPorcentajeOcupacion()) masLleno = c;
            if (c.getPorcentajeOcupacion() < masVacio.getPorcentajeOcupacion()) masVacio = c;
        }

        double promedio = totalCupo > 0 ? ((double) totalInscritos / totalCupo) * 100 : 0;

        System.out.println("  Total de cursos       : " + cursos.size());
        System.out.println("  Total de inscritos    : " + totalInscritos);
        System.out.println("  Capacidad total       : " + totalCupo);
        System.out.printf ("  Ocupación promedio    : %.1f%%%n", promedio);
        System.out.printf ("  Curso más ocupado     : %s (%.1f%%)%n", masLleno.getNombre(), masLleno.getPorcentajeOcupacion());
        System.out.printf ("  Curso menos ocupado   : %s (%.1f%%)%n", masVacio.getNombre(), masVacio.getPorcentajeOcupacion());

        System.out.println("\n  ── Ranking por ocupación ──────────────");
        ArrayList<curso> ordenados = new ArrayList<>(cursos);
        ordenados.sort((a, b) -> Double.compare(b.getPorcentajeOcupacion(), a.getPorcentajeOcupacion()));

        for (int i = 0; i < ordenados.size(); i++) {
            curso c = ordenados.get(i);
            int barras = (int)(c.getPorcentajeOcupacion() / 5);
            String barra = "█".repeat(barras) + "░".repeat(20 - barras);
            System.out.printf("  %2d. %-20s [%s] %5.1f%%%n",
                    i + 1, c.getNombre(), barra, c.getPorcentajeOcupacion());
        }

        historial.push("Se consultaron las estadísticas del sistema");
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