/*
Prompt inicial utilizado en OpenCode:

Implementa una cola enlazada FIFO para trabajos de impresión usando
Nodo<T>, TrabajoImpresion y SistemaColaImpresion. Cada trabajo guarda nombre
de archivo, cantidad de páginas y usuario. Implementa agregarTrabajo,
imprimirProximoTrabajo, consultarProximoArchivo, mostrarTrabajosPendientes,
estaVacia y cantidadTrabajosPendientes. head debe señalar el próximo trabajo
a imprimir y tail el último agregado. Si se consulta o imprime con la cola
vacía, lanza IllegalStateException. En main, agrega Reporte.pdf, Tesis.docx
y Plano.png; consulta el primero, procesa en orden FIFO, prueba la cola vacía
y mide las operaciones con System.nanoTime().

Complejidad esperada: agregar, consultar e imprimir el próximo en O(1);
mostrar la fila en O(n); consultar si está vacía o su tamaño en O(1);
espacio total O(n).

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio9_6 como clase pública para coincidir con el archivo.
Nodo<T>, TrabajoImpresion y SistemaColaImpresion son clases auxiliares.
El main prueba la excepción tanto al consultar como al imprimir cuando la
cola queda vacía.
*/

public class ejercicio9_6 {

    public static void main(String[] args) {
        SistemaColaImpresion cola = new SistemaColaImpresion();

        long inicio = System.nanoTime();

        // Cada llegada crea un TrabajoImpresion y se agrega al final de la fila.
        cola.agregarTrabajo(
                new TrabajoImpresion("Reporte.pdf", 4, "Ana"));
        cola.agregarTrabajo(
                new TrabajoImpresion("Tesis.docx", 80, "Bruno"));
        cola.agregarTrabajo(
                new TrabajoImpresion("Plano.png", 2, "Carla"));

        // Consulta el frente sin retirarlo: Reporte.pdf será el próximo.
        String proximoArchivo = cola.consultarProximoArchivo();

        StringBuilder trabajosImpresos = new StringBuilder();

        // Retira los trabajos desde el frente, respetando el orden FIFO.
        while (!cola.estaVacia()) {
            if (trabajosImpresos.length() > 0) {
                trabajosImpresos.append(", ");
            }
            trabajosImpresos.append(cola.imprimirProximoTrabajo());
        }

        long tiempoOperaciones = System.nanoTime() - inicio;

        System.out.println("Próximo archivo: " + proximoArchivo);
        System.out.println("Trabajos procesados: " + trabajosImpresos);
        System.out.println("Trabajos pendientes: "
                + cola.cantidadTrabajosPendientes());
        System.out.println("Tiempo de operaciones: "
                + tiempoOperaciones + " ns");

        // Verifica las excepciones cuando no quedan trabajos en la cola.
        try {
            cola.consultarProximoArchivo();
        } catch (IllegalStateException error) {
            System.out.println("Error al consultar: " + error.getMessage());
        }

        try {
            cola.imprimirProximoTrabajo();
        } catch (IllegalStateException error) {
            System.out.println("Error al imprimir: " + error.getMessage());
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

/** Representa un trabajo con sus datos de impresión. */
class TrabajoImpresion {
    private final String nombreArchivo;
    private final int cantidadPaginas;
    private final String usuario;

    TrabajoImpresion(String nombreArchivo, int cantidadPaginas, String usuario) {
        this.nombreArchivo = nombreArchivo;
        this.cantidadPaginas = cantidadPaginas;
        this.usuario = usuario;
    }

    String getNombreArchivo() {
        return nombreArchivo;
    }

    @Override
    public String toString() {
        return nombreArchivo + " (" + cantidadPaginas
                + " páginas, usuario: " + usuario + ")";
    }
}

/**
 * Cola FIFO de trabajos de impresión. head es el frente y punto de salida;
 * tail es el final y punto donde se agregan los nuevos trabajos.
 */
class SistemaColaImpresion {
    private Nodo<TrabajoImpresion> head;
    private Nodo<TrabajoImpresion> tail;
    private int cantidad;

    public void agregarTrabajo(TrabajoImpresion trabajo) {
        // Crea un nodo para enlazar la instancia del trabajo a la cola.
        Nodo<TrabajoImpresion> nuevo = new Nodo<>(trabajo);

        if (estaVacia()) {
            // Si no hay trabajos, el primero ocupa ambos extremos.
            head = nuevo;
            tail = nuevo;
        } else {
            // Enlaza al final y avanza tail; head conserva el próximo trabajo.
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        cantidad++;
    }

    public TrabajoImpresion imprimirProximoTrabajo() {
        if (estaVacia()) {
            // Underflow: no existe un trabajo al frente para imprimir.
            throw new IllegalStateException(
                    "No hay trabajos pendientes en la cola de impresión");
        }

        // Guarda el trabajo del frente y avanza head al siguiente nodo.
        TrabajoImpresion trabajo = head.getDato();
        head = head.getSiguiente();
        cantidad--;

        // Si se retiró el último trabajo, la cola queda sin ambos extremos.
        if (head == null) {
            tail = null;
        }

        return trabajo;
    }

    public String consultarProximoArchivo() {
        if (estaVacia()) {
            // Underflow: una cola vacía no tiene un próximo archivo.
            throw new IllegalStateException(
                    "No hay trabajos pendientes en la cola de impresión");
        }

        // Consulta el frente sin retirarlo ni modificar head.
        return head.getDato().getNombreArchivo();
    }

    public void mostrarTrabajosPendientes() {
        Nodo<TrabajoImpresion> actual = head;

        if (actual == null) {
            System.out.println("(sin trabajos pendientes)");
            return;
        }

        // Recorre desde el próximo trabajo a imprimir hasta el último agregado.
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int cantidadTrabajosPendientes() {
        return cantidad;
    }
}
