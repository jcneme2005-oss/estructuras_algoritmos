/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase ColaCircularEnteros con un arreglo int[] datos y las
 * variables front, rear y cantidad. Implementa enqueue, dequeue, front,
 * isEmpty e isFull con avance circular mediante (indice + 1) % capacidad.
 * En main, llena la cola, desencola dos elementos y agrega dos nuevos para
 * comprobar la reutilización de los índices 0 y 1. Mide el tiempo con
 * System.nanoTime()."
 *
 * Ajustes realizados:
 * La clase pública se llama ejercicio6_4 para coincidir con el archivo y
 * la clase auxiliar se llama ColaEnteros. La prueba actual usa capacidad 3
 * y realiza un dequeue y un enqueue; por eso no coincide exactamente con
 * la prueba inicial de dos dequeue y dos enqueue.
 *
 * Prueba de ejecución esperada con el código actual:
 * Frente consultado: 10
 * Elemento desencolado: 10
 * Tiempo de operaciones: [variable] ns
 * Elementos restantes en orden FIFO:
 * 20
 * 30
 * 40
 */
public class ejercicio6_4 {

    public static void main(String[] args) {
        ColaEnteros cola = new ColaEnteros(3);

        long inicio = System.nanoTime();

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);

        int primero = cola.front();
        int retirado = cola.dequeue();
        cola.enqueue(40);

        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Frente consultado: " + primero);
        System.out.println("Elemento desencolado: " + retirado);
        System.out.println("Tiempo de operaciones: "
                + tiempoTranscurrido + " ns");

        System.out.println("Elementos restantes en orden FIFO:");
        while (!cola.isEmpty()) {
            System.out.println(cola.dequeue());
        }
    }
}

/**
 * Cola circular de enteros basada en un arreglo.
 *
 * <p>El contador {@code cantidad} registra cuántos elementos hay y permite
 * distinguir entre cola vacía ({@code cantidad == 0}) y cola llena
 * ({@code cantidad == capacidad}). Esto es importante porque, en ambos
 * estados, los índices {@code front} y {@code rear} podrían coincidir.</p>
 */
class ColaEnteros {
    private final int[] datos;
    private final int capacidad;

    // Índice del siguiente elemento que se consultará o retirará.
    private int front;

    // Índice donde se insertará el próximo elemento.
    private int rear;

    // Cantidad actual de elementos en la cola.
    private int cantidad;

    public ColaEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero.");
        }

        this.datos = new int[capacidad];
        this.capacidad = capacidad;
        this.front = 0;
        this.rear = 0;
        this.cantidad = 0;
    }

    public void enqueue(int valor) {
        if (isFull()) {
            throw new IllegalStateException("Overflow: Cola llena");
        }

        datos[rear] = valor;

        /*
         * Avanza el índice de entrada. El módulo hace que rear vuelva a 0
         * al pasar la última posición del arreglo.
         */
        rear = (rear + 1) % capacidad;
        cantidad++;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        int valor = datos[front];

        // Avanza el índice de salida con el mismo recorrido circular.
        front = (front + 1) % capacidad;
        cantidad--;
        return valor;
    }

    public int front() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        // Consulta el próximo elemento de salida sin retirarlo.
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
}
