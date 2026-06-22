import java.util.ArrayList;
import java.util.Random;
import java.util.Stack;
import java.util.Scanner;

public class Curso {

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

    static ArrayList<curso> listaCursos = new ArrayList<>();
    static Stack<String> historial = new Stack<>();
    static Scanner scanner = new Scanner(System.in);
    static Random random = new Random();

    public static void main(String[] args) {
        System.out.println("╔══════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE GESTIÓN DE CURSOS – UTC     ║");
        System.out.println("║   Estructura de Datos  |  Parcial 1      ║");
        System.out.println("╚══════════════════════════════════════════╝");

        int opcion;
        do {
            System.out.println("\n══════════════════════════════════════════");
            System.out.println("  MENÚ PRINCIPAL");
            System.out.println("══════════════════════════════════════════");
            System.out.println("  1. Agregar curso");
            System.out.println("  2. Mostrar cursos");
            System.out.println("  3. Buscar curso por clave");
            System.out.println("  4. Inscribir estudiante");
            System.out.println("  5. Dar de baja estudiante (aleatorio)");
            System.out.println("  6. Eliminar curso");
            System.out.println("  7. Mostrar historial de acciones");
            System.out.println("  8. Estadísticas [Funcionalidad extra]");
            System.out.println("  9. Salir");
            System.out.println("══════════════════════════════════════════");
            System.out.print("  Seleccione una opción: ");

            opcion = leerEntero();

            switch (opcion) {
                case 1:  agregarCurso();                   break;
                case 2:  mostrarCursos();                  break;
                case 3:  buscarCursoPorClave();            break;
                case 4:  inscribirEstudiante();            break;
                case 5:  darDeBajaEstudianteRandom();      break;
                case 6:  eliminarCurso();                  break;
                case 7:  mostrarHistorial();               break;
                case 8:  mostrarEstadisticas();            break;
                case 9:  System.out.println("\n  ¡Hasta luego!\n"); break;
                default: System.out.println("\n  [!] Opción no válida.");
            }
        } while (opcion != 9);

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
        if (buscarCurso(clave) != null) {
            System.out.println("  [!] Ya existe un curso con esa clave.");
            return;
        }

        System.out.print("  Nombre  : ");
        String nombre = scanner.nextLine().trim();

        System.out.print("  Docente : ");
        String docente = scanner.nextLine().trim();

        System.out.print("  Cupo máximo: ");
        int cupo = leerEnteroPositivo();

        listaCursos.add(new curso(clave, nombre, docente, cupo));
        historial.push("Se agregó el curso " + nombre + " [" + clave + "]");
        System.out.println("  ✔ Curso agregado exitosamente.");
    }

    static void mostrarCursos() {
        System.out.println("\n── LISTA DE CURSOS ────────────────────────");
        if (listaCursos.isEmpty()) {
            System.out.println("  (No hay cursos registrados)");
            return;
        }
        for (int i = 0; i < listaCursos.size(); i++) {
            System.out.println("\n  [" + (i + 1) + "]");
            System.out.println(listaCursos.get(i));
            System.out.println("  ──────────────────────────────────────");
        }
    }

