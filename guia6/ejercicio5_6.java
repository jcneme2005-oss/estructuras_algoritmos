/*
Prompt inicial utilizado en OpenCode:

Implementa un historial de navegación con una pila enlazada de URLs. Usa
Nodo<String> y HistorialNavegacion, con head como tope. Implementa
visitarPagina, volverAtras, paginaActual, imprimirHistorial, estaVacio y
cantidadPaginas. La página más reciente debe ser la activa; volver atrás la
retira y activa la anterior. paginaActual y volverAtras deben lanzar
IllegalStateException si el historial está vacío. En main, visita
google.com, github.com y stackoverflow.com, consulta la página actual,
retrocede dos veces, imprime el historial y mide el tiempo con
System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio5_6 como clase pública para coincidir con el archivo.
Nodo y HistorialNavegacion son clases auxiliares. Se agregó una prueba con
un historial vacío para capturar las excepciones de consulta y retroceso.
*/

public class ejercicio5_6 {

    public static void main(String[] args) {
        HistorialNavegacion historial = new HistorialNavegacion();

        long inicio = System.nanoTime();
        historial.visitarPagina("google.com");
        historial.visitarPagina("github.com");
        historial.visitarPagina("stackoverflow.com");

        String paginaActual = historial.paginaActual();
        String primeraRetirada = historial.volverAtras();
        String segundaRetirada = historial.volverAtras();
        long tiempo = System.nanoTime() - inicio;

        System.out.println("Página actual: " + paginaActual);
        System.out.println("Volver atrás: " + primeraRetirada);
        System.out.println("Volver atrás: " + segundaRetirada);
        System.out.println("Historial (de más reciente a más antigua):");
        historial.imprimirHistorial();
        System.out.println("Páginas en historial: "
                + historial.cantidadPaginas());
        System.out.println("Tiempo de operaciones: " + tiempo + " ns");

        HistorialNavegacion historialVacio = new HistorialNavegacion();

        try {
            historialVacio.paginaActual();
        } catch (IllegalStateException error) {
            // Captura Underflow al consultar un historial vacío.
            System.out.println("Error al consultar: " + error.getMessage());
        }

        try {
            historialVacio.volverAtras();
        } catch (IllegalStateException error) {
            // Captura Underflow al retroceder en un historial vacío.
            System.out.println("Error al volver atrás: " + error.getMessage());
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
 * Historial implementado como una pila LIFO.
 * head señala el tope y representa la página activa en pantalla.
 */
class HistorialNavegacion {
    private Nodo<String> head;
    private int size;

    public void visitarPagina(String url) {
        Nodo<String> nuevaPagina = new Nodo<>(url);

        // Conserva el historial anterior y pone la página nueva en el tope.
        nuevaPagina.setSiguiente(head);
        head = nuevaPagina;
        size++;
    }

    public String volverAtras() {
        if (estaVacio()) {
            throw new IllegalStateException(
                    "No hay páginas anteriores en el historial");
        }

        String urlRetirada = head.getDato();

        /*
         * Desvincula la página activa: el nodo siguiente pasa a ser head
         * y representa la nueva página activa después del retroceso.
         */
        head = head.getSiguiente();
        size--;

        return urlRetirada;
    }

    public String paginaActual() {
        if (estaVacio()) {
            throw new IllegalStateException(
                    "No hay páginas anteriores en el historial");
        }

        // Consulta head sin retirarlo de la pila.
        return head.getDato();
    }

    public void imprimirHistorial() {
        Nodo<String> actual = head;

        if (actual == null) {
            System.out.println("(vacío)");
            return;
        }

        // Recorre desde la página activa hacia las páginas anteriores.
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public boolean estaVacio() {
        return head == null;
    }

    public int cantidadPaginas() {
        return size;
    }
}
