/*
Prompt inicial utilizado en OpenCode:

Implementa en Java una pila enlazada de enteros. Usa una clase Nodo con un
dato int y una referencia al siguiente nodo, y una clase PilaEnlazada donde
head represente el tope. Corresponde usar una pila porque las operaciones
siguen LIFO (el último elemento en entrar es el primero en salir), a
diferencia de una cola, que atiende en orden FIFO.

Implementa push, pop, peek, isEmpty, buscar, imprimir y getSize. En push,
enlaza el nuevo nodo antes del antiguo head y actualiza head. En pop, avanza
head al nodo siguiente. Si se intenta hacer pop o peek con la pila vacía,
lanza IllegalStateException. Considera búsquedas presentes y ausentes, y el
estado de la pila después de desapilar todos los elementos.

Complejidad esperada: push, pop, peek e isEmpty en O(1); buscar e imprimir
en O(n); getSize en O(1) si se mantiene un contador. El espacio total es O(n).

En main, apila 10, 20 y 30, consulta el tope, busca 20 y 99, desapila todos
los elementos, captura Underflow y mide el tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio1_6 como clase pública para coincidir con el archivo;
Nodo y PilaEnlazada son clases auxiliares. La implementación representa el
tope directamente con head y mantiene size. El main prueba las búsquedas y
los errores de pop y peek sobre la pila vacía; no invoca imprimir() ni
getSize(), aunque ambos métodos están implementados.

Prueba de ejecución esperada:

Tope: 30
¿Está el 20? true
¿Está el 99? false
Elementos desapilados: 30, 20, 10
¿Está vacía? true
Underflow en pop: Underflow: Pila vacía
Underflow en peek: Underflow: Pila vacía
El tiempo se muestra en nanosegundos y varía en cada ejecución.
*/
public class ejercicio1_6 {

    public static void main(String[] args) {
        PilaEnlazada pila = new PilaEnlazada();

        long inicio = System.nanoTime();

        pila.push(10);
        pila.push(20);
        pila.push(30);

        int tope = pila.peek();
        boolean encuentra20 = pila.buscar(20);
        boolean encuentra99 = pila.buscar(99);

        StringBuilder desapilados = new StringBuilder();
        while (!pila.isEmpty()) {
            if (desapilados.length() > 0) {
                desapilados.append(", ");
            }
            desapilados.append(pila.pop());
        }

        long tiempo = System.nanoTime() - inicio;

        System.out.println("Tope: " + tope);
        System.out.println("¿Está el 20? " + encuentra20);
        System.out.println("¿Está el 99? " + encuentra99);
        System.out.println("Elementos desapilados: " + desapilados);
        System.out.println("¿Está vacía? " + pila.isEmpty());
        System.out.println("Tiempo de operaciones: " + tiempo + " ns");

        try {
            pila.pop();
        } catch (IllegalStateException error) {
            System.out.println("Underflow en pop: " + error.getMessage());
        }

        try {
            pila.peek();
        } catch (IllegalStateException error) {
            System.out.println("Underflow en peek: " + error.getMessage());
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
 * Pila enlazada de enteros. head señala el tope LIFO: el último nodo insertado
 * es el primero que se retira.
 */
class PilaEnlazada {
    private Nodo head;
    private int size;

    public void push(int dato) {
        Nodo nuevo = new Nodo(dato);

        /*
         * Primero enlaza el nodo nuevo con el antiguo tope; después actualiza
         * head. Así se conserva la cadena de nodos existente.
         */
        nuevo.setSiguiente(head);
        head = nuevo;
        size++;
    }

    public int pop() {
        if (isEmpty()) {
            // Underflow: no se puede retirar un elemento de una pila vacía.
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        int dato = head.getDato();

        // El nodo siguiente pasa a ser el nuevo tope de la pila.
        head = head.getSiguiente();
        size--;

        return dato;
    }

    public int peek() {
        if (isEmpty()) {
            // Underflow: una pila vacía no tiene un tope que consultar.
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        // Consulta el tope sin retirarlo ni modificar head.
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

