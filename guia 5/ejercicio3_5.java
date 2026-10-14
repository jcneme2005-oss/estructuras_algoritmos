/*
Prompt inicial utilizado en OpenCode:

Escribe el método int obtener(int posicion) dentro de ListaEnlazadaSimple.
Valida que posicion no sea menor que 0 ni mayor o igual que size; si no es
válida, lanza IndexOutOfBoundsException. Recorre la lista desde head con un
puntero auxiliar actual y un bucle for hasta llegar a la posición solicitada,
sin modificar head. Retorna el dato del nodo encontrado. En main, prueba
posiciones válidas e inválidas y mide el tiempo de búsqueda con
System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

El método inicial se había mostrado aislado y causaba error al pegarlo fuera
de una clase. Se preparó un archivo completo con ejercicio3_5 como clase
pública, además de Nodo y ListaEnlazadaSimple. Se agregaron insertarAlFinal
para crear los datos de prueba y probarObtencion para medir el tiempo y
capturar posiciones inválidas.

Prueba de ejecución esperada:

Posición 0: 10 | [tiempo variable] ns
Posición 2: 30 | [tiempo variable] ns
Posición 3: Posición fuera de rango: 3 | [tiempo variable] ns
Posición -1: Posición fuera de rango: -1 | [tiempo variable] ns
*/
public class ejercicio3_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);

        probarObtencion(lista, 0);
        probarObtencion(lista, 2);
        probarObtencion(lista, 3);
        probarObtencion(lista, -1);
    }

    private static void probarObtencion(
            ListaEnlazadaSimple lista, int posicion) {
        long inicio = System.nanoTime();

        try {
            int dato = lista.obtener(posicion);
            long tiempo = System.nanoTime() - inicio;
            System.out.println("Posición " + posicion + ": " + dato
                    + " | " + tiempo + " ns");
        } catch (IndexOutOfBoundsException error) {
            long tiempo = System.nanoTime() - inicio;
            System.out.println("Posición " + posicion + ": "
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

    public int obtener(int posicion) {
        /*
         * Valida que la posición esté entre 0 y size - 1.
         * Así se evita recorrer una posición que no existe.
         */
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición fuera de rango: " + posicion);
        }

        /*
         * actual es una referencia local que comienza en el mismo nodo que
         * head. Avanzar actual no modifica head, que conserva el acceso al
         * primer nodo de la lista.
         */
        Nodo actual = head;

        // Avanza posicion enlaces para llegar al nodo solicitado.
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }

        return actual.getDato();
    }
}
