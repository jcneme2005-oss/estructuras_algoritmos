/*
Prompt inicial utilizado en OpenCode:

Implementa una fila bancaria FIFO en Java con Nodo<T>, Cliente y
SistemaAtencionBanco. Usa head como salida y tail como entrada. Implementa
agregarCliente, atenderProximoCliente, consultarQuienSigue,
imprimirFilaActual, estaVacia y cantidadClientesEnEspera. Si se intenta
atender o consultar con la fila vacía, lanza IllegalStateException. En main,
agrega a Juan, María y Pedro, consulta el siguiente, atiende en orden de
llegada, prueba la fila vacía y mide el tiempo con System.nanoTime().

Ajustes realizados luego de la primera respuesta de OpenCode:

Se mantuvo ejercicio6_6 como clase pública para coincidir con el archivo.
Nodo<T>, Cliente y SistemaAtencionBanco son clases auxiliares. Se agregó
el manejo de la fila vacía y se mide el tiempo de las operaciones.

Prueba esperada:
El próximo es Juan y la atención sigue el orden Juan, María, Pedro.
Las consultas y atenciones posteriores en la fila vacía lanzan
IllegalStateException. El tiempo varía en cada ejecución.
*/

public class ejercicio6_6 {

    public static void main(String[] args) {
        SistemaAtencionBanco banco = new SistemaAtencionBanco();

        long inicio = System.nanoTime();

        // Las llegadas se agregan al final, conservando el orden FIFO.
        banco.agregarCliente(new Cliente("Juan", 1, "Depósito"));
        banco.agregarCliente(new Cliente("María", 2, "Consulta de saldo"));
        banco.agregarCliente(new Cliente("Pedro", 3, "Retiro"));

        // Consulta el frente sin retirar a Juan de la fila.
        Cliente siguiente = banco.consultarQuienSigue();

        StringBuilder ordenAtencion = new StringBuilder();

        // Atiende desde head hasta vaciar la fila, en orden de llegada.
        while (!banco.estaVacia()) {
            if (ordenAtencion.length() > 0) {
                ordenAtencion.append(", ");
            }
            ordenAtencion.append(banco.atenderProximoCliente());
        }

        long tiempo = System.nanoTime() - inicio;

        System.out.println("Quién sigue: " + siguiente);
        System.out.println("Orden de atención: " + ordenAtencion);
        System.out.println("Clientes pendientes: "
                + banco.cantidadClientesEnEspera());
        System.out.println("Tiempo de operaciones: " + tiempo + " ns");

        // Comprueba el Underflow al consultar o atender con la fila vacía.
        try {
            banco.consultarQuienSigue();
        } catch (IllegalStateException error) {
            System.out.println("Error al consultar: " + error.getMessage());
        }

        try {
            banco.atenderProximoCliente();
        } catch (IllegalStateException error) {
            System.out.println("Error al atender: " + error.getMessage());
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
 * Cliente con sus datos de turno y motivo de consulta.
 */
class Cliente {
    private final String nombre;
    private final int numeroTurno;
    private final String motivoConsulta;

    Cliente(String nombre, int numeroTurno, String motivoConsulta) {
        this.nombre = nombre;
        this.numeroTurno = numeroTurno;
        this.motivoConsulta = motivoConsulta;
    }

    @Override
    public String toString() {
        return "Turno " + numeroTurno + ": " + nombre
                + " (" + motivoConsulta + ")";
    }
}

/**
 * Cola FIFO: head es el próximo en salir y tail señala al último que llegó.
 */
class SistemaAtencionBanco {
    private Nodo<Cliente> head;
    private Nodo<Cliente> tail;
    private int cantidad;

    public void agregarCliente(Cliente cliente) {
        Nodo<Cliente> nuevo = new Nodo<>(cliente);

        if (estaVacia()) {
            // El primer cliente ocupa ambos extremos de la cola.
            head = nuevo;
            tail = nuevo;
        } else {
            // Enlaza al final y actualiza tail; head mantiene el próximo turno.
            tail.setSiguiente(nuevo);
            tail = nuevo;
        }

        cantidad++;
    }

    public Cliente atenderProximoCliente() {
        if (estaVacia()) {
            // Underflow: no existe un cliente que se pueda atender.
            throw new IllegalStateException(
                    "No hay clientes en espera en el banco");
        }

        // Retira desde head y avanza el frente al siguiente cliente.
        Cliente atendido = head.getDato();
        head = head.getSiguiente();
        cantidad--;

        // Si no quedan clientes, ambos extremos deben quedar vacíos.
        if (head == null) {
            tail = null;
        }

        return atendido;
    }

    public Cliente consultarQuienSigue() {
        if (estaVacia()) {
            // Underflow: una fila vacía no tiene un próximo cliente.
            throw new IllegalStateException(
                    "No hay clientes en espera en el banco");
        }

        // Devuelve el frente sin retirarlo de la fila.
        return head.getDato();
    }

    public void imprimirFilaActual() {
        Nodo<Cliente> actual = head;

        // Recorre la fila desde el próximo cliente hasta el último en llegar.
        while (actual != null) {
            System.out.println(actual.getDato());
            actual = actual.getSiguiente();
        }
    }

    public boolean estaVacia() {
        return head == null;
    }

    public int cantidadClientesEnEspera() {
        return cantidad;
    }
}