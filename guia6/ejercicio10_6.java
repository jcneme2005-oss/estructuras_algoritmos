/*
Prompt inicial utilizado en OpenCode:

Implementa una lista doblemente enlazada genérica en Java con NodoDoble<T>
(dato, siguiente y anterior) y ListaDoblementeEnlazada<T> (head, tail y size).
Incluye insertarAlInicio, insertarAlFinal, eliminar, imprimirHaciaAdelante,
imprimirHaciaAtras, estaVacia y getSize. En eliminar, contempla lista vacía,
único nodo, head, tail, nodo intermedio y dato inexistente. Reconecta los
enlaces en ambas direcciones y actualiza size solo cuando se elimina un nodo.
Mide las operaciones con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio10_6 como clase pública para coincidir con el archivo.
NodoDoble<T> y ListaDoblementeEnlazada<T> son clases auxiliares. El main
prueba los seis casos de eliminación y los recorridos hacia adelante y atrás.
*/

public class ejercicio10_6 {

    public static void main(String[] args) {
        ListaDoblementeEnlazada<Integer> vacia =
                new ListaDoblementeEnlazada<>();

        long inicio = System.nanoTime();
        boolean eliminaDeVacia = vacia.eliminar(1);
        long tiempoVacia = System.nanoTime() - inicio;

        ListaDoblementeEnlazada<Integer> unica =
                new ListaDoblementeEnlazada<>();
        unica.insertarAlFinal(99);

        inicio = System.nanoTime();
        boolean eliminaUnico = unica.eliminar(99);
        long tiempoUnico = System.nanoTime() - inicio;

        ListaDoblementeEnlazada<Integer> lista =
                new ListaDoblementeEnlazada<>();

        inicio = System.nanoTime();
        lista.insertarAlFinal(20);
        lista.insertarAlInicio(10);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        long tiempoInserciones = System.nanoTime() - inicio;

        System.out.println("Lista hacia adelante:");
        lista.imprimirHaciaAdelante();
        System.out.println("Lista hacia atrás:");
        lista.imprimirHaciaAtras();

        inicio = System.nanoTime();
        boolean eliminaMedio = lista.eliminar(20);
        boolean eliminaHead = lista.eliminar(10);
        boolean eliminaTail = lista.eliminar(40);
        boolean datoInexistente = lista.eliminar(500);
        long tiempoEliminaciones = System.nanoTime() - inicio;

        System.out.println("Eliminar lista vacía: " + eliminaDeVacia);
        System.out.println("Eliminar único nodo: " + eliminaUnico);
        System.out.println("Eliminar nodo intermedio: " + eliminaMedio);
        System.out.println("Eliminar head: " + eliminaHead);
        System.out.println("Eliminar tail: " + eliminaTail);
        System.out.println("Dato no encontrado: " + datoInexistente);

        System.out.println("Lista final:");
        lista.imprimirHaciaAdelante();
        System.out.println("Tamaño: " + lista.getSize());
        System.out.println("Tiempo caso vacío: " + tiempoVacia + " ns");
        System.out.println("Tiempo caso único: " + tiempoUnico + " ns");
        System.out.println("Tiempo de inserciones: "
                + tiempoInserciones + " ns");
        System.out.println("Tiempo de eliminaciones: "
                + tiempoEliminaciones + " ns");
    }
}

/**
 * Nodo doblemente enlazado: cada nodo conoce al siguiente y al anterior.
 *
 * @param <T> tipo del dato almacenado
 */
class NodoDoble<T> {
    private final T dato;
    private NodoDoble<T> siguiente;
    private NodoDoble<T> anterior;

    NodoDoble(T dato) {
        this.dato = dato;
    }

    T getDato() {
        return dato;
    }

    NodoDoble<T> getSiguiente() {
        return siguiente;
    }

    void setSiguiente(NodoDoble<T> siguiente) {
        this.siguiente = siguiente;
    }

    NodoDoble<T> getAnterior() {
        return anterior;
    }

    void setAnterior(NodoDoble<T> anterior) {
        this.anterior = anterior;
    }
}

/**
 * Lista doblemente enlazada: head y tail señalan los extremos de la cadena.
 *
 * @param <T> tipo de los elementos almacenados
 */
class ListaDoblementeEnlazada<T> {
    private NodoDoble<T> head;
    private NodoDoble<T> tail;
    private int size;

    public void insertarAlInicio(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if (estaVacia()) {
            // El primer nodo es ambos extremos y no tiene vecinos.
            head = nuevo;
            tail = nuevo;
        } else {
            // Actualiza los dos enlaces entre el nuevo nodo y el antiguo head.
            nuevo.setSiguiente(head);
            head.setAnterior(nuevo);
            head = nuevo;
        }

        size++;
    }

    public void insertarAlFinal(T dato) {
        NodoDoble<T> nuevo = new NodoDoble<>(dato);

        if (estaVacia()) {
            // El primer nodo ocupa tanto head como tail.
            head = nuevo;
            tail = nuevo;
        } else {
            // Conecta el nuevo nodo después del tail en ambas direcciones.
            nuevo.setAnterior(tail);
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        size++;
    }

    public boolean eliminar(T dato) {
        // Caso 1: una lista vacía no contiene elementos que eliminar.
        if (estaVacia()) {
            return false;
        }

        // Busca el primer nodo coincidente, sin avanzar desde una referencia nula.
        NodoDoble<T> actual = head;
        while (actual != null && !sonIguales(actual.getDato(), dato)) {
            actual = actual.getSiguiente();
        }

        // Caso 6: se alcanzó el final; el dato no existe en la lista.
        if (actual == null) {
            return false;
        }

        // Caso 2: el único nodo ocupa ambos extremos.
        if (actual == head && actual == tail) {
            head = null;
            tail = null;

        // Caso 3: se elimina head; el sucesor se convierte en el nuevo inicio.
        } else if (actual == head) {
            head = actual.getSiguiente();
            head.setAnterior(null);

        // Caso 4: se elimina tail; el anterior se convierte en el nuevo final.
        } else if (actual == tail) {
            tail = actual.getAnterior();
            tail.setSiguiente(null);

        // Caso 5: el nodo está en el medio; se reconectan sus dos vecinos.
        } else {
            NodoDoble<T> anterior = actual.getAnterior();
            NodoDoble<T> siguiente = actual.getSiguiente();

            anterior.setSiguiente(siguiente);
            siguiente.setAnterior(anterior);
        }

        // Se decrementa únicamente después de eliminar un nodo existente.
        size--;
        return true;
    }

    public void imprimirHaciaAdelante() {
        NodoDoble<T> actual = head;

        while (actual != null) {
            System.out.print(actual.getDato() + " <-> ");
            actual = actual.getSiguiente();
        }

        System.out.println("null");
    }

    public void imprimirHaciaAtras() {
        NodoDoble<T> actual = tail;

        while (actual != null) {
            System.out.print(actual.getDato() + " <-> ");
            actual = actual.getAnterior();
        }

        System.out.println("null");
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }

    private boolean sonIguales(T primero, T segundo) {
        if (primero == null) {
            return segundo == null;
        }

        return primero.equals(segundo);
    }
}
