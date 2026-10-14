
/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase en Java llamada ColaCircularEnteros utilizando un
 * arreglo int[] datos y las variables front, rear y cantidad. Implementa
 * el avance circular (indice + 1) % capacidad en enqueue y dequeue.
 * Verifica isFull() para lanzar Overflow en enqueue y isEmpty() para lanzar
 * Underflow en dequeue y front. En main, llena la cola, desencola dos
 * elementos y agrega dos nuevos para reutilizar los índices 0 y 1. Mide
 * el tiempo con System.nanoTime() y explica cómo se detecta si está llena
 * o vacía."
 *
 * Ajuste del prompt:
 * La clase pública se llamó ejercicio10_4 para coincidir con el archivo;
 * ColaCircularEnteros quedó como clase auxiliar en el mismo archivo.
 *
 * Prueba de ejecución:
 * Con capacidad 4 y los valores 10, 20, 30, 40, luego de retirar dos y
 * agregar 50 y 60, se espera:
 * Arreglo: [50, 60, 30, 40]
 * front=2, rear=1, cantidad=4
 * Elementos FIFO: 30, 40, 50, 60
 * El tiempo en nanosegundos varía en cada ejecución.
 */
import java.util.Arrays;

public class ejercicio10_4 {

    public static void main(String[] args) {
        ColaCircularEnteros cola = new ColaCircularEnteros(4);

        long inicio = System.nanoTime();

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);
        cola.enqueue(40);

        cola.dequeue();
        cola.dequeue();

        // Al avanzar circularmente, los nuevos elementos reutilizan los
        // índices 0 y 1 que quedaron disponibles.
        cola.enqueue(50);
        cola.enqueue(60);

        long tiempoTranscurrido = System.nanoTime() - inicio;

        cola.mostrarEstado();
        System.out.println("Tiempo de operaciones: "
                + tiempoTranscurrido + " ns");

        System.out.println("Elementos en orden FIFO:");
        while (!cola.isEmpty()) {
            System.out.println(cola.dequeue());
        }
    }
}

/**
 * Cola circular de enteros respaldada por un arreglo de capacidad fija.
 *
 * <p>La variable {@code cantidad} distingue una cola vacía de una llena.
 * Esto es necesario porque, al completar una vuelta, {@code front} y
 * {@code rear} pueden coincidir en ambos estados.</p>
 */
class ColaCircularEnteros {
    private final int[] datos;
    private final int capacidad;
    private int front;
    private int rear;
    private int cantidad;

    public ColaCircularEnteros(int capacidad) {
        // Evita crear una cola sin posiciones válidas para almacenar datos.
        if (capacidad <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero.");
        }

        this.datos = new int[capacidad];
        this.capacidad = capacidad;
        this.front = 0;
        this.rear = -1;
        this.cantidad = 0;
    }

    public void enqueue(int valor) {
        // Control de frontera: no insertar si ya se ocuparon todas las celdas.
        if (isFull()) {
            throw new IllegalStateException("Overflow: Cola llena");
        }

        /*
         * Avanza rear una posición. El módulo hace que, al alcanzar capacidad,
         * el índice vuelva a 0 y se reutilicen las celdas liberadas.
         */
        rear = (rear + 1) % capacidad;
        datos[rear] = valor;
        cantidad++;
    }

    public int dequeue() {
        // Control de frontera: no retirar ni leer una celda si no hay elementos.
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        int valor = datos[front];

        // El mismo avance modular mueve front y lo mantiene dentro del arreglo.
        front = (front + 1) % capacidad;
        cantidad--;
        return valor;
    }

    public int front() {
        // Impide consultar el frente de una cola vacía.
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        return datos[front];
    }

    public boolean isEmpty() {
        return cantidad == 0;
    }

    public boolean isFull() {
        return cantidad == capacidad;
    }

    public int size() {
        return cantidad;
    }

    public void mostrarEstado() {
        System.out.println("Arreglo: " + Arrays.toString(datos));
        System.out.println("front=" + front + ", rear=" + rear
                + ", cantidad=" + cantidad);
    }
}