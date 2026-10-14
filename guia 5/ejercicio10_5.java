/*
Prompt inicial utilizado en OpenCode:

Implementa una lista enlazada simple genérica en Java con las clases Nodo<T>
y ListaEnlazada<T>. Incluye inserción, búsqueda, obtención por posición,
eliminación, impresión y consulta del tamaño. Compara elementos de tipo T
con equals, no con ==. Crea una clase Alumno con legajo, nombre y un método
equals. En main, prueba las listas con Integer, String y Alumno y mide el
tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio10_5 como clase pública para coincidir con el nombre del
archivo. Nodo<T>, ListaEnlazada<T> y Alumno son clases auxiliares. La igualdad
de elementos se centralizó en sonIguales, con protección para valores null.
Alumno.equals considera iguales a los alumnos con el mismo legajo. El main
mide la búsqueda de Alumno; no mide por separado las operaciones con
Integer y String. Tampoco prueba obtener ni eliminar en el main.

Prueba de ejecución esperada:

Integer: true
10 -> 20 -> null
String: true
Ana -> Beto -> null
¿Está el legajo 1002? true
Alumno{legajo=1001, nombre='Ada'} -> Alumno{legajo=1002, nombre='Grace'} -> null
Tamaño: 2
Tiempo de búsqueda: [variable] ns
*/
public class ejercicio10_5 {

    public static void main(String[] args) {
        ListaEnlazada<Integer> enteros = new ListaEnlazada<>();
        enteros.insertarAlFinal(10);
        enteros.insertarAlFinal(20);
        System.out.println("Integer: " + enteros.buscar(20));
        enteros.imprimir();

        ListaEnlazada<String> textos = new ListaEnlazada<>();
        textos.insertarAlFinal("Ana");
        textos.insertarAlFinal("Beto");
        System.out.println("String: " + textos.buscar("Beto"));
        textos.imprimir();

        ListaEnlazada<Alumno> alumnos = new ListaEnlazada<>();
        alumnos.insertarAlFinal(new Alumno(1001, "Ada"));
        alumnos.insertarAlFinal(new Alumno(1002, "Grace"));

        long inicio = System.nanoTime();
        boolean encontrado = alumnos.buscar(new Alumno(1002, "Grace"));
        long tiempo = System.nanoTime() - inicio;

        System.out.println("¿Está el legajo 1002? " + encontrado);
        alumnos.imprimir();
        System.out.println("Tamaño: " + alumnos.getSize());
        System.out.println("Tiempo de búsqueda: " + tiempo + " ns");
    }
}

/**
 * Nodo genérico: {@code T} representa el tipo del dato guardado.
 * La referencia al nodo siguiente funciona igual sin importar si T es
 * Integer, String o una clase personalizada.
 *
 * @param <T> tipo del dato almacenado en el nodo
 */
class Nodo<T> {
    private final T dato;
    private Nodo<T> siguiente;

    Nodo(T dato) {
        this.dato = dato;
    }

    T getDato() {
        return dato;
    }

    Nodo<T> getSiguiente() {
        return siguiente;
    }

    void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
}

/**
 * Lista enlazada simple que almacena elementos de tipo T.
 *
 * <p>El parámetro {@code <T>} permite reutilizar la misma estructura con
 * diferentes tipos. Los nodos, {@code head} y los enlaces no dependen del
 * tipo concreto: solo cambia el dato que contiene cada nodo.</p>
 *
 * @param <T> tipo de los elementos de la lista
 */
class ListaEnlazada<T> {
    // head siempre apunta al primer nodo, sin importar el tipo de sus datos.
    private Nodo<T> head;
    private int size;

    public void insertarAlFinal(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        // Si no hay primer nodo, el nuevo pasa a ser head.
        if (head == null) {
            head = nuevo;
        } else {
            // Recorre los enlaces hasta el último nodo.
            Nodo<T> actual = head;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }

            // Conecta el último nodo con el nuevo.
            actual.setSiguiente(nuevo);
        }

        size++;
    }

    public boolean buscar(T dato) {
        Nodo<T> actual = head;

        // Recorre los nodos; la lógica de enlaces es independiente de T.
        while (actual != null) {
            if (sonIguales(actual.getDato(), dato)) {
                return true;
            }
            actual = actual.getSiguiente();
        }

        return false;
    }

    public T obtener(int posicion) {
        validarPosicion(posicion);

        Nodo<T> actual = head;
        for (int i = 0; i < posicion; i++) {
            actual = actual.getSiguiente();
        }

        return actual.getDato();
    }

    public boolean eliminar(T dato) {
        if (head == null) {
            return false;
        }

        // Si coincide el primer dato, mueve head al nodo siguiente.
        if (sonIguales(head.getDato(), dato)) {
            head = head.getSiguiente();
            size--;
            return true;
        }

        // Busca el nodo que precede al elemento que se quiere eliminar.
        Nodo<T> anterior = head;
        Nodo<T> actual = head.getSiguiente();

        while (actual != null) {
            if (sonIguales(actual.getDato(), dato)) {
                // Reconecta el enlace para omitir el nodo encontrado.
                anterior.setSiguiente(actual.getSiguiente());
                size--;
                return true;
            }

            anterior = actual;
            actual = actual.getSiguiente();
        }

        return false;
    }

    public void imprimir() {
        Nodo<T> actual = head;

        while (actual != null) {
            System.out.print(actual.getDato() + " -> ");
            actual = actual.getSiguiente();
        }

        System.out.println("null");
    }

    public int getSize() {
        return size;
    }

    private boolean sonIguales(T primero, T segundo) {
        // Trata null explícitamente antes de invocar equals.
        if (primero == null) {
            return segundo == null;
        }

        /*
         * equals compara igualdad lógica de objetos. A diferencia de ==,
         * no requiere que ambos datos sean la misma referencia en memoria.
         */
        return primero.equals(segundo);
    }

    private void validarPosicion(int posicion) {
        if (posicion < 0 || posicion >= size) {
            throw new IndexOutOfBoundsException(
                    "Posición fuera de rango: " + posicion);
        }
    }
}

class Alumno {
    private final int legajo;
    private final String nombre;

    Alumno(int legajo, String nombre) {
        this.legajo = legajo;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Alumno)) {
            return false;
        }

        Alumno otro = (Alumno) objeto;

        // Para este ejemplo, dos alumnos son iguales si coincide su legajo.
        return legajo == otro.legajo;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(legajo);
    }

    @Override
    public String toString() {
        return "Alumno{legajo=" + legajo + ", nombre='" + nombre + "'}";
    }
}