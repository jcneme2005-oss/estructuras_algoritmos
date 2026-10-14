/*
Prompt inicial utilizado en OpenCode:

Escribe el método void insertarEnPosicion(int dato, int posicion) dentro de
ListaEnlazadaSimple. Valida que posicion esté entre 0 y size, inclusive;
si no, lanza IndexOutOfBoundsException. Si posicion == 0, llama a
insertarAlInicio(dato). En otro caso, recorre hasta el nodo anterior y
enlaza en este orden: primero nuevo.setSiguiente(actual.getSiguiente()) y
luego actual.setSiguiente(nuevo). Incrementa size después de insertar.
En main, prueba inserciones al inicio, en el medio, al final y posiciones
inválidas, midiendo el tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio4_5 como clase pública para que coincida con el archivo.
Nodo y ListaEnlazadaSimple se definieron como clases auxiliares. Se agregaron
insertarAlInicio, insertarAlFinal e imprimir para construir y mostrar los
casos de prueba. La inserción en posición 0 delega el incremento de size a
insertarAlInicio para evitar incrementarlo dos veces.

Prueba de ejecución esperada:

Insertado 10 en posición 0: 10 -> 20 -> 30 -> 40
Insertado 25 en posición 2: 10 -> 20 -> 25 -> 30 -> 40
Insertado 50 en posición 5: 10 -> 20 -> 25 -> 30 -> 40 -> 50
Posición -1 inválida: Posición fuera de rango: -1
Posición 7 inválida: Posición fuera de rango: 7
Los tiempos se muestran en nanosegundos y varían en cada ejecución.
*/
public class ejercicio4_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);
        lista.insertarAlFinal(40);

        probarInsercion(lista, 10, 0);  // Inicio
        probarInsercion(lista, 25, 2);  // Medio
        probarInsercion(lista, 50, 5);  // Final
        probarInsercion(lista, 99, -1); // Posición inválida
        probarInsercion(lista, 99, 7);  // Posición inválida
    }

    private static void probarInsercion(
            ListaEnlazadaSimple lista, int dato, int posicion) {
        long inicio = System.nanoTime();

        try {
            lista.insertarEnPosicion(dato, posicion);
            long tiempo = System.nanoTime() - inicio;

            System.out.print("Insertado " + dato + " en posición "
                    + posicion + ": ");
            lista.imprimir();
            System.out.println("Tiempo: " + tiempo + " ns");
        } catch (IndexOutOfBoundsException error) {
            long tiempo = System.nanoTime() - inicio;
            System.out.println("Posición " + posicion + " inválida: "
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

    public void insertarAlInicio(int dato) {
        Nodo nuevo = new Nodo(dato);
        nuevo.setSiguiente(head);
        head = nuevo;
        size++;
    }

    public void insertarAlFinal(int dato) {
        insertarEnPosicion(dato, size);
    }

    public void insertarEnPosicion(int dato, int posicion) {
        // Las posiciones válidas van de 0 a size, inclusive.
        if (posicion < 0 || posicion > size) {
            throw new IndexOutOfBoundsException(
                    "Posición fuera de rango: " + posicion);
        }

        // Este método ya actualiza head y aumenta size.
        if (posicion == 0) {
            insertarAlInicio(dato);
            return;
        }

        // Avanza hasta el nodo anterior a la posición solicitada.
        Nodo actual = head;
        for (int i = 0; i < posicion - 1; i++) {
            actual = actual.getSiguiente();
        }

        Nodo nuevo = new Nodo(dato);

        /*
         * Primero conecta nuevo con el resto de la cadena; después conecta
         * actual con nuevo. Invertir este orden podría perder los nodos
         * que siguen a actual.
         */
        nuevo.setSiguiente(actual.getSiguiente());
        actual.setSiguiente(nuevo);

        // La inserción agregó un nodo a la lista.
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
}