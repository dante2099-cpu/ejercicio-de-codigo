import java.util.ArrayList;

public class BecasEstudiantiles {
    public static void main(String[] args) {
        ArrayList<Double> calificaciones = new ArrayList<>();
        calificaciones.add(95.5); calificaciones.add(88.0); calificaciones.add(72.5);
        calificaciones.add(91.0); calificaciones.add(65.0); calificaciones.add(98.5);
        calificaciones.add(84.0); calificaciones.add(77.5); calificaciones.add(93.0);
        calificaciones.add(69.5);

        ArrayList<Integer> asistencias = new ArrayList<>();
        asistencias.add(95); asistencias.add(85); asistencias.add(70);
        asistencias.add(82); asistencias.add(90); asistencias.add(100);
        asistencias.add(78); asistencias.add(88); asistencias.add(92);
        asistencias.add(65);

        ArrayList<Boolean> concursos = new ArrayList<>();
        concursos.add(false); concursos.add(true); concursos.add(false);
        concursos.add(false); concursos.add(true); concursos.add(false);
        concursos.add(false); concursos.add(true); concursos.add(false);
        concursos.add(false);

        System.out.println("=== BECAS ESTUDIANTILES ===\n");
        int totalBeca = 0;

        for (int i = 0; i < 10; i++) {
            double cal = calificaciones.get(i);
            int asist = asistencias.get(i);
            boolean concur = concursos.get(i);
            boolean beca = obtieneBeca(cal, asist, concur);

            if (beca) totalBeca++;

            String razon = concur ? "concurso" : (cal >= 90 && asist >= 80) ? "cal+asist" : "no cumple";
            System.out.println("Est " + (i+1) + ": Cal=" + cal + " Asist=" + asist +
                    " Beca=" + beca + " (" + razon + ")");
        }

        System.out.println("\n=== RESUMEN ===");
        System.out.println("Total: 10 estudiantes");
        System.out.println("Con beca: " + totalBeca);
        System.out.println("Sin beca: " + (10 - totalBeca));
        System.out.println("Porcentaje: " + totalBeca*10 + "%");
    }

    public static boolean obtieneBeca(double cal, int asist, boolean concurso) {
        if (concurso) return true;
        if (cal >= 90 && asist >= 80) return true;
        return false;
    }
}