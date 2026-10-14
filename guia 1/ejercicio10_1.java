import java.util.Arrays;

public class ejercicio10_1 {

    // Cuenta las comparaciones entre elementos vecinos.
    private static long comparaciones;

    // Cuenta los intercambios realizados.
    private static long intercambios;

    /**
     * Ordena el vector ascendentemente usando Bubble Sort.
     * Modifica el vector original y usa memoria auxiliar O(1):
     * solo necesita variables temporales, no crea otro arreglo.
     *
     * @param vector arreglo que se va a ordenar
     */
    public static void bubbleSort(int[] vector) {
        // Reinicia los contadores para esta ejecución.
        comparaciones = 0;
        intercambios = 0;

        /*
         * Cada pasada del bucle externo desplaza valores grandes hacia
         * el final. Después de cada pasada, una posición final queda
         * ordenada y ya no necesita revisarse.
         */
        for (int i = 0; i < vector.length - 1; i++) {

            /*
             * El bucle interno compara cada par de elementos vecinos
             * de la parte que todavía falta ordenar.
             */
            for (int j = 0; j < vector.length - 1 - i; j++) {
                comparaciones++;

                // Si están en orden descendente, se intercambian.
                if (vector[j] > vector[j + 1]) {
                    int swap = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = swap;
                    intercambios++;
                }
            }
        }
    }

    public static void main(String[] args) {
        int[] vector = {64, 34, 25, 12, 22, 11, 90};

        long inicio = System.nanoTime();
        bubbleSort(vector);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Vector ordenado: " + Arrays.toString(vector));
        System.out.println("Comparaciones: " + comparaciones);
        System.out.println("Intercambios: " + intercambios);
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}