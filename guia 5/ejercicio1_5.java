/*
Prompt inicial utilizado:

Implementa una lista enlazada simple en Java, sin usar LinkedList ni
ArrayList. Define las clases Nodo y ListaEnlazadaSimple. Cada Nodo debe
guardar un dato entero y una referencia al siguiente nodo; la lista debe
mantener una referencia head y el tamaño size.

Implementa insertarAlInicio(int dato), insertarAlFinal(int dato), imprimir(),
estaVacia() y getSize(). Al insertar al inicio, enlaza primero el nodo nuevo
con el head anterior y luego actualiza head, para no perder la cadena. Al
insertar al final, recorre desde head hasta que el siguiente nodo sea null;
considera también la lista vacía. Actualiza size en cada inserción.

En main, inserta 10, 20 y 30 al inicio, y 40 y 50 al final. Imprime la lista
y su tamaño, e incluye una medición con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio1_5 como clase pública para que coincida con el nombre
del archivo. Nodo y ListaEnlazadaSimple quedaron como clases auxiliares.
Los atributos de Nodo se encapsularon y se agregaron sus métodos de acceso.
El código original de este ejercicio no incluía operaciones de eliminación
ni actualización de enlaces; por eso el prompt se limitó a inserciones y
recorrido. Si la consigna también exige esas operaciones, deben agregarse
al código y probarse.

Prueba de ejecución esperada:

Contenido de la lista: 30 -> 20 -> 10 -> 40 -> 50
Tamaño: 5
Lista vacía: false
Tiempo de inserciones: [variable] ns
*/
public class ejercicio1_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();

        long inicio = System.nanoTime();

        lista.insertarAlInicio(10);
        lista.insertarAlInicio(20);
        lista.insertarAlInicio(30);

        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.print("Contenido de la lista: ");
        lista.imprimir();
        System.out.println("Tamaño: " + lista.getSize());
        System.out.println("Lista vacía: " + lista.estaVacia());
        System.out.println("Tiempo de inserciones: "
                + tiempoTranscurrido + " ns");
    }
}

class Nodo {
    private final int dato;
    private Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
        this.siguiente = null;
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

    public ListaEnlazadaSimple() {
        head = null;
        size = 0;
    }

    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);

        /*
         * Primero enlaza el nodo nuevo con la cadena existente.
         * Después actualiza head. Si se cambiara head antes, se perdería
         * la referencia al inicio de la cadena anterior.
         */
        nuevo.setSiguiente(head);
        head = nuevo;

        // La lista ganó exactamente un nodo.
        size++;
    }

    public void insertarAlFinal(int dato) {
        Nodo nuevo = new Nodo(dato);

        if (estaVacia()) {
            head = nuevo;
        } else {
            Nodo actual = head;

            /*
             * Avanza mientras el nodo actual tenga un sucesor.
             * Al detenerse, actual es el último nodo de la lista.
             */
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            // Enlaza el último nodo con el nuevo nodo.
            actual.setSiguiente(nuevo);
        }

        // Se incrementa una sola vez, tanto si se insertó al inicio
        // de una lista vacía como si se agregó al final.
        size++;
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

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
    }
}