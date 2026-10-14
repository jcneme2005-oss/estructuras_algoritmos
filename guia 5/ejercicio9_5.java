/*
Prompt inicial utilizado en OpenCode:

Escribe el método void invertir() dentro de ListaEnlazadaSimple. Si la lista
está vacía o tiene un solo nodo, retorna sin cambios. Usa tres referencias:
anterior = null, actual = head y siguiente = null. Mientras actual != null,
ejecuta en este orden: guarda actual.getSiguiente() en siguiente, cambia el
enlace de actual para que apunte a anterior, avanza anterior a actual y
actual a siguiente. Al terminar, actualiza head = anterior. En main, crea la
lista 10 -> 20 -> 30 -> 40, imprímela, inviértela, imprime el resultado y mide
el tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio9_5 como clase pública para coincidir con el nombre del
archivo. Nodo y ListaEnlazadaSimple quedaron como clases auxiliares. Se agregó
insertarAlFinal para preparar la lista de prueba e imprimir para mostrarla
antes y después de invertirla.

Prueba de ejecución esperada:

Lista original: 10 -> 20 -> 30 -> 40 -> null
Lista invertida: 40 -> 30 -> 20 -> 10 -> null
Tiempo de inversión: [variable] ns
*/
public class ejercicio9_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        System.out.print("Lista original: ");
        lista.imprimir();

        long inicio = System.nanoTime();
        lista.invertir();
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.print("Lista invertida: ");
        lista.imprimir();
        System.out.println("Tiempo de inversión: "
                + tiempoTranscurrido + " ns");
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

        if (estaVacia()) {
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

    public void invertir() {
        if (estaVacia() || head.getSiguiente() == null) {
            return;
        }

        // anterior conserva la parte ya invertida; comienza sin nodo previo.
        Nodo anterior = null;

        // actual recorre los nodos pendientes de invertir; comienza en head.
        Nodo actual = head;

        // siguiente guarda temporalmente el enlace para no perder el resto.
        Nodo siguiente = null;

        while (actual != null) {
            // 1. Conserva el nodo siguiente antes de cambiar su enlace.
            siguiente = actual.getSiguiente();

            // 2. Invierte el enlace del nodo actual hacia el nodo anterior.
            actual.setSiguiente(anterior);

            // 3. Avanza anterior al nodo que acabas de invertir.
            anterior = actual;

            // 4. Avanza actual usando la referencia guardada en siguiente.
            actual = siguiente;
        }

        // anterior señala el nuevo primer nodo; actualiza la cabeza.
        head = anterior;
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int getSize() {
        return size;
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
