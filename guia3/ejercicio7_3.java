import java.util.Arrays;

public class ejercicio7_3 {

    static int contadorLlamadas = 0;

    public static void quickSort(int[] array, int inicio, int fin) {
        contadorLlamadas++;

        /*
         * Condición de parada: un rango vacío (inicio > fin) o con un único
         * elemento (inicio == fin) ya está ordenado y no debe particionarse.
         */
        if (inicio >= fin) {
            return;
        }

        int indicePivote = partition(array, inicio, fin);

        /*
         * Cada llamada recursiva deja pendiente el procesamiento del otro
         * rango. En el peor caso, los marcos se acumulan en la pila hasta que
         * el rango se reduce a un elemento y se alcanza la condición de parada.
         */
        quickSort(array, inicio, indicePivote - 1);
        quickSort(array, indicePivote + 1, fin);
    }

    private static int partition(int[] array, int inicio, int fin) {
        int pivote = array[inicio];
        int indiceMenor = inicio;

        for (int j = inicio + 1; j <= fin; j++) {
            /*
             * Solo los valores menores que el pivote se mueven a su izquierda.
             * En el vector ascendente de prueba, array[inicio] es el mínimo:
             * ningún elemento cumple array[j] < pivote, así que indiceMenor
             * permanece en inicio.
             */
            if (array[j] < pivote) {
                indiceMenor++;

                int temporal = array[indiceMenor];
                array[indiceMenor] = array[j];
                array[j] = temporal;
            }
        }

        /*
         * El pivote queda en indiceMenor, que sigue siendo inicio en este caso.
         * Por eso la partición izquierda [inicio, inicio - 1] queda vacía
         * (tamaño 0), y la derecha conserva los otros n - 1 elementos.
         */
        array[inicio] = array[indiceMenor];
        array[indiceMenor] = pivote;

        return indiceMenor;
    }

    public static void main(String[] args) {
        int[] numeros = {1, 2, 3, 4, 5, 6, 7};

        contadorLlamadas = 0;
        long inicio = System.nanoTime();
        quickSort(numeros, 0, numeros.length - 1);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Vector ordenado: " + Arrays.toString(numeros));
        System.out.println("Llamadas a quickSort: " + contadorLlamadas);
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}
