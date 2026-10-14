
public class ejercicio2_5 {

    public static void main(String[] args) {
        ListaEnlazadaSimple lista = new ListaEnlazadaSimple();
        lista.insertarAlFinal(10);
        lista.insertarAlFinal(20);
        lista.insertarAlFinal(30);

        // Mide por separado la búsqueda de un valor presente.
        long inicio = System.nanoTime();
        boolean encontrado = lista.buscar(20);
        long tiempo = System.nanoTime() - inicio;
        System.out.println("Buscar 20: " + encontrado + " | " + tiempo + " ns");

        // Mide la búsqueda de un valor que no está en la lista.
        inicio = System.nanoTime();
        encontrado = lista.buscar(99);
        tiempo = System.nanoTime() - inicio;
        System.out.println("Buscar 99: " + encontrado + " | " + tiempo + " ns");
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

    public boolean buscar(int dato) {
        // Usa una referencia local: avanzar actual no modifica ni pierde head.
        Nodo actual = head;

        /*
         * La condición evita acceder a actual cuando vale null, previniendo
         * un NullPointerException al llegar al final de la lista.
         */
        while (actual != null) {
            if (actual.getDato() == dato) {
                // Detiene la búsqueda inmediatamente al hallar el dato.
                return true;
            }

            // Avanza al siguiente nodo sin cambiar la referencia head.
            actual = actual.getSiguiente();
        }

        // Se llegó al final: el dato no aparece en la lista.
        return false;
    }
}
/*
Prompt inicial utilizado en OpenCode:

Escribe el método boolean buscar(int dato) dentro de la clase
ListaEnlazadaSimple. Recorre la lista con un puntero Nodo actual = head y un
bucle while (actual != null). Compara actual.getDato() con dato: retorna true
si lo encuentra y false al llegar al final. En main, prueba la búsqueda de
un valor presente y otro ausente, midiendo el tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se incorporó el método dentro de una clase ListaEnlazadaSimple completa para
evitar el error de declarar un método fuera de una clase. Se añadieron Nodo
y insertarAlFinal para construir una lista de prueba, y ejercicio2_5 como
clase pública acorde al nombre del archivo.

Prueba de ejecución esperada:

Buscar 20: true | [tiempo variable] ns
Buscar 99: false | [tiempo variable] ns
*/