/*
Prompt inicial utilizado en OpenCode:

Escribe el método void eliminarEnPosicion(int posicion) dentro de la clase
ListaEnlazadaSimple. Valida si posicion < 0 || posicion >= size y lanza
IndexOutOfBoundsException si no es válida. Si posicion == 0, actualiza head
al siguiente nodo y decrementa size. En otro caso, recorre hasta el nodo
anterior y lo enlaza con el nodo siguiente al que se eliminará; luego
decrementa size. En main, prueba eliminaciones al inicio, en el medio, al
final y con posiciones inválidas, midiendo el tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio6_5 como clase pública para que coincida con el archivo.
Nodo y ListaEnlazadaSimple quedaron como clases auxiliares. Se agregaron
insertarAlFinal, imprimir y getSize para preparar y mostrar las pruebas.
El main prueba las posiciones inválidas -1 y size, además de inicio, medio
y final.

Prueba de ejecución esperada:

Eliminación al inicio: correcta
Lista: 20 -> 30 -> 40 -> 50

Eliminación en el medio: correcta
Lista: 20 -> 40 -> 50

Eliminación al final: correcta
Lista: 20 -> 40

Eliminación posición negativa: Posición fuera de rango: -1
Eliminación fuera de rango: Posición fuera de rango: 2

Cada prueba muestra también su tiempo en nanosegundos, que varía entre
ejecuciones.
*/
public class ejercicio6_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        probarEliminacion(lista, 0, "al inicio");
        probarEliminacion(lista, 1, "en el medio");
        probarEliminacion(lista, lista.getSize() - 1, "al final");
        probarEliminacion(lista, -1, "posición negativa");
        probarEliminacion(lista, lista.getSize(), "fuera de rango");
    }

    private static void probarEliminacion(
            ListaEnlazadaSimple lista, int posicion, String caso) {
        long inicio = System.nanoTime();

        try {
            lista.eliminarEnPosicion(posicion);
            long tiempo = System.nanoTime() - inicio;

            System.out.println("Eliminación " + caso + ": correcta");
            System.out.print("Lista: ");
            lista.imprimir();
            System.out.println("Tiempo: " + tiempo + " ns");
        } catch (IndexOutOfBoundsException error) {
            long tiempo = System.nanoTime() - inicio;
            System.out.println("Eliminación " + caso + ": "
                    + error.getMessage() + " | " + tiempo + " ns");
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

class ListaEnlazadaSimple {
    private Nodo head;
    private int size;

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (head == null) {
            head = nuevo;
        } else {
            Nodo actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevo);
        }

        size++;
    }

    public void eliminarEnPosicion(int posicion) {
        // Las posiciones válidas van de 0 a size - 1.
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición fuera de rango: " + posicion);
        }

        // Para eliminar el primer nodo, head avanza al nodo siguiente.
        if (posicion == 0) {
            head = head.getSiguiente();
            size--;
            return;
        }

        // Avanza hasta el nodo anterior a la posición que se eliminará.
        Nodo anterior = head;
        for (int i = 0; i < posicion - 1; i++) {
            anterior = anterior.getSiguiente();
        }

        // Reconecta la lista para omitir el nodo eliminado.
        anterior.setSiguiente(anterior.getSiguiente().getSiguiente());

        // La lista contiene ahora un nodo menos.
        size--;
    }

    public int getSize() {
        return size;
    }

    public void imprimir() {
        Nodo actual = head;

        while (actual != null) {
            System.out.print(actual.getDato());
            if (actual.getSiguiente() != null) {
                System.out.print(" -> ");
            }
            actual = actual.getSiguiente();
        }
        System.out.println();
    }
}