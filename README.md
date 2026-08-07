# Ejercicio-de-codigo
----------
## Introduccion

### El índice siguiente presenta los títulos de los códigos incluidos en este repositorio. Cada título se muestra en azul y funciona como un enlace que lleva directamente al código correspondiente. Al final del documento, se incluye una tabla con la descripción de lo que hace cada archivo.
---------
#### Indice

[AreayPerimetrodeuncirculo.java](AreayPerimetrodeuncirculo.java)

[Fibonacci.java](Fibonacci.java)

[FizzBuzz.java](FizzBuzz.java)

[Funciones.java](Funciones.java)

[dosvariablesparadosnumerosenteros.java](dosvariablesparadosnumerosenteros.java)

[numerosprimos.java](numerosprimos.java)

[operacinaritmetica.java](operacinaritmetica.java)

[pizza.java](pizza.java)

[arreglos.java](arreglos.java)

[calculadora.java](calculadora.java)

[dosvariablesparadosnumerosenteros.java](dosvariablesparadosnumerosenteros.java)

[numerosprimos.java](numerosprimos.java)

[operacinaritmetica.java](operacinaritmetica.java)

[pizza.java](pizza.java)

[arreglos.java](arreglos.java)

[contraseña.java](contraseña.java)

[calculadora.java](calculadora.java)

[promedio.java](promedio.java)

[ParesImpares.java](ParesImpares.java)

[classFilaPersonas.java](classFilaPersonas.java)

[InvertirPalabra.java](InvertirPalabra.java)

[Cafeteria.java](Cafeteria.java)

[ConEncapsulado.java](ConEncapsulado.java)

[SinEncapsulamiento.java](SinEncapsulamiento.java)

[cursos.java](cursos.java)

---------
| Archivo | Descripción |
|---------|-------------|
| `AreayPerimetrodeuncirculo.java` | Calcula el área y el perímetro de un círculo dado su radio |
| `Fibonacci.java` | Genera la sucesión de Fibonacci donde cada término es la suma de los dos anteriores |
| `FizzBuzz.java` | Programa que imprime Fizz para números divisibles por 3, Buzz para divisibles por 5, y FizzBuzz para divisibles por 15 |
| `Funciones.java` | Define y utiliza funciones o métodos en Java para demostrar la reutilización de código |
| `dosvariablesparadosnumerosenteros.java` | Maneja dos variables para almacenar y operar con dos números enteros |
| `numerosprimos.java` | Determina si un número es primo o genera una lista de números primos dentro de un rango |
| `operacinaritmetica.java` | Realiza operaciones aritméticas básicas entre números |
| `pizza.java` | Programa relacionado con cálculos de pizza como área, costo por porción o cantidad de ingredientes |
| `arreglos.java` | Demuestra el uso de arreglos o arrays en Java incluyendo creación, llenado e iteración sobre elementos |
| `contraseña.java` | Programa de validación de contraseña que verifica longitud, caracteres especiales o compara con una contraseña almacenada |
| `Calculator.java` | Calculadora en Java que utiliza métodos (suma, resta, multiplicación, división) definidos en la clase Functions para realizar operaciones aritméticas |
| `promedio.java` | Calcula el promedio de calificaciones y determina si estudiantes obtienen becas basándose en calificaciones (≥90), asistencias (≥80%) y participación en concursos, usando ArrayList para almacenar datos de 10 estudiantes |
| `ParesImpares.java` | Genera 20 números aleatorios, los almacena en una cola, y separa en dos colas: números pares y números impares mostrando ambos y sus totales |
| `classFilaPersonas.java` | Simula una fila (cola) de personas usando Queue: agrega 5 nombres, muestra quién está al frente, atiende (poll) dos personas y muestra la fila y conteo restante |
| `InvertirPalabra.java` | Pide una palabra por teclado, la invierte usando una pila (Stack), muestra la palabra invertida y verifica si es palíndromo |
| `Cafeteria.java` | Simula el sistema de una cafetería con menú interactivo: cola de clientes (Queue) para llegada/atención, pila (Stack) para pedidos cancelados, opciones para mostrar estado y salir |
| `ConEncapsulado.java` | Clase `PerroConEncapsulado` que demuestra encapsulamiento en Java: atributos privados (nombre, raza, edad), constructor, getters/setters con validación (edad ≥ 0), y métodos ladrar/presentarse |
| `SinEncapsulamiento.java` | Clase `PerroSinEncapsulado` que muestra lo contrario del encapsulamiento: atributos públicos (nombre, raza, edad) accesibles directamente sin validación, sin getters/setters, y métodos ladrar/presentarse |
| `Curso.java` | Sistema de gestión de cursos con menú interactivo: usa `ArrayList` para lista de cursos (clase interna `curso` con atributos privados, getters/setters, inscribir/dar de baja), `Stack` para historial de acciones, búsqueda por clave, inscripciones (aleatoria/manual), bajas aleatorias, eliminación, estadísticas con ranking y gráfica de barras de ocupación |
---------
# Segundo Parcial

