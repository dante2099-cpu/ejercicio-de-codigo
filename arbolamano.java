import java.util.Scanner;
import java.util.ArrayList;
import java.util.List;

class Nodo {
    int valor;
    Nodo izquierdo;
    Nodo derecho;

    public Nodo(int valor) {
        this.valor = valor;
        this.izquierdo = null;
        this.derecho = null;
    }
}

class ArbolBinarioCompleto {
    public static void main(String[] args) {
        Nodo raiz = new Nodo(50);
        System.out.println("Insertado: 50 Raíz");

        raiz.izquierdo = new Nodo(30);
        System.out.println("Insertado: 30 a la izquierda, este es el hijo de 50");

        raiz.derecho = new Nodo(70);
        System.out.println("Insertado: 70 a la derecha, este es el hijo de 50");

        raiz.izquierdo.izquierdo = new Nodo(20);
        System.out.println("Insertado: 20 a la izquierda, este es el hijo de 30");

        raiz.izquierdo.derecho = new Nodo(40);
        System.out.println("Insertado: 40 a la derecha, este es el hijo de 30");

        raiz.derecho.izquierdo = new Nodo(60);
        System.out.println("Insertado: 60 a la izquierda, este es el hijo de 70");

        raiz.derecho.derecho = new Nodo(80);
        System.out.println("Insertado: 80 a la derecha, este es el hijo de 70");
    }
}