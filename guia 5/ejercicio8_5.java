/*
Prompt inicial utilizado en OpenCode:

Escribe el método int contarOcurrencias(int dato) dentro de
ListaEnlazadaSimple. Inicializa contador en 0 y un puntero Nodo actual = head.
Recorre todos los nodos con while (actual != null). Si
actual.getDato() == dato, incrementa contador. Avanza al siguiente nodo y,
al terminar, retorna contador. En main, crea la lista
10 -> 20 -> 10 -> 30 -> 10, cuenta las apariciones de 10, 20 y 99
(esperadas: 3, 1 y 0) y prueba también una lista vacía. Mide las llamadas
con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio8_5 como clase pública para que coincida con el archivo;
Nodo y ListaEnlazadaSimple son clases auxiliares. Se añadieron
insertarAlFinal e imprimir para preparar y mostrar la lista de prueba, y
probarConteo para medir cada búsqueda. La lista vacía se prueba con una
instancia separada.

Prueba de ejecución esperada:

Lista: 10 -> 20 -> 10 -> 30 -> 10 -> null
Ocurrencias de 10: 3
Ocurrencias de 20: 1
Ocurrencias de 99: 0
Ocurrencias de 10 en la lista vacía: 0

Cada resultado incluye su tiempo en nanosegundos, que varía entre ejecuciones.
*/
public class ejercicio8_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(10);

        lista.imprimir();
        probarConteo(lista, 10);
        probarConteo(lista, 20);
        probarConteo(lista, 99);

        ListaEnlazadaSimple listaVacia = new ListaEnlazadaSimple();
        probarConteo(listaVacia, 10);
    }

    private static void probarConteo(
            ListaEnlazadaSimple lista, int dato) {
        long inicio = System.nanoTime();
        int cantidad = lista.contarOcurrencias(dato);
        long tiempo = System.nanoTime() - inicio;

        System.out.println("Ocurrencias de " + dato + ": " + cantidad
                + " | Tiempo: " + tiempo + " ns");
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

class ListaEnlazadaSimple {
    private Nodo head;

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (head == null) {
            head = nuevo;
            return;
        }

        Nodo actual = head;
        while (actual.getSiguiente() != null) {
            actual = actual.getSiguiente();
        }
        actual.setSiguiente(nuevo);
    }

    public int contarOcurrencias(int dato) {
        int contador = 0;
        Nodo actual = head;

        /*
         * Recorre hasta null, que indica que ya no quedan nodos.
         * No retorna al encontrar una coincidencia porque debe continuar
         * para contar todas las apariciones.
         */
        while (actual != null) {
            if (actual.getDato() == dato) {
                // Suma una coincidencia y sigue recorriendo la lista.
                contador++;
            }

            actual = actual.getSiguiente();
        }

        // Devuelve el total después de revisar todos los nodos.
        return contador;
    }

    public void imprimir() {
        Nodo actual = head;

        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        }

        System.out.println("null");
    }
}
