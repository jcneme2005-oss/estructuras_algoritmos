public class ejercicio9_3 {

    private static long comparacionesBubble;
    private static long intercambiosBubble;
    private static long comparacionesSelection;
    private static long intercambiosSelection;
    private static long comparacionesInsertion;
    private static long desplazamientosInsertion;

    public static void bubbleSort(int[] array) {
        comparacionesBubble = 0;
        intercambiosBubble = 0;

        for (int i = 0; i < array.length - 1; i++) {
            for (int j = 0; j < array.length - 1 - i; j++) {
                comparacionesBubble++;

                if (array[j] > array[j + 1]) {
                    /*
                     * El temporal conserva el valor izquierdo mientras
                     * se intercambian los dos valores vecinos.
                     */
                    int temporal = array[j];
                    array[j] = array[j + 1];
                    array[j + 1] = temporal;
                    intercambiosBubble++;
                }
            }
        }
    }

    public static void selectionSort(int[] array) {
        comparacionesSelection = 0;
        intercambiosSelection = 0;

        for (int i = 0; i < array.length - 1; i++) {
            int indiceMinimo = i;

            for (int j = i + 1; j < array.length; j++) {
                comparacionesSelection++;

                if (array[j] < array[indiceMinimo]) {
                    indiceMinimo = j;
                }
            }

            if (indiceMinimo != i) {
                /*
                 * El temporal guarda el valor de la posición actual para
                 * colocar allí el mínimo encontrado sin perder ese valor.
                 */
                int temporal = array[i];
                array[i] = array[indiceMinimo];
                array[indiceMinimo] = temporal;
                intercambiosSelection++;
            }
        }
    }

    public static void insertionSort(int[] array) {
        comparacionesInsertion = 0;
        desplazamientosInsertion = 0;

        for (int i = 1; i < array.length; i++) {
            /*
             * clave conserva el elemento que se insertará. Es necesario
             * guardarlo antes de desplazar otros valores sobre su posición.
             */
            int clave = array[i];
            int j = i - 1;

            while (j >= 0) {
                comparacionesInsertion++;

                if (array[j] > clave) {
                    // Desplaza un elemento mayor una posición hacia la derecha.
                    array[j + 1] = array[j];
                    desplazamientosInsertion++;
                    j--;
                } else {
                    break;
                }
            }

            // Inserta la clave en el espacio que dejaron los desplazamientos.
            array[j + 1] = clave;
        }
    }

    public static void main(String[] args) {
        int[] original = {64, 25, 12, 22, 11, 90, 34};

        /*
         * Cada algoritmo modifica el arreglo que recibe. Las copias hacen
         * que todos empiecen con los mismos valores y en el mismo orden.
         */
        int[] copia1 = original.clone();
        int[] copia2 = original.clone();
        int[] copia3 = original.clone();

        long inicio = System.nanoTime();
        bubbleSort(copia1);
        long tiempoBubble = System.nanoTime() - inicio;

        inicio = System.nanoTime();
        selectionSort(copia2);
        long tiempoSelection = System.nanoTime() - inicio;

        inicio = System.nanoTime();
        insertionSort(copia3);
        long tiempoInsertion = System.nanoTime() - inicio;

        System.out.printf("%-18s %14s %20s %14s%n",
                "Algoritmo", "Comparaciones", "Swaps/shifts", "Tiempo (ns)");
        System.out.printf("%-18s %14d %20d %14d%n",
                "Bubble Sort", comparacionesBubble, intercambiosBubble,
                tiempoBubble);
        System.out.printf("%-18s %14d %20d %14d%n",
                "Selection Sort", comparacionesSelection, intercambiosSelection,
                tiempoSelection);
        System.out.printf("%-18s %14d %20d %14d%n",
                "Insertion Sort", comparacionesInsertion,
                desplazamientosInsertion, tiempoInsertion);
    }
}