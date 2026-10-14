/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase PilaEnteros con un arreglo int[] datos y una variable
 * top. En el constructor PilaEnteros(int capacidad), inicializa el arreglo
 * y asigna top = -1. Implementa push, pop, peek, isEmpty, isFull y size.
 * En push, lanza IllegalStateException('Overflow: Pila llena') si la pila
 * está llena. En pop y peek, lanza IllegalStateException('Underflow: Pila
 * vacía') si está vacía. En main, prueba las operaciones y captura los
 * errores con try-catch."
 *
 * Ajustes realizados:
 * La clase pública se llama ejercicio1_4 para coincidir con el archivo;
 * PilaEnteros es una clase auxiliar. El main prueba la pila llena y vacía,
 * además de push, pop, peek, isEmpty e isFull.
 *
 * Prueba de ejecución esperada:
 * ¿Está vacía? true
 * Tope: 20
 * Tamaño: 2
 * ¿Está llena? true
 * Overflow: Pila llena
 * Sale: 20
 * Sale: 10
 * ¿Está vacía? true
 * Underflow: Pila vacía
 * Underflow: Pila vacía
 */
public class ejercicio1_4 {

    public static void main(String[] args) {
        PilaEnteros pila = new PilaEnteros(2);

        System.out.println("¿Está vacía? " + pila.isEmpty());

        pila.push(10);
        pila.push(20);

        System.out.println("Tope: " + pila.peek());
        System.out.println("Tamaño: " + pila.size());
        System.out.println("¿Está llena? " + pila.isFull());

        try {
            pila.push(30);
        } catch (IllegalStateException error) {
            System.out.println(error.getMessage());
        }

        System.out.println("Sale: " + pila.pop());
        System.out.println("Sale: " + pila.pop());
        System.out.println("¿Está vacía? " + pila.isEmpty());

        try {
            pila.pop();
        } catch (IllegalStateException error) {
            System.out.println(error.getMessage());
        }

        try {
            pila.peek();
        } catch (IllegalStateException error) {
            System.out.println(error.getMessage());
        }
    }
}

class PilaEnteros {
    private final int[] datos;
    private int top;

    public PilaEnteros(int capacidad) {
        datos = new int[capacidad];

        /*
         * -1 indica que no hay posiciones ocupadas. La primera inserción
         * incrementa top a 0, que es el índice inicial del arreglo.
         */
        top = -1;
    }

    public void push(int valor) {
        if (isFull()) {
            throw new IllegalStateException("Overflow: Pila llena");
        }

        // Incrementa top y guarda el nuevo valor en esa posición.
        datos[++top] = valor;
    }

    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        /*
         * top-- devuelve primero el índice anterior y luego lo decrementa.
         * El dato puede seguir físicamente en el arreglo, pero queda fuera
         * de la pila lógica porque size() ya no lo incluye.
         */
        return datos[top--];
    }

    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        // Consulta el elemento del tope sin quitarlo.
        return datos[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == datos.length - 1;
    }

    public int size() {
        /*
         * Los elementos activos ocupan los índices de 0 a top,
         * ambos inclusive; por eso la cantidad es top + 1.
         */
        return top + 1;
    }
}
