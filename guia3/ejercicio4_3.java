import java.util.Arrays;

public class ejercicio4_3 {

    public static void selectionSortNombres(String[] array) {
        for (int i = 0; i < array.length - 1; i++) {
            int minimo = i;

            // Busca el nombre que debe ir primero en la parte no ordenada.
            for (int j = i + 1; j < array.length; j++) {
                /*
                 * compareTo compara las cadenas lexicográficamente.
                 * Un resultado menor que 0 significa que array[j] va antes
                 * que array[minimo] según el orden natural de String.
                 * El resultado se basa en sus caracteres UTF-16; no es
                 * necesariamente un orden lingüístico que tenga en cuenta
                 * reglas regionales o acentos.
                 */
                if (array[j].compareTo(array[minimo]) < 0) {
                    minimo = j;
                }
            }

            if (minimo != i) {
                /*
                 * String es un objeto: temporal guarda una referencia al
                 * objeto, y estas asignaciones cambian las referencias en
                 * las posiciones del arreglo. No copian ni modifican el
                 * contenido de los objetos String, que son inmutables.
                 *
                 * En un arreglo numérico primitivo, el intercambio mueve
                 * los valores numéricos. En Java se habla de referencias,
                 * no de punteros manipulables directamente.
                 */
                String temporal = array[i];
                array[i] = array[minimo];
                array[minimo] = temporal;
            }

            System.out.println("Pasada " + (i + 1) + ": "
                    + Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        String[] nombres = {"Lucia", "Ana", "Pedro", "Juan"};

        long inicio = System.nanoTime();
        selectionSortNombres(nombres);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}