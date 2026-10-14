/*
Prompt inicial utilizado en OpenCode:

Implementa en Java una pila enlazada genérica PilaEnlazada<T> con nodos
Nodo<T>. Usa head como tope porque la pila sigue LIFO: el último elemento
que entra es el primero que sale. Implementa push(T), pop(), peek(),
isEmpty(), buscar(T), imprimir() y getSize(). En buscar, compara objetos
con equals() en lugar de == y contempla referencias null. Define Alumno con
legajo, nombre y equals(), considerando iguales a alumnos con el mismo
legajo. En main, prueba la pila con Integer, String y Alumno y mide el
tiempo con System.nanoTime().

Complejidad esperada: push, pop, peek, isEmpty y getSize en O(1);
buscar e imprimir en O(n); espacio total O(n). Controla Underflow al
consultar o retirar elementos de una pila vacía.

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio3_6 como clase pública para coincidir con el archivo;
Nodo<T>, PilaEnlazada<T> y Alumno quedaron como clases auxiliares. La
comparación genérica contempla null y usa equals(). Alumno.equals()
define la igualdad por legajo. El main prueba Underflow con pop(), pero no
llama a imprimir() ni prueba Underflow con peek().

Prueba de ejecución esperada:

Integer: tope=30, buscar 20=true, retirado=30, tamaño=2
String: tope=Carla, buscar Beto=true, retirado=Carla, tamaño=2
Alumno: tope=Alumno{legajo=1003, nombre='Katherine'},
buscar legajo 1002=true,
retirado=Alumno{legajo=1003, nombre='Katherine'}, tamaño=2
Underflow controlado: Underflow: Pila vacía

Cada tipo muestra además su tiempo en nanosegundos, que varía entre
ejecuciones.
*/
public class ejercicio3_6 {

    public static void main(String[] args) {
        PilaEnlazada<Integer> enteros = new PilaEnlazada<>();

        long inicio = System.nanoTime();
        enteros.push(10);
        enteros.push(20);
        enteros.push(30);
        int topeEnteros = enteros.peek();
        boolean encuentra20 = enteros.buscar(20);
        int retiradoEnteros = enteros.pop();
        long tiempoEnteros = System.nanoTime() - inicio;

        System.out.println("Integer: tope=" + topeEnteros
                + ", buscar 20=" + encuentra20
                + ", retirado=" + retiradoEnteros
                + ", tamaño=" + enteros.getSize());
        System.out.println("Tiempo Integer: " + tiempoEnteros + " ns");

        PilaEnlazada<String> textos = new PilaEnlazada<>();

        inicio = System.nanoTime();
        textos.push("Ana");
        textos.push("Beto");
        textos.push("Carla");
        String topeTextos = textos.peek();
        boolean encuentraBeto = textos.buscar("Beto");
        String retiradoTexto = textos.pop();
        long tiempoTextos = System.nanoTime() - inicio;

        System.out.println("String: tope=" + topeTextos
                + ", buscar Beto=" + encuentraBeto
                + ", retirado=" + retiradoTexto
                + ", tamaño=" + textos.getSize());
        System.out.println("Tiempo String: " + tiempoTextos + " ns");

        PilaEnlazada<Alumno> alumnos = new PilaEnlazada<>();

        inicio = System.nanoTime();
        alumnos.push(new Alumno(1001, "Ada"));
        alumnos.push(new Alumno(1002, "Grace"));
        alumnos.push(new Alumno(1003, "Katherine"));
        Alumno topeAlumno = alumnos.peek();
        boolean encuentraAlumno = alumnos.buscar(new Alumno(1002, "Grace"));
        Alumno retiradoAlumno = alumnos.pop();
        long tiempoAlumnos = System.nanoTime() - inicio;

        System.out.println("Alumno: tope=" + topeAlumno
                + ", buscar legajo 1002=" + encuentraAlumno
                + ", retirado=" + retiradoAlumno
                + ", tamaño=" + alumnos.getSize());
        System.out.println("Tiempo Alumno: " + tiempoAlumnos + " ns");

        PilaEnlazada<Integer> pilaVacia = new PilaEnlazada<>();
        try {
            pilaVacia.pop();
        } catch (IllegalStateException error) {
            System.out.println("Underflow controlado: " + error.getMessage());
        }
    }
}

/**
 * Nodo genérico: T representa el tipo del dato almacenado.
 * El enlace al siguiente nodo funciona igual con Integer, String o Alumno.
 *
 * @param <T> tipo del dato guardado
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
 * Pila enlazada genérica. head apunta al tope LIFO: el último elemento
 * insertado es el primero que se retira.
 *
 * @param <T> tipo de los elementos de la pila
 */
class PilaEnlazada<T> {
    private Nodo<T> head;
    private int size;

    public void push(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        // Enlaza el nodo nuevo con el antiguo tope antes de mover head.
        nuevo.setSiguiente(head);
        head = nuevo;
        size++;
    }

    public T pop() {
        if (isEmpty()) {
            // Underflow: no se puede retirar de una pila vacía.
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        T dato = head.getDato();

        // El siguiente nodo pasa a ser el nuevo tope.
        head = head.getSiguiente();
        size--;
        return dato;
    }

    public T peek() {
        if (isEmpty()) {
            // Underflow: una pila vacía no tiene un tope que consultar.
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        // Consulta el tope sin retirarlo ni cambiar head.
        return head.getDato();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean buscar(T dato) {
        Nodo<T> actual = head;

        while (actual != null) {
            T datoActual = actual.getDato();

            /*
             * equals compara igualdad lógica entre objetos. Se comprueba
             * null antes para no invocar equals sobre una referencia nula.
             */
            if (datoActual == null ? dato == null : datoActual.equals(dato)) {
                return true;
            }

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

        // Para este ejemplo, dos alumnos son iguales si tienen el mismo legajo.
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
