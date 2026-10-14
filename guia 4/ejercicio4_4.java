/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase HistorialNavegacion que gestione una pila de URLs
 * usando String. Implementa visitarPagina(String url), volverAtras(),
 * paginaActual(), esVacio() y cantidadPaginas(). En volverAtras() y
 * paginaActual(), verifica que la pila no esté vacía y lanza
 * IllegalStateException con el mensaje 'No hay páginas anteriores en el
 * historial'. En main, navega por google.com, github.com,
 * stackoverflow.com y java.oracle.com, retrocede progresivamente y captura
 * el caso de historial vacío."
 *
 * Ajustes realizados:
 * La clase pública se llama ejercicio4_4 para coincidir con el archivo;
 * HistorialNavegacion es una clase auxiliar. Se usó ArrayDeque como pila,
 * con el inicio de la deque como cima.
 *
 * Prueba de ejecución esperada:
 * Página actual: java.oracle.com
 * Páginas en historial: 4
 * Volviendo atrás desde: java.oracle.com
 * Página actual: stackoverflow.com
 * Volviendo atrás desde: stackoverflow.com
 * Página actual: github.com
 * Volviendo atrás desde: github.com
 * Página actual: google.com
 * Volviendo atrás desde: google.com
 * No hay páginas anteriores en el historial
 * No hay páginas anteriores en el historial
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class ejercicio4_4 {

    public static void main(String[] args) {
        HistorialNavegacion historial = new HistorialNavegacion();

        // Visitar una URL equivale a push: se convierte en la nueva cima.
        historial.visitarPagina("google.com");
        historial.visitarPagina("github.com");
        historial.visitarPagina("stackoverflow.com");
        historial.visitarPagina("java.oracle.com");

        System.out.println("Página actual: " + historial.paginaActual());
        System.out.println("Páginas en historial: "
                + historial.cantidadPaginas());

        // Atrás equivale a pop: retira la URL que está en la cima.
        while (!historial.esVacio()) {
            System.out.println("Volviendo atrás desde: "
                    + historial.volverAtras());

            if (!historial.esVacio()) {
                // Actual equivale a peek: consulta la cima sin retirarla.
                System.out.println("Página actual: "
                        + historial.paginaActual());
            }
        }

        // La pila vacía no tiene una URL que consultar ni retirar.
        try {
            historial.paginaActual();
        } catch (IllegalStateException error) {
            System.out.println(error.getMessage());
        }

        try {
            historial.volverAtras();
        } catch (IllegalStateException error) {
            System.out.println(error.getMessage());
        }
    }
}

class HistorialNavegacion {
    /*
     * El inicio de la Deque representa la cima. ArrayDeque administra
     * internamente el equivalente al puntero top.
     */
    private final Deque<String> paginas = new ArrayDeque<>();

    /** Visitar: inserta la URL en la cima (push). */
    public void visitarPagina(String url) {
        paginas.push(url);
    }

    /** Atrás: retira y devuelve la URL de la cima (pop). */
    public String volverAtras() {
        if (esVacio()) {
            // Underflow: no hay ninguna URL que retirar.
            throw new IllegalStateException(
                    "No hay páginas anteriores en el historial");
        }

        // pop retira la cima y ArrayDeque actualiza su estado interno.
        return paginas.pop();
    }

    /** Actual: consulta la URL de la cima sin retirarla (peek). */
    public String paginaActual() {
        if (esVacio()) {
            // Underflow: no existe una URL actual para consultar.
            throw new IllegalStateException(
                    "No hay páginas anteriores en el historial");
        }

        return paginas.peek();
    }

    public boolean esVacio() {
        return paginas.isEmpty();
    }

    public int cantidadPaginas() {
        return paginas.size();
    }
}