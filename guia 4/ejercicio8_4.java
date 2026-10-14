
/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase Java ColaImpresion que gestione trabajos mediante una
 * cola de objetos Documento, con nombre y cantidadPaginas. Implementa
 * agregarDocumento, imprimirSiguiente, consultarProximo,
 * hayDocumentosPendientes y totalDocumentosEnEspera. Si se intenta imprimir
 * o consultar con la cola vacía, lanza IllegalStateException con el mensaje
 * 'No hay documentos pendientes en la cola de impresión'. En main, agrega
 * tres documentos, consulta el frente, imprime dos, agrega uno nuevo y
 * procesa los pendientes hasta comprobar el caso vacío con try-catch."
 *
 * Ajustes del prompt:
 * Se mantuvo ejercicio8_4 como clase pública para que coincida con el archivo.
 * agregarDocumento recibe un objeto Documento, no solo un String, para incluir
 * también la cantidad de páginas. La comprobación de cola vacía se hace con
 * hayDocumentosPendientes().
 *
 * Prueba de ejecución con los datos del main:
 * Próximo documento: Informe (5 páginas)
 * Imprimiendo: Informe (5 páginas)
 * Imprimiendo: Factura (2 páginas)
 * Imprimiendo: Presentación (12 páginas)
 * Imprimiendo: Contrato (8 páginas)
 * Error al consultar: No hay documentos pendientes en la cola de impresión
 * Error al imprimir: No hay documentos pendientes en la cola de impresión
 * El tiempo, si se agrega una medición, depende de cada ejecución.
 */
import java.util.ArrayDeque;
import java.util.Deque;

public class ejercicio8_4 {

    public static void main(String[] args) {
        ColaImpresion cola = new ColaImpresion();

        // Agregar documentos equivale a enqueue: se incorporan al final.
        cola.agregarDocumento(new Documento("Informe", 5));
        cola.agregarDocumento(new Documento("Factura", 2));
        cola.agregarDocumento(new Documento("Presentación", 12));

        // Consultar equivale a peek: observa el frente sin retirarlo.
        System.out.println("Próximo documento: " + cola.consultarProximo());

        // Imprimir equivale a dequeue: retira el documento del frente.
        System.out.println("Imprimiendo: " + cola.imprimirSiguiente());
        System.out.println("Imprimiendo: " + cola.imprimirSiguiente());

        // El nuevo documento se agrega detrás de los que siguen esperando.
        cola.agregarDocumento(new Documento("Contrato", 8));

        while (cola.hayDocumentosPendientes()) {
            System.out.println("Imprimiendo: " + cola.imprimirSiguiente());
        }

        // Ambos métodos comprueban y reportan el subdesbordamiento.
        try {
            cola.consultarProximo();
        } catch (IllegalStateException error) {
            System.out.println("Error al consultar: " + error.getMessage());
        }

        try {
            cola.imprimirSiguiente();
        } catch (IllegalStateException error) {
            System.out.println("Error al imprimir: " + error.getMessage());
        }
    }
}

class ColaImpresion {
    /*
     * ArrayDeque conserva el orden FIFO. El final representa el extremo de
     * entrada (rear) y el inicio, el extremo de salida (front); sus posiciones
     * internas las administra la propia colección.
     */
    private final Deque<Documento> documentos = new ArrayDeque<>();

    /** TDA Cola: agrega un documento al extremo de entrada (enqueue). */
    public void agregarDocumento(Documento documento) {
        documentos.addLast(documento);
    }

    /**
     * TDA Cola: retira y devuelve el documento del extremo de salida (dequeue).
     * Comprueba si la cola está vacía para evitar un subdesbordamiento.
     */
    public Documento imprimirSiguiente() {
        if (!hayDocumentosPendientes()) {
            throw new IllegalStateException(
                    "No hay documentos pendientes en la cola de impresión");
        }

        return documentos.removeFirst();
    }

    /**
     * TDA Cola: consulta el documento del extremo de salida (peek)
     * sin retirarlo.
     */
    public Documento consultarProximo() {
        if (!hayDocumentosPendientes()) {
            throw new IllegalStateException(
                    "No hay documentos pendientes en la cola de impresión");
        }

        return documentos.getFirst();
    }

    /** Indica si hay elementos pendientes en la cola. */
    public boolean hayDocumentosPendientes() {
        return !documentos.isEmpty();
    }

    /** Devuelve la cantidad de documentos que esperan ser impresos. */
    public int totalDocumentosEnEspera() {
        return documentos.size();
    }
}

class Documento {
    private final String nombre;
    private final int cantidadPaginas;

    public Documento(String nombre, int cantidadPaginas) {
        this.nombre = nombre;
        this.cantidadPaginas = cantidadPaginas;
    }

    public String getNombre() {
        return nombre;
    }

    public int getCantidadPaginas() {
        return cantidadPaginas;
    }

    @Override
    public String toString() {
        return nombre + " (" + cantidadPaginas + " páginas)";
    }
}