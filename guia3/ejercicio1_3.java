import java.util.Arrays;

public class ejercicio1_3 {

    public static void bubbleSortConPasadas(int[] vector) {
        int n = vector.length;

        // Muestra el estado del vector antes de comenzar a ordenarlo.
        System.out.println("Arreglo original: " + Arrays.toString(vector));

        for (int i = 0; i < n - 1; i++) {

            /*
             * En cada pasada, el mayor elemento de la parte no ordenada
             * queda al final. Por eso n - 1 - i evita volver a comparar
             * la sección final que ya está ordenada.
             */
            for (int j = 0; j < n - 1 - i; j++) {
                if (vector[j] > vector[j + 1]) {
                    // swap guarda un valor temporal para completar el intercambio.
                    int swap = vector[j];
                    vector[j] = vector[j + 1];
                    vector[j + 1] = swap;
                }
            }

            /*
             * Imprimir tras cada pasada permite observar cómo los valores
             * mayores van quedando en su posición definitiva al final.
             */
            System.out.println("Pasada " + (i + 1) + ": "
                    + Arrays.toString(vector));
        }
    }

    public static void main(String[] args) {
        int[] vector = {64, 34, 25, 12, 22, 11, 90};

        long inicio = System.nanoTime();
        bubbleSortConPasadas(vector);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}