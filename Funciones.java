public class EjerciciosFunciones {

    // 1. Función que muestra "¡Hola amigo!"
    public static void saludoAmigo() {
        System.out.println("¡Hola amigo!");
    }

    // 2. Función que recibe un nombre y muestra el saludo personalizado
    public static void saludoPersonalizado(String nombre) {
        System.out.println("¡hola " + nombre + "!");
    }

    // 3. Función que calcula el factorial de un número entero positivo
    public static long factorial(int n) {
        if (n < 0) {
            throw new IllegalArgumentException("El número debe ser positivo");
        }
        long resultado = 1;
        for (int i = 2; i <= n; i++) {
            resultado *= i;
        }
        return resultado;
    }

    // 4. Función que calcula el total de factura con IVA (por defecto 21%)
    public static double calcularTotalFactura(double cantidadSinIva, Double porcentajeIva) {
        double iva = (porcentajeIva == null) ? 21.0 : porcentajeIva;
        return cantidadSinIva * (1 + iva / 100);
    }

    // Sobrecarga para llamar sin pasar el IVA (usa 21% por defecto)
    public static double calcularTotalFactura(double cantidadSinIva) {
        return calcularTotalFactura(cantidadSinIva, 21.0);
    }

    // 5. Función que calcula el área de un círculo
    public static double areaCirculo(double radio) {
        return Math.PI * radio * radio;
    }

    // 6. Función que calcula el volumen de un cilindro usando areaCirculo
    public static double volumenCilindro(double radio, double altura) {
        return areaCirculo(radio) * altura;
    }

    // Método principal para probar todas las funciones
    public static void main(String[] args) {
        // Prueba función 1
        saludoAmigo();  // ¡Hola amigo!

        // Prueba función 2
        saludoPersonalizado("Carlos");  // ¡hola Carlos!

        // Prueba función 3
        System.out.println("Factorial de 5: " + factorial(5));  // 120

        // Prueba función 4 con IVA personalizado
        System.out.println("Total con 10% IVA: " + calcularTotalFactura(100, 10.0));  // 110.0

        // Prueba función 4 sin IVA (usa 21% por defecto)
        System.out.println("Total con IVA por defecto: " + calcularTotalFactura(100));  // 121.0

        // Prueba función 5
        System.out.println("Área de círculo (r=3): " + areaCirculo(3));  // ~28.27

        // Prueba función 6
        System.out.println("Volumen cilindro (r=3, h=5): " + volumenCilindro(3, 5));  // ~141.37
    }
}