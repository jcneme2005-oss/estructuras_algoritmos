/*
Prompt inicial utilizado en OpenCode:

Escribe el método boolean eliminar(int dato) dentro de
ListaEnlazadaSimple. Si la lista está vacía, retorna false. Si el dato está
en head, actualiza head al siguiente nodo, decrementa size y retorna true.
Para nodos del medio o del final, recorre con un puntero actual hasta
encontrar el nodo anterior; reconecta sus enlaces para omitir el nodo
eliminado, decrementa size y retorna true. Si no encuentra el dato, retorna
false. En main, prueba la eliminación al inicio, en el medio, al final, de
un elemento inexistente y en una lista vacía, midiendo el tiempo con
System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio5_5 como clase pública para que coincida con el archivo;
Nodo y ListaEnlazadaSimple son clases auxiliares. Se añadieron
insertarAlFinal, imprimir, estaVacia y getSize para preparar y mostrar los
casos de prueba. La prueba vacía usa una lista independiente.

Prueba de ejecución esperada:

Eliminar 10 (al inicio): true
Lista: 20 -> 30 -> 40 -> 50
Tamaño: 4

Eliminar 30 (en el medio): true
Lista: 20 -> 40 -> 50
Tamaño: 3

Eliminar 50 (al final): true
Lista: 20 -> 40
Tamaño: 2

Eliminar 99 (inexistente): false
Lista: 20 -> 40
Tamaño: 2

Eliminar 1 (lista vacía): false
Lista:
Tamaño: 0

Cada prueba también imprime el tiempo en nanosegundos, que varía entre ejecuciones.
*/
public class ejercicio5_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        probarEliminacion(lista, 10, "al inicio");   // Caso 1
        probarEliminacion(lista, 30, "en el medio"); // Caso 2
        probarEliminacion(lista, 50, "al final");   // Caso 3
        probarEliminacion(lista, 99, "inexistente");// Caso 4

        ListaEnlazadaSimple listaVacia = new ListaEnlazadaSimple();
        probarEliminacion(listaVacia, 1, "lista vacía"); // Caso 5
    }

    private static void probarEliminacion(
            ListaEnlazadaSimple lista, int dato, String caso) {
        long inicio = System.nanoTime();
        boolean eliminado = lista.eliminar(dato);
        long tiempo = System.nanoTime() - inicio;

        System.out.println("Eliminar " + dato + " (" + caso + "): "
                + eliminado);
        System.out.print("Lista: ");
        lista.imprimir();
        System.out.println("Tamaño: " + lista.getSize());
        System.out.println("Tiempo: " + tiempo + " ns");
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

    public boolean eliminar(int dato) {
        // Caso 5: una lista vacía no contiene el dato que se busca eliminar.
        if (estaVacia()) {
            return false;
        }

        // Caso 1: si el dato está al inicio, head pasa al nodo siguiente.
        if (head.getDato() == dato) {
            head = head.getSiguiente();
            size--;

            /*
             * El nodo anterior deja de estar enlazado desde head. Cuando ya
             * no queden referencias a él, será elegible para el Garbage
             * Collector; no se libera necesariamente de inmediato.
             */
            return true;
        }

        /*
         * Busca el nodo anterior al que podría eliminarse. La primera parte
         * de la condición verifica que exista un sucesor antes de consultar
         * su dato, evitando acceder a null.
         */
        Nodo actual = head;
        while (actual.getSiguiente() != null
                && actual.getSiguiente().getDato() != dato) {
            actual = actual.getSiguiente();
        }

        if (actual.getSiguiente() != null) {
            // Casos 2 y 3: elimina un nodo del medio o del final.
            Nodo eliminado = actual.getSiguiente();

            // Reconecta actual con el sucesor del nodo eliminado y lo omite.
            actual.setSiguiente(eliminado.getSiguiente());
            size--;

            /*
             * eliminado ya no pertenece a la cadena desde head. Al salir
             * del método, si no existen otras referencias a ese nodo, podrá
             * ser recuperado por el Garbage Collector.
             */
            return true;
        }

        // Caso 4: se llegó al final sin encontrar el dato.
        return false;
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
            System.out.print(actual.getDato());
            if (actual.getSiguiente() != null) {
                System.out.print(" -> ");
            }
            actual = actual.getSiguiente();
        }

        System.out.println();
    }
}