## Indice segundo

[Cuadrocomparativo.md](Cuadrocomparativo.md)

[ListaEnlazadaBasica.java](ListaEnlazadaBasica.java)

[busquedayactualizacion.java](busquedayactualizacion.java)

[ListaDoble.java](ListaDoble.java)

[recursivoconfibonacci.java](recursivoconfibonacci.java)

[Curso2.java](Curso2.java)

--------
| Archivo | Descripción |
|--------|-------------|
| `Cuadrocomparativo.md` | Este cuadro comparativo tiene la función de mostrar, de manera clara y ordenada, las diferencias y similitudes entre varias estructuras de datos. Permite identificar rápidamente cómo trabaja cada una, en qué casos se usa, cuáles son sus características principales y qué las hace distintas entre sí. |
| `ListaEnlazadaBasica.java` | Ejemplo básico de una lista enlazada usando una clase interna `Nodo`. Construye una lista manualmente y la recorre para mostrar cómo se conectan los nodos. |
| `busquedayactualizacion.java` | Implementación de búsqueda y actualización dentro de una lista enlazada. Incluye métodos para encontrar un nodo por su dato y modificarlo si existe. |
| `Nodomenu.java` | Es un programa en Java que implementa una lista enlazada simple usando una clase `Nodo`, donde cada nodo guarda un dato (`String`) y una referencia al siguiente nodo (`siguienteNodo`). El programa ofrece un menú interactivo por consola (usando `Scanner`) que se repite en un bucle infinito, permitiendo al usuario: agregar un nodo al final de la lista, buscar un nodo por su dato, actualizar el dato de un nodo existente, eliminar un nodo por su dato, y mostrar todos los nodos de la lista en orden. |
| `ListaDoble.java` | Implementación de una lista doblemente enlazada usando una clase interna NodoDoble con referencias prev y next. Incluye métodos para insertar al final, buscar, actualizar y eliminar un nodo (ajustando ambos enlaces), además de recorrer la lista en ambos sentidos (adelante y atrás). El programa cuenta con un menú interactivo por consola (usando Scanner) que permite al usuario ejecutar todas estas operaciones hasta elegir salir. |
| `recursivoconfibonacci.java` | Programa en Java que calcula la serie de Fibonacci usando recursividad. El método `fibonacci(n)` se llama a sí mismo para obtener cada término, con dos casos base (`n = 0` y `n = 1`). El usuario indica por consola (usando `Scanner`) cuántos términos de la serie desea ver, y el programa los muestra en orden consecutivo separados por comas. |
| `Curso2.java` | Sistema de gestión de cursos (Parcial 2) con lista simple y lista doble enlazadas manualmente, pila para historial de acciones, ArrayList y Random para inscripciones/bajas. Menú interactivo con: agregar/buscar/eliminar curso, inscribir y dar de baja estudiantes (manual o aleatorio), mostrar lista en ambos sentidos, navegador tipo carrusel (nodos doble enlace), conteo y búsqueda recursiva de cursos, historial de acciones (pila) y estadísticas con ranking y gráfica de barras de ocupación. |

---------
# tercer Parcial

## Indice tercer

[GrafoMatriz.java](GrafoMatriz.java)

[arbolamano.java](arbolamano.java)

[arbolcomputadora,java.txt](arbolcomputadora,java.txt)
