public class PerroConEncapsulado {
    private String nombre;
    private String raza;
    private int edad;

    public PerroConEncapsulado(String nombre, String raza, int edad) {
        this.nombre = nombre;
        this.raza = raza;
        this.edad = edad;
    }
    public String getNombre() {
        return nombre;
    }

    public String getRaza() {
        return raza;
    }

    public int getEdad() {
        return edad;
    }
    public void setNombre(String nuevoNombre) {
        this.nombre = nuevoNombre;
    }

    public void setRaza(String nuevaRaza) {
        this.raza = nuevaRaza;
    }

    public void setEdad(int nuevaEdad) {
        if (nuevaEdad >= 0) {
            this.edad = nuevaEdad;
        } else {
            System.out.println("Error: La edad no puede ser negativa");
        }
    }
    public void ladrar() {
        System.out.println("¡Guau! ¡Guau! Soy " + nombre + " y estoy ladrando!");
    }

    public void presentarse() {
        System.out.println("Soy " + nombre + ", raza " + raza + ", y tengo " + edad + " años.");
    }

    public static void main(String[] args) {

        PerroConEncapsulado perro2 = new PerroConEncapsulado("Charlie", "Golden Retriever", 5);
        perro2.presentarse();
        perro2.ladrar();
        System.out.println("Acceso mediante getNombre(): " + perro2.getNombre());
        perro2.setNombre("Cooper");
        System.out.println("After modificación con setNombre(): " + perro2.getNombre());
        System.out.println("Test con edad negativa:");
        perro2.setEdad(-2);

        perro2.setEdad(6);
        System.out.println("After setEdad(6): " + perro2.getEdad());

        perro2.presentarse();
    }
}