    static void buscarCursoPorClave() {
        System.out.println("\n── BUSCAR CURSO ───────────────────────────");
        System.out.print("  Ingrese la clave: ");
        String clave = scanner.nextLine().trim().toUpperCase();

        curso c = buscarCurso(clave);
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

        if (modo == 1) {
            ArrayList<curso> disponibles = new ArrayList<>();
            for (curso c : listaCursos) {
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
            for (int i = 0; i < listaCursos.size(); i++) {
                curso c = listaCursos.get(i);
                System.out.printf("  [%d] %-20s | Libres: %d / %d%n",
                        i + 1, c.getNombre(), c.getLugaresDisponibles(), c.getCupoMaximo());
            }
            System.out.print("\n  Seleccione número de curso: ");
            int idx = leerEntero();
            if (idx < 1 || idx > listaCursos.size()) {
                System.out.println("  [!] Número de curso inválido.");
                return;
            }
            curso seleccionado = listaCursos.get(idx - 1);
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
        for (curso c : listaCursos) {
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

        curso c = buscarCurso(clave);
        if (c == null) { System.out.println("  [!] Curso no encontrado."); return; }

        System.out.print("  ¿Confirmar eliminación de \"" + c.getNombre() + "\"? (s/n): ");
        String conf = scanner.nextLine().trim().toLowerCase();

        if (conf.equals("s")) {
            listaCursos.remove(c);
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
        if (listaCursos.isEmpty()) {
            System.out.println("  (No hay cursos para analizar)");
            return;
        }

        int totalInscritos = 0, totalCupo = 0;
        curso masLleno = listaCursos.get(0);
        curso masVacio = listaCursos.get(0);

        for (curso c : listaCursos) {
            totalInscritos += c.getNumInscritos();
            totalCupo += c.getCupoMaximo();
            if (c.getPorcentajeOcupacion() > masLleno.getPorcentajeOcupacion()) masLleno = c;
            if (c.getPorcentajeOcupacion() < masVacio.getPorcentajeOcupacion()) masVacio = c;
        }

        double promedio = totalCupo > 0 ? ((double) totalInscritos / totalCupo) * 100 : 0;

        System.out.println("  Total de cursos       : " + listaCursos.size());
        System.out.println("  Total de inscritos    : " + totalInscritos);
        System.out.println("  Capacidad total       : " + totalCupo);
        System.out.printf ("  Ocupación promedio    : %.1f%%%n", promedio);
        System.out.printf ("  Curso más ocupado     : %s (%.1f%%)%n", masLleno.getNombre(), masLleno.getPorcentajeOcupacion());
        System.out.printf ("  Curso menos ocupado   : %s (%.1f%%)%n", masVacio.getNombre(), masVacio.getPorcentajeOcupacion());

        System.out.println("\n  ── Ranking por ocupación ──────────────");
        ArrayList<curso> ordenados = new ArrayList<>(listaCursos);
        ordenados.sort((a, b) -> Double.compare(b.getPorcentajeOcupacion(), a.getPorcentajeOcupacion()));

        for (int i = 0; i < ordenados.size(); i++) {
            curso c = ordenados.get(i);
            int barras = (int)(c.getPorcentajeOcupacion() / 5);
            String barra = "█".repeat(barras) + "░".repeat(20 - barras);
            System.out.printf("  %2d. %-20s [%s] %5.1f%%%n",
                    i + 1, c.getNombre(), barra, c.getPorcentajeOcupacion());
        }

        graficaBarras(ordenados);

        historial.push("Se consultaron las estadísticas del sistema");
    }

    static void graficaBarras(ArrayList<curso> ordenados) {
        final int ALTURA   = 10;
        final int ANCHO_BAR = 6;

        System.out.println("\n  ── Gráfica de ocupación (%) ───────────");

        double[][] columnas = new double[ordenados.size()][2];
        for (int i = 0; i < ordenados.size(); i++) {
            columnas[i][0] = ordenados.get(i).getPorcentajeOcupacion();
            columnas[i][1] = i;
        }

        for (int fila = ALTURA; fila >= 1; fila--) {
            double umbral = (fila - 1) * (100.0 / ALTURA);
            System.out.printf("  %3.0f%% |", umbral + (100.0 / ALTURA));
            for (double[] col : columnas) {
                String bloque = col[0] >= umbral + (100.0 / ALTURA) ? "████" : "    ";
                System.out.printf(" %-" + ANCHO_BAR + "s", bloque);
            }
            System.out.println();
        }

        System.out.print("     +");
        for (int i = 0; i < ordenados.size(); i++) {
            System.out.print("-------");
        }
        System.out.println();

        System.out.print("      ");
        for (curso c : ordenados) {
            String etiqueta = c.getClave().length() > 6 ? c.getClave().substring(0, 6) : c.getClave();
            System.out.printf(" %-7s", etiqueta);
        }
        System.out.println();

        System.out.print("      ");
        for (curso c : ordenados) {
            System.out.printf(" %5.1f%%", c.getPorcentajeOcupacion());
        }
        System.out.println("\n");
    }

    static curso buscarCurso(String clave) {
        for (curso c : listaCursos) {
            if (c.getClave().equalsIgnoreCase(clave)) return c;
        }
        return null;
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