/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase ColaSimpleEnteros con un arreglo int[] datos y los
 * índices front = 0 y rear = -1. Implementa enqueue, dequeue, isFull
 * (rear == capacidad - 1), isEmpty (front > rear) y mostrarEstadoArreglo(),
 * que imprima las celdas desocupadas al inicio. En main, llena la cola con
 * cinco elementos, desencola tres, muestra que los índices 0, 1 y 2 quedan
 * libres e intenta otro enqueue para provocar y capturar Falso Overflow."
 *
 * Ajuste del prompt:
 * Se mantuvo ejercicio9_4 como clase pública para coincidir con el nombre
 * del archivo; ColaSimpleEnteros quedó como clase auxiliar. La implementación
 * es lineal, así que rear no vuelve a usar las posiciones liberadas al inicio.
 *
 * Prueba de ejecución con datos de ejemplo:
 * Arreglo lleno:
 * [0: 10] [1: 20] [2: 30] [3: 40] [4: 50]
 * Desencolado: 10
 * Desencolado: 20
 * Desencolado: 30
 * Estado tras desencolar tres elementos:
 * [0: LIBRE] [1: LIBRE] [2: LIBRE] [3: 40] [4: 50]
 * Excepción capturada: Falso Overflow: rear llegó al final del arreglo
 */
public class ejercicio9_4 {

    public static void main(String[] args) {
        ColaSimpleEnteros cola = new ColaSimpleEnteros(5);

        // Llena el arreglo desde el índice 0 hasta el 4.
        for (int numero = 10; numero <= 50; numero += 10) {
            cola.enqueue(numero);
        }

        System.out.println("Arreglo lleno:");
        cola.mostrarEstadoArreglo();

        // Cada dequeue avanza front; las posiciones anteriores quedan "muertas".
        System.out.println("Desencolado: " + cola.dequeue());
        System.out.println("Desencolado: " + cola.dequeue());
        System.out.println("Desencolado: " + cola.dequeue());

        System.out.println("Estado tras desencolar tres elementos:");
        cola.mostrarEstadoArreglo();

        /*
         * Aunque hay tres celdas libres al inicio, rear sigue en el último
         * índice. La cola lineal no puede volver a esas celdas y el enqueue
         * provoca el falso overflow.
         */
        try {
            cola.enqueue(60);
        } catch (IllegalStateException error) {
            System.out.println("Excepción capturada: " + error.getMessage());
        }
    }
}

/**
 * Cola lineal implementada con un arreglo.
 *
 * <p>La capacidad física es el tamaño total del arreglo. La cantidad de
 * elementos presentes puede ser menor que esa capacidad y aun así no quedar
 * espacio alcanzable para insertar: rear solo avanza hacia índices mayores
 * y nunca reutiliza las celdas liberadas antes de front. Por eso
 * {@code rear == capacidad - 1} indica que se alcanzó el final lineal del
 * arreglo, aunque haya celdas vacías al principio. Esta situación es un
 * falso overflow; una cola circular permitiría reutilizarlas.</p>
 */
class ColaSimpleEnteros {
    private final int[] datos;
    private final int capacidad;
    private int front = 0;
    private int rear = -1;

    public ColaSimpleEnteros(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero.");
        }

        this.capacidad = capacidad;
        this.datos = new int[capacidad];
    }

    public void enqueue(int valor) {
        // Detecta si rear ya llegó al último índice disponible.
        if (isFull()) {
            throw new IllegalStateException(
                    "Falso Overflow: rear llegó al final del arreglo");
        }

        // Avanza rear y escribe el nuevo elemento en esa posición.
        datos[++rear] = valor;
    }

    public int dequeue() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        // Retira el elemento de salida y avanza front.
        int valor = datos[front];
        datos[front] = 0;
        front++;

        /*
         * La celda que quedó antes de front está libre físicamente, pero
         * es una celda "muerta" para esta cola: rear no retrocede ni la reutiliza.
         */
        return valor;
    }

    public boolean isFull() {
        // Es una condición de desbordamiento lineal, no de ocupación total.
        return rear == capacidad - 1;
    }

    public boolean isEmpty() {
        // No quedan elementos cuando front ya pasó a rear.
        return front > rear;
    }

    public void mostrarEstadoArreglo() {
        for (int i = 0; i < capacidad; i++) {
            boolean ocupado = i >= front && i <= rear;

            if (ocupado) {
                System.out.print("[" + i + ": " + datos[i] + "] ");
            } else {
                System.out.print("[" + i + ": LIBRE] ");
            }
        }
        System.out.println();
    }
}
