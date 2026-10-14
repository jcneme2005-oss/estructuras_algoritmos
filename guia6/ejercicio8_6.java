/*
Prompt inicial utilizado en OpenCode:

Implementa en Java Nodo<T>, PilaEnlazada<T> e InversorPalabra. El método
invertir(String palabra) debe apilar los caracteres de izquierda a derecha
y desapilarlos para formar la palabra invertida. Usa una pila porque su
comportamiento LIFO devuelve primero el último carácter apilado. Contempla
la palabra vacía. En main, prueba "algoritmo" y "java" y mide el tiempo con
System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio8_6 como clase pública para coincidir con el archivo.
Nodo<T>, PilaEnlazada<T> e InversorPalabra quedaron como clases auxiliares.
pop() controla Underflow, aunque invertir() solo lo invoca mientras la pila
no está vacía.

Prueba esperada:
algoritmo -> omtirogla
java -> avaj
Los tiempos varían entre ejecuciones.
*/

public class ejercicio8_6 {

    public static void main(String[] args) {
        probarInversion("algoritmo");
        probarInversion("java");
    }

    private static void probarInversion(String palabra) {
        long inicio = System.nanoTime();
        String resultado = InversorPalabra.invertir(palabra);
        long tiempo = System.nanoTime() - inicio;

        System.out.println(palabra + " -> " + resultado
                + " | Tiempo: " + tiempo + " ns");
    }
}

class Nodo<T> {
    private final T dato;
    private Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
    }

    T getDato() {
        return dato;
    }

    Nodo<T> getSiguiente() {
        return siguiente;
    }

    void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}

class PilaEnlazada<T> {
    private Nodo<T> head;
    private int size;

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);
        nuevo.setSiguiente(head);
        head = nuevo;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        T dato = head.getDato();
        head = head.getSiguiente();
        size--;
        return dato;
    }

    public boolean isEmpty() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}

class InversorPalabra {

    public static String invertir(String palabra) {
        PilaEnlazada<Character> pila = new PilaEnlazada<>();

        // Fase 1: recorre la palabra de izquierda a derecha y apila cada carácter.
        for (int i = 0; i < palabra.length(); i++) {
            pila.push(palabra.charAt(i));
        }

        // Fase 2: reconstruye la palabra retirando los caracteres de la pila.
        StringBuilder invertida = new StringBuilder();

        // Se detiene cuando ya se desapilaron todos los caracteres.
        while (!pila.isEmpty()) {
            /*
             * Por LIFO, sale primero el último carácter apilado.
             * Al añadirlo al resultado, se construye la palabra al revés.
             */
            invertida.append(pila.pop());
        }

        return invertida.toString();
    }
}