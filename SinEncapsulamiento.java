public class PerroSinEncapsulamiento {
    public String nombre;
    public String raza;
    public int edad;

    public PerroSinEncapsulamiento(String nombre, String raza, int edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
    }
    public void ladrar() {
        System.out.println("¡Guau! ¡Guau! Soy " + nombre + " y estoy ladrando!");
    }
    public void presentarse() {
        System.out.println("Soy " + nombre + ", raza " + raza + ", y tengo " + edad + " años.");
    }
    public static void main(String[] args) {
        PerroSinEncapsulamiento perro1 = new PerroSinEncapsulamiento("Max", "Labrador", 3);
        perro1.presentarse();
        perro1.ladrar();
        System.out.println("Acceso directo al nombre: " + perro1.nombre);
        System.out.println("Acceso directo a la raza: " + perro1.raza);
        System.out.println("Acceso directo a la edad: " + perro1.edad);
        perro1.nombre = "Buddy";
        perro1.edad = 5;
        perro1.raza = "Pastor Mexicano";

        System.out.println("After modificación directa: " + perro1.nombre);
        perro1.presentarse();
        perro1.ladrar();

        perro1.edad = -10;
        System.out.println("Age con valor negativo: " + perro1.edad);
    }
}