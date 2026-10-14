public class ejercicio10_3 {

    public static void insertionSort(Jugador[] jugadores) {
        // Recorre los jugadores desde el segundo elemento.
        for (int i = 1; i < jugadores.length; i++) {
            // Conserva el jugador que se insertará en su posición correcta.
            Jugador clave = jugadores[i];
            int j = i - 1;

            /*
             * Según compareTo, un resultado positivo significa que el
             * jugador a la izquierda debe ir después de la clave.
             * Se desplaza a la derecha para abrirle espacio.
             */
            while (j >= 0 && jugadores[j].compareTo(clave) > 0) {
                jugadores[j + 1] = jugadores[j];
                j--;
            }

            // Inserta la clave en el espacio que dejaron los desplazamientos.
            jugadores[j + 1] = clave;
        }
    }

    public static void main(String[] args) {
        Jugador[] jugadores = {
            new Jugador("Lucia", 90),
            new Jugador("Ana", 100),
            new Jugador("Pedro", 90),
            new Jugador("Juan", 75),
            new Jugador("Marta", 100)
        };

        long inicio = System.nanoTime();
        insertionSort(jugadores);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        // Encabezados y columnas alineadas para mostrar el ranking como tabla.
        System.out.printf("%-9s %-15s %s%n", "Ranking", "Nombre", "Puntaje");
        for (int i = 0; i < jugadores.length; i++) {
            System.out.printf("%-9s %-15s %d%n",
                    (i + 1) + "º",
                    jugadores[i].getNombre(),
                    jugadores[i].getPuntaje());
        }

        System.out.println("Tiempo de ordenamiento: "
                + tiempoTranscurrido + " ns");
    }
}

class Jugador implements Comparable<Jugador> {
    /*
     * Los campos privados encapsulan el estado: solo se consulta desde
     * fuera mediante getters, y se inicializan al construir el objeto.
     */
    private final String nombre;
    private final int puntaje;

    public Jugador(String nombre, int puntaje) {
        this.nombre = nombre;
        this.puntaje = puntaje;
    }

    public String getNombre() {
        return nombre;
    }

    public int getPuntaje() {
        return puntaje;
    }

    @Override
    public int compareTo(Jugador otro) {
        /*
         * Orden descendente por puntaje: un puntaje mayor debe aparecer antes.
         * Si empatan, compareTo de String ordena los nombres alfabéticamente.
         */
        if (this.puntaje != otro.puntaje) {
            return otro.puntaje - this.puntaje;
        }

        return this.nombre.compareTo(otro.nombre);
    }
}
