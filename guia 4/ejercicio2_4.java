import java.util.ArrayDeque;
import java.util.Deque;

public class ejercicio2_4 {

    public static void main(String[] args) {
        TorreDePlatos torre = new TorreDePlatos();

        torre.agregarPlato("Plato Cerámica Rojo");
        torre.agregarPlato("Plato Cerámica Azul");
        torre.agregarPlato("Plato Vidrio Transparente");

        System.out.println("Plato superior: " + torre.consultarPlatoSuperior());
        System.out.println("Cantidad de platos: " + torre.cantidadPlatos());

        System.out.println("Retirado: " + torre.retirarPlato());
        System.out.println("Retirado: " + torre.retirarPlato());

        TorreDePlatos torreVacia = new TorreDePlatos();

        try {
            torreVacia.retirarPlato();
        } catch (IllegalStateException error) {
            System.out.println("Error al retirar: " + error.getMessage());
        }

        try {
            torreVacia.consultarPlatoSuperior();
        } catch (IllegalStateException error) {
            System.out.println("Error al consultar: " + error.getMessage());
        }
    }
}

class TorreDePlatos {
    /*
     * Deque permite operar como una pila LIFO: el elemento del inicio
     * representa la cima. ArrayDeque administra esa posición internamente.
     */
    private final Deque<String> platos = new ArrayDeque<>();

    /** push: agrega un plato nuevo en la cima de la pila. */
    public void agregarPlato(String plato) {
        platos.push(plato);
    }

    /**
     * pop: retira y devuelve el plato de la cima.
     * Si no hay elementos, produce Underflow y lanza una excepción.
     */
    public String retirarPlato() {
        if (estaVacia()) {
            throw new IllegalStateException("La torre de platos está vacía.");
        }

        // pop quita el elemento superior y actualiza internamente la cima.
        return platos.pop();
    }

    /** peek: consulta la cima sin retirar el plato. */
    public String consultarPlatoSuperior() {
        if (estaVacia()) {
            throw new IllegalStateException("La torre de platos está vacía.");
        }

        return platos.peek();
    }

    /** isEmpty: indica si la pila no contiene platos. */
    public boolean estaVacia() {
        return platos.isEmpty();
    }

    /** size: devuelve la cantidad actual de platos. */
    public int cantidadPlatos() {
        return platos.size();
    }
}
