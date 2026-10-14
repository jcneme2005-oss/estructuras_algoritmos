/*
Prompt inicial utilizado en OpenCode:

Implementa en Java una cola enlazada de enteros usando las clases Nodo y
ColaEnlazada. Usa head como inicio y punto de salida (dequeue), tail como
final y punto de entrada (enqueue), y size para contar los elementos. Esta
estructura corresponde porque una cola atiende en orden FIFO: primero entra,
primero sale; una pila LIFO retiraría primero al último elemento ingresado.

Implementa enqueue(int dato), dequeue(), front(), isEmpty(), buscar(int dato),
imprimir() y getSize(). En enqueue, agrega en O(1) mediante tail; si la cola
está vacía, head y tail deben apuntar al primer nodo. En dequeue, retira head
en O(1) y, si era el último nodo, asigna tail = null. Si dequeue o front se
usan con la cola vacía, lanza IllegalStateException por Underflow.

Contempla la cola inicialmente vacía, la transición de vacía a ocupada, la
eliminación del último elemento y búsquedas presentes y ausentes. Complejidad
esperada: enqueue, dequeue, front, isEmpty y getSize en O(1); buscar e
imprimir en O(n); espacio total O(n).

En main, encola 10, 20 y 30, consulta el frente, busca 20 y 99, desencola
todos los elementos y captura Underflow. Mide el tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio2_6 como clase pública para coincidir con el archivo;
Nodo y ColaEnlazada son clases auxiliares. Se actualiza tail a null al retirar
el último elemento para mantener coherentes los extremos de la cola.

Prueba de ejecución esperada:

Frente: 10
¿Está el 20? true
¿Está el 99? false
Elementos desencolados: 10, 20, 30
¿Está vacía? true
Underflow en dequeue: Underflow: Cola vacía
Underflow en front: Underflow: Cola vacía
El tiempo se muestra en nanosegundos y varía entre ejecuciones.
*/
public class ejercicio2_6 {

    public static void main(String[] args) {
        ColaEnlazada cola = new ColaEnlazada();

        long inicio = System.nanoTime();

        cola.enqueue(10);
        cola.enqueue(20);
        cola.enqueue(30);

        int primero = cola.front();
        boolean encuentra20 = cola.buscar(20);
        boolean encuentra99 = cola.buscar(99);

        StringBuilder desencolados = new StringBuilder();
        while (!cola.isEmpty()) {
            if (desencolados.length() > 0) {
                desencolados.append(", ");
            }
            desencolados.append(cola.dequeue());
        }

        long tiempo = System.nanoTime() - inicio;

        System.out.println("Frente: " + primero);
        System.out.println("¿Está el 20? " + encuentra20);
        System.out.println("¿Está el 99? " + encuentra99);
        System.out.println("Elementos desencolados: " + desencolados);
        System.out.println("¿Está vacía? " + cola.isEmpty());
        System.out.println("Tiempo de operaciones: " + tiempo + " ns");

        try {
            cola.dequeue();
        } catch (IllegalStateException error) {
            System.out.println("Underflow en dequeue: " + error.getMessage());
        }

        try {
            cola.front();
        } catch (IllegalStateException error) {
            System.out.println("Underflow en front: " + error.getMessage());
        }
    }
}

class Nodo {
    private final int dato;
    private Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
    }

    int getDato() {
        return dato;
    }

    Nodo getSiguiente() {
        return siguiente;
    }

    void setSiguiente(Nodo siguiente) {
        this.siguiente = siguiente;
    }
}

/**
 * Cola enlazada de enteros con comportamiento FIFO.
 *
 * head señala el punto de salida: dequeue retira desde allí.
 * tail señala el punto de entrada: enqueue agrega al final, en O(1).
 */
class ColaEnlazada {
    private Nodo head;
    private Nodo tail;
    private int size;

    public void enqueue(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (isEmpty()) {
            /*
             * Si estaba vacía, el nuevo nodo es el único elemento:
             * ambos punteros deben señalarlo.
             */
            head = nuevo;
            tail = nuevo;
        } else {
            // Agrega el nuevo nodo después de tail y actualiza la entrada.
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        size++;
    }

    public int dequeue() {
        if (isEmpty()) {
            // Underflow: no hay ningún elemento que retirar.
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        // Guarda y retira el nodo del punto de salida.
        int dato = head.getDato();
        head = head.getSiguiente();
        size--;

        /*
         * Si head ahora es null, se retiró el último elemento.
         * tail también debe quedar en null para representar la cola vacía.
         */
        if (head == null) {
            tail = null;
        }

        return dato;
    }

    public int front() {
        if (isEmpty()) {
            // Underflow: la cola vacía no tiene frente para consultar.
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        // Consulta el punto de salida sin retirar el elemento.
        return head.getDato();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean buscar(int dato) {
        Nodo actual = head;

        while (actual != null) {
            if (actual.getDato() == dato) {
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    public void imprimir() {
        Nodo actual = head;

        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        }

        System.out.println("null");
    }

    public int getSize() {
        return size;
    }
}
