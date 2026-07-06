# 📊 Cuadro Comparativo — Estructuras de Datos

| Estructura | Cómo trabaja | Uso común | Características | Diferencias |
|------------|--------------|-----------|-----------------|-------------|
| **Arreglo (Array)** | Almacena elementos en posiciones contiguas de memoria, accesibles por índice | Guardar colecciones de tamaño fijo y acceso rápido | Tamaño fijo, acceso directo O(1), inserción/eliminación costosa | Tamaño estático; no puede crecer ni reducirse en tiempo de ejecución |
| **Lista (ArrayList)** | Array dinámico que crece automáticamente | Listas de tamaño variable con acceso frecuente por índice | Tamaño dinámico, acceso O(1), inserción al final eficiente | Como el Array pero con tamaño dinámico; más lento al redimensionarse |
| **Cola (Queue)** | FIFO: el primero en entrar es el primero en salir | Gestión de tareas, colas de impresión, procesos en espera | `enqueue()` al final, `dequeue()` al inicio | Solo permite acceso por los extremos; no se puede acceder al medio |
| **Pila (Stack)** | LIFO: el último en entrar es el primero en salir | Deshacer acciones, llamadas a funciones, navegación | `push()` agrega arriba, `pop()` retira de arriba | A diferencia de la Cola, el acceso es solo por un extremo (tope) |
| **Lista enlazada (LinkedList)** | Nodos conectados por punteros, cada uno apunta al siguiente | Inserciones/eliminaciones frecuentes en cualquier posición | Sin acceso directo por índice, insertar/eliminar es O(1) | Solo recorre en un sentido; cada nodo tiene un único puntero al siguiente |
| **Lista doblemente enlazada** | Cada nodo apunta al siguiente y al anterior | Recorridos en ambas direcciones (ej. navegador, listas de reproducción) | Más memoria por nodo, permite recorrido bidireccional | A diferencia de la lista simple, permite recorrer en ambas direcciones |
