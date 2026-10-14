/*
Prompt inicial utilizado en OpenCode:

Implementa en Java una cola enlazada genérica usando Nodo<T> y
ColaEnlazada<T>, con punteros head, tail y size. Usa una cola porque sigue
FIFO: el primero en entrar es el primero en salir. head es la salida y tail
la entrada.

Implementa enqueue(T), dequeue(), front(), isEmpty(), buscar(T), imprimir()
y getSize(). enqueue agrega en O(1) mediante tail y contempla la cola vacía.
dequeue retira head en O(1) y, al quitar el último elemento, deja tail en
null. dequeue y front lanzan IllegalStateException si la cola está vacía.
buscar compara objetos con equals() y contempla referencias null. Complejidad
esperada: operaciones en los extremos y getSize en O(1); buscar e imprimir
en O(n); espacio O(n).

Crea Cliente con id y nombre. En main, prueba la cola con String y Cliente,
mide el tiempo con System.nanoTime() y muestra los resultados.

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio4_6 como clase pública para coincidir con el archivo.
Se agregaron Nodo<T> y main, que faltaban en la respuesta inicial. Cliente
define igualdad por id; la búsqueda genérica usa equals() y contempla null.
Se agregó una prueba de Underflow.

Prueba esperada:
La búsqueda de "Beto" y del cliente con id 102 devuelve true.
Las colas muestran los elementos en orden FIFO. Los tiempos varían.
*/

public class ejercicio4_6 {

    public static void main(String[] args) {
        ColaEnlazada<String> nombres = new ColaEnlazada<>();

        long inicio = System.nanoTime();
        nombres.enqueue("Ana");
        nombres.enqueue("Beto");
        nombres.enqueue("Carla");
        boolean encuentraBeto = nombres.buscar("Beto");
        String nombreAtendido = nombres.dequeue();
        long tiempoNombres = System.nanoTime() - inicio;

        System.out.println("Cola String:");
        nombres.imprimir();
        System.out.println("¿Está Beto? " + encuentraBeto);
        System.out.println("Atendido: " + nombreAtendido);
        System.out.println("Pendientes: " + nombres.getSize());
        System.out.println("Tiempo: " + tiempoNombres + " ns");

        ColaEnlazada<Cliente> clientes = new ColaEnlazada<>();

        inicio = System.nanoTime();
        clientes.enqueue(new Cliente(101, "Lucía"));
        clientes.enqueue(new Cliente(102, "Mateo"));
        clientes.enqueue(new Cliente(103, "Sofía"));
        boolean encuentraCliente =
                clientes.buscar(new Cliente(102, "Otro nombre"));
        Cliente proximoCliente = clientes.front();
        Cliente clienteAtendido = clientes.dequeue();
        long tiempoClientes = System.nanoTime() - inicio;

        System.out.println("\nCola Cliente:");
        clientes.imprimir();
        System.out.println("¿Está el cliente con id 102? "
                + encuentraCliente);
        System.out.println("Próximo cliente: " + proximoCliente);
        System.out.println("Atendido: " + clienteAtendido);
        System.out.println("Pendientes: " + clientes.getSize());
        System.out.println("Tiempo: " + tiempoClientes + " ns");

        ColaEnlazada<Integer> vacia = new ColaEnlazada<>();
        try {
            vacia.dequeue();
        } catch (IllegalStateException error) {
            System.out.println("Underflow controlado: " + error.getMessage());
        }
    }
}

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
 * Cola enlazada genérica. head es la salida FIFO y tail la entrada FIFO.
 *
 * @param <T> tipo de los elementos que almacena la cola
 */
class ColaEnlazada<T> {
    private Nodo<T> head;
    private Nodo<T> tail;
    private int size;

    public void enqueue(T dato) {
        Nodo<T> nuevo = new Nodo<>(dato);

        if (isEmpty()) {
            // El primer nodo ocupa ambos extremos de la cola.
            head = nuevo;
            tail = nuevo;
        } else {
            // Enlaza al final y actualiza el puntero de entrada.
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        size++;
    }

    public T dequeue() {
        if (isEmpty()) {
            // Underflow: no hay ningún elemento que retirar.
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        T dato = head.getDato();
        head = head.getSiguiente();
        size--;

        // Si se retiró el último nodo, también se limpia tail.
        if (head == null) {
            tail = null;
        }

        return dato;
    }

    public T front() {
        if (isEmpty()) {
            // Underflow: no hay ningún elemento que consultar.
            throw new IllegalStateException("Underflow: Cola vacía");
        }

        return head.getDato();
    }

    public boolean isEmpty() {
        return head == null;
    }

    public boolean buscar(T dato) {
        Nodo<T> actual = head;

        while (actual != null) {
            T datoActual = actual.getDato();

            // Compara igualdad lógica y evita llamar equals() sobre null.
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

class Cliente {
    private final int id;
    private final String nombre;

    Cliente(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    @Override
    public boolean equals(Object objeto) {
        if (this == objeto) {
            return true;
        }

        if (!(objeto instanceof Cliente)) {
            return false;
        }

        Cliente otro = (Cliente) objeto;
        return id == otro.id;
    }

    @Override
    public int hashCode() {
        return Integer.hashCode(id);
    }

    @Override
    public String toString() {
        return "Cliente{id=" + id + ", nombre='" + nombre + "'}";
    }
}