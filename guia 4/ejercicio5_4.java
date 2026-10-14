/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase genérica Pila<T> respaldada por un arreglo T[] datos.
 * En Pila(int capacidad), crea el arreglo con
 * @SuppressWarnings("unchecked") y (T[]) new Object[capacidad). Implementa
 * push, pop, peek, isEmpty, isFull y size. En pop, asigna datos[top] = null
 * antes de decrementar top. En main, crea e interactúa con una pila de
 * productos."
 *
 * Ajustes realizados:
 * La clase pública se llama ejercicio5_4 para coincidir con el archivo;
 * Pila<T> es una clase auxiliar. El main usa Producto como ejemplo de tipo
 * personalizado. La pila guarda T[] mediante la conversión requerida debido
 * a que Java no permite crear arreglos genéricos directamente.
 *
 * Prueba de ejecución esperada:
 * Producto superior: Lápiz (cantidad: 12)
 * Cantidad: 2
 * Retirado: Lápiz (cantidad: 12)
 * ¿Está vacía? false
 */
public class ejercicio5_4 {

    public static void main(String[] args) {
        Pila<Producto> pilaProductos = new Pila<>(3);

        pilaProductos.push(new Producto("Cuaderno", 5));
        pilaProductos.push(new Producto("Lápiz", 12));

        System.out.println("Producto superior: " + pilaProductos.peek());
        System.out.println("Cantidad: " + pilaProductos.size());
        System.out.println("Retirado: " + pilaProductos.pop());
        System.out.println("¿Está vacía? " + pilaProductos.isEmpty());
    }

    static class Producto {
        private final String nombre;
        private final int cantidad;

        Producto(String nombre, int cantidad) {
            this.nombre = nombre;
            this.cantidad = cantidad;
        }

        @Override
        public String toString() {
            return nombre + " (cantidad: " + cantidad + ")";
        }
    }
}

/**
 * Pila genérica respaldada por un arreglo.
 *
 * <p>El parámetro de tipo {@code T} permite que la misma pila almacene
 * distintos tipos de referencia. Por ejemplo, {@code Pila<Integer>} almacena
 * el wrapper {@code Integer}; para valores {@code int}, Java aplica
 * autoboxing. También puede almacenarse un tipo personalizado, como
 * {@code Pila<Producto>}, con sus propios campos y comportamiento. Los
 * genéricos no aceptan directamente tipos primitivos.</p>
 *
 * @param <T> tipo de los elementos almacenados
 */
class Pila<T> {
    private final T[] datos;
    private int top;

    @SuppressWarnings("unchecked")
    public Pila(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero.");
        }

        /*
         * Java no permite crear directamente un arreglo genérico con new T[].
         * Se crea un arreglo de Object y se convierte a T[]; la conversión no
         * puede comprobarse completamente en compilación debido al borrado
         * de tipos de los genéricos.
         */
        this.datos = (T[]) new Object[capacidad];

        /*
         * Suprime únicamente la advertencia de esa conversión necesaria.
         * Es razonable aquí porque el arreglo es privado y solo se escriben
         * elementos T a través de push().
         */
        this.top = -1;
    }

    public void push(T elemento) {
        if (isFull()) {
            throw new IllegalStateException("Overflow: Pila llena");
        }

        // Incrementa top y almacena el elemento en la nueva cima.
        datos[++top] = elemento;
    }

    public T pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        T elemento = datos[top];

        // Elimina la referencia del arreglo antes de bajar la cima.
        datos[top] = null;
        top--;

        return elemento;
    }

    public T peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Underflow: Pila vacía");
        }

        // Devuelve la cima sin retirarla.
        return datos[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }

    public boolean isFull() {
        return top == datos.length - 1;
    }

    public int size() {
        return top + 1;
    }
}
