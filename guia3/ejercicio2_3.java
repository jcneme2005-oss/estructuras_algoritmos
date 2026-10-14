/**
 * Compara Bubble Sort y Selection Sort.
 *
 * Bubble Sort compara parejas de elementos vecinos e intercambia los que
 * están desordenados. Selection Sort busca el mínimo global de la parte
 * todavía no ordenada y lo coloca al inicio de esa parte.
 */
public class ejercicio2_3 {

    private static long bubbleComparaciones;
    private static long bubbleIntercambios;
    private static long selectionComparaciones;
    private static long selectionIntercambios;

    public static void bubbleSort(int[] array) {
        bubbleComparaciones = 0;
        bubbleIntercambios = 0;

        // Cada pasada mueve un elemento grande hacia el final del arreglo.
        for (int i = 0; i < array.length - 1; i++) {
            // Compara parejas vecinas de la parte que todavía falta ordenar.
            for (int j = 0; j < array.length - 1 - i; j++) {
                bubbleComparaciones++;

                // Intercambia la pareja si está en orden descendente.
                if (array[j] > array[j + 1]) {
                    int temporal = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporal;
                    bubbleIntercambios++;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        selectionComparaciones = 0;
        selectionIntercambios = 0;

        // Cada pasada fija una posición al inicio de la parte no ordenada.
        for (int i = 0; i < array.length - 1; i++) {
            int indiceMinimo = i;

            // Busca el menor elemento entre i y el final del arreglo.
            for (int j = i + 1; j < array.length; j++) {
                selectionComparaciones++;

                if (array[j] < array[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }

            // Coloca el mínimo encontrado en la posición actual.
            if (indiceMinimo != i) {
                int temporal = array[i];
                array[i] = array[indiceMinimo];
                array[indiceMinimo] = temporal;
                selectionIntercambios++;
            }
        }
    }

    public static void main(String[] args) {
        int[] array = {64, 25, 12, 22, 11, 90, 34};

        /*
         * Ambos algoritmos modifican el arreglo recibido. Las dos copias
         * permiten que empiecen con los mismos valores y que el segundo
         * algoritmo no ordene accidentalmente el resultado del primero.
         */
        int[] copia1 = array.clone();
        int[] copia2 = array.clone();

        // El cronómetro mide el ordenamiento, no la creación de las copias.
        long inicio = System.nanoTime();
        bubbleSort(copia1);
        long tiempoBubble = System.nanoTime() - inicio;

        inicio = System.nanoTime();
        selectionSort(copia2);
        long tiempoSelection = System.nanoTime() - inicio;

        System.out.printf("%-18s %14s %14s %14s%n",
                "Algoritmo", "Comparaciones", "Intercambios", "Tiempo (ns)");
        System.out.printf("%-18s %14d %14d %14d%n",
                "Bubble Sort", bubbleComparaciones, bubbleIntercambios,
                tiempoBubble);
        System.out.printf("%-18s %14d %14d %14d%n",
                "Selection Sort", selectionComparaciones, selectionIntercambios,
                tiempoSelection);
    }
}
