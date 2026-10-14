import java.util.Arrays;

public class ejercicio6_3 {

    public static void quickSort(int[] array, int inicio, int fin) {
        // Caso base: un rango vacío o de un elemento ya está ordenado.
        if (inicio >= fin) {
            return;
        }

        int indicePivote = partition(array, inicio, fin);

        // Ordena recursivamente los rangos a cada lado del pivote.
        quickSort(array, inicio, indicePivote - 1);
        quickSort(array, indicePivote + 1, fin);
    }

    public static int partition(int[] array, int inicio, int fin) {
        int pivote = array[inicio];
        int i = inicio + 1;
        int j = fin;

        // Reordena los elementos dentro del mismo arreglo usando dos índices.
        while (i <= j) {
            while (i <= j && array[i] <= pivote) {
                i++;
            }

            while (i <= j && array[j] > pivote) {
                j--;
            }

            if (i < j) {
                int temporal = array[i];
                array[i] = array[j];
                array[j] = temporal;
                i++;
                j--;
            }
        }

        /*
         * j marca el índice de partición: se intercambia allí el pivote
         * con el elemento que ocupa esa posición. Así, el pivote queda
         * entre los elementos menores o iguales y los elementos mayores.
         */
        array[inicio] = array[j];
        array[j] = pivote;

        System.out.println("Pivote elegido: " + pivote);
        System.out.print("Subarreglo izquierdo: ");
        imprimirRango(array, inicio, j - 1);
        System.out.print("Subarreglo derecho: ");
        imprimirRango(array, j + 1, fin);
        System.out.println("Vector tras posicionar el pivote: "
                + Arrays.toString(array));
        System.out.println();

        return j;
    }

    // Imprime un rango directamente, sin crear un arreglo auxiliar.
    private static void imprimirRango(int[] array, int desde, int hasta) {
        System.out.print("[");
        for (int indice = desde; indice <= hasta; indice++) {
            if (indice > desde) {
                System.out.print(", ");
            }
            System.out.print(array[indice]);
        }
        System.out.println("]");
    }

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};

        long inicio = System.nanoTime();
        quickSort(array, 0, array.length - 1);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Vector ordenado: " + Arrays.toString(array));
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}