
/*
 * Prompt inicial usado en OpenCode:
 * "Escribe una clase SistemaTurnosOficina que gestione una cola de clientes,
 * representados por Turno con numeroTurno y nombreCliente. Implementa
 * agregarPersona, atenderSiguiente, consultarPrimero,
 * hayClientesEnEspera y clientesPendientes. Si se intenta atender o
 * consultar cuando no hay clientes, lanza IllegalStateException con el
 * mensaje 'No hay clientes en espera en la oficina'. En main, agrega a
 * Ana, Pedro, Lucía y Carlos, atiende a dos, agrega un nuevo cliente y
 * atiende a los restantes hasta comprobar la cola vacía con try-catch."
 *
 * Ajustes realizados:
 * La clase pública se llama ejercicio7_4 para coincidir con el archivo;
 * SistemaTurnosOficina y Turno son clases auxiliares. Se implementó una
 * cola circular de capacidad fija y se añadió una prueba de Overflow, que
 * no estaba en el prompt inicial.
 *
 * Prueba de ejecución esperada:
 * Próximo cliente: Turno 1: Ana
 * Atendido: Turno 1: Ana
 * Atendido: Turno 2: Pedro
 * Atendido: Turno 3: Lucía
 * Atendido: Turno 4: Carlos
 * Atendido: Turno 5: Sofía
 * Error de capacidad: Overflow: Cola llena
 * Error de atención: No hay clientes en espera en la oficina
 * Error de consulta: No hay clientes en espera en la oficina
 */
public class ejercicio7_4 {

    public static void main(String[] args) {
        SistemaTurnosOficina oficina = new SistemaTurnosOficina(5);

        // Las llegadas se encolan al final, en orden de llegada.
        oficina.agregarPersona("Ana");
        oficina.agregarPersona("Pedro");
        oficina.agregarPersona("Lucía");
        oficina.agregarPersona("Carlos");

        System.out.println("Próximo cliente: " + oficina.consultarPrimero());

        // La atención retira siempre al cliente que lleva más tiempo esperando.
        System.out.println("Atendido: " + oficina.atenderSiguiente());
        System.out.println("Atendido: " + oficina.atenderSiguiente());

        oficina.agregarPersona("Sofía");

        while (oficina.hayClientesEnEspera()) {
            System.out.println("Atendido: " + oficina.atenderSiguiente());
        }

        // Comprueba el desbordamiento en una cola de capacidad uno.
        SistemaTurnosOficina limitada = new SistemaTurnosOficina(1);
        limitada.agregarPersona("Cliente de prueba");

        try {
            limitada.agregarPersona("Cliente extra");
        } catch (IllegalStateException error) {
            System.out.println("Error de capacidad: " + error.getMessage());
        }

        // Comprueba el subdesbordamiento tras vaciar la cola principal.
        try {
            oficina.atenderSiguiente();
        } catch (IllegalStateException error) {
            System.out.println("Error de atención: " + error.getMessage());
        }

        try {
            oficina.consultarPrimero();
        } catch (IllegalStateException error) {
            System.out.println("Error de consulta: " + error.getMessage());
        }
    }
}

/**
 * Cola circular de capacidad fija para atender clientes en orden FIFO.
 *
 * <p>FIFO (primero en entrar, primero en salir) representa la atención por
 * orden de llegada. LIFO atendería primero al último en llegar, como una pila,
 * y no reflejaría el funcionamiento habitual de una fila de clientes.</p>
 */
class SistemaTurnosOficina {
    private final Turno[] cola;
    private int front;
    private int rear;
    private int cantidad;
    private int siguienteNumeroTurno = 1;

    public SistemaTurnosOficina(int capacidad) {
        if (capacidad <= 0) {
            throw new IllegalArgumentException(
                    "La capacidad debe ser mayor que cero.");
        }

        cola = new Turno[capacidad];
    }

    public void agregarPersona(String nombre) {
        if (cantidad == cola.length) {
            throw new IllegalStateException("Overflow: Cola llena");
        }

        cola[rear] = new Turno(siguienteNumeroTurno++, nombre);
        rear = (rear + 1) % cola.length;
        cantidad++;
    }

    public Turno atenderSiguiente() {
        if (!hayClientesEnEspera()) {
            throw new IllegalStateException(
                    "No hay clientes en espera en la oficina");
        }

        Turno atendido = cola[front];
        cola[front] = null;
        front = (front + 1) % cola.length;
        cantidad--;
        return atendido;
    }

    public Turno consultarPrimero() {
        if (!hayClientesEnEspera()) {
            throw new IllegalStateException(
                    "No hay clientes en espera en la oficina");
        }

        return cola[front];
    }

    public boolean hayClientesEnEspera() {
        return cantidad > 0;
    }

    public int clientesPendientes() {
        return cantidad;
    }
}

class Turno {
    private final int numeroTurno;
    private final String nombreCliente;

    Turno(int numeroTurno, String nombreCliente) {
        this.numeroTurno = numeroTurno;
        this.nombreCliente = nombreCliente;
    }

    @Override
    public String toString() {
        return "Turno " + numeroTurno + ": " + nombreCliente;
    }
}