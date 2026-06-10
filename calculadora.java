public class Calculator {
    public static void main(String[] args) {
        double r = Functions.suma(158.0, 204.0);
        System.out.println("Suma: " + r);
        Functions.resta(789, 412);
        Functions.multiplicacion();
        r = Functions.division();
        System.out.println("Division: " + r);
    }
}
class Functions {
    public static double suma(double a, double b) { return a + b; }
    public static void resta(double a, double b) { System.out.println("Resta: " + (a - b)); }
    public static void multiplicacion() { System.out.println("Multiplicacion: " + (12.0 * 5.0)); }
    public static double division() { return 100.0 / 4.0; }
}