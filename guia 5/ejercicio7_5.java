/*
Prompt inicial utilizado en OpenCode:

Escribe el método void modificar(int posicion, int nuevoDato) dentro de
ListaEnlazadaSimple. Valida que posicion < 0 || posicion >= size y, si no
es válida, lanza IndexOutOfBoundsException. Recorre la lista desde head
con un puntero auxiliar actual hasta llegar a la posición solicitada y
actualiza el dato con actual.setDato(nuevoDato), sin modificar los enlaces.
En main, prueba la modificación del primer nodo, uno del medio, el último
y una posición inválida. Mide cada operación con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio7_5 como clase pública para que coincida con el archivo.
Nodo y ListaEnlazadaSimple se añadieron como clases auxiliares. Se agregó
insertarAlFinal para preparar la lista de prueba y setDato para actualizar
el valor de un nodo sin alterar su referencia siguiente. El main captura la
posición inválida mediante try-catch.

Prueba de ejecución esperada:

Modificación del primer nodo: correcta
Lista: 100 -> 20 -> 30 -> 40 -> 50

Modificación del nodo del medio: correcta
Lista: 100 -> 20 -> 300 -> 40 -> 50

Modificación del último nodo: correcta
Lista: 100 -> 20 -> 300 -> 40 -> 500

La posición inválida produce IndexOutOfBoundsException.
Cada prueba muestra su tiempo en nanosegundos; este varía entre ejecuciones.
*/
public class ejercicio7_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);
        lista.insertarAlFinal(50);

        probarModificacion(lista, 0, 100, "primer nodo");
        probarModificacion(lista, 2, 300, "nodo del medio");
        probarModificacion(lista, lista.getSize() - 1, 500, "último nodo");
        probarModificacion(lista, lista.getSize(), 999, "posición inválida");
    }

    private static void probarModificacion(
            ListaEnlazadaSimple lista, int posicion, int nuevoDato, String caso) {
        long inicio = System.nanoTime();

        try {
            lista.modificar(posicion, nuevoDato);
            long tiempo = System.nanoTime() - inicio;

            System.out.println("Modificación del " + caso + ": correcta");
            System.out.print("Lista: ");
            lista.imprimir();
            System.out.println("Tiempo: " + tiempo + " ns");
        } catch (IndexOutOfBoundsException error) {
            long tiempo = System.nanoTime() - inicio;
            System.out.println("Modificación del " + caso + ": "
                    + error.getMessage() + " | " + tiempo + " ns");
        }
    }
}

class Nodo {
    private int dato;
    private Nodo siguiente;

    Nodo(int dato) {
        this.dato = dato;
    }

    int getDato() {
        return dato;
    }

    void setDato(int dato) {
        this.dato = dato;
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

    public void modificar(int posicion, int nuevoDato) {
        // Valida los límites antes de recorrer la lista.
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición fuera de rango: " + posicion);
        }

        // Usa un puntero auxiliar; head conserva la referencia al primer nodo.
        Nodo actual = head;

        // Avanza enlace por enlace hasta la posición solicitada.
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }

        /*
         * Cambia únicamente el dato del nodo. setDato no modifica siguiente,
         * así que se conserva el enlace con el resto de la lista.
         */
        actual.setDato(nuevoDato);
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