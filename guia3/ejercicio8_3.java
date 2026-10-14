import java.util.Arrays;

public class ejercicio8_3 {

    public static void mergeSort(int[] array, int inicio, int fin) {
        /*
         * Caso base: con un elemento o ninguno, el rango ya está ordenado.
         * Si tiene un único elemento, se informa que se llegó a una hoja.
         */
        if (inicio >= fin) {
            if (inicio == fin) {
                System.out.println("Sublista de tamaño 1: [" + array[inicio] + "]");
            }
            return;
        }

        // Calcula el índice central para dividir el rango en dos mitades.
        int medio = (inicio + fin) / 2;
        System.out.println("Partición [" + inicio + ", " + fin
                + "] en medio " + medio);

        // Ordena recursivamente primero la mitad izquierda y luego la derecha.
        mergeSort(array, inicio, medio);
        mergeSort(array, medio + 1, fin);

        // Combina las dos mitades, que ya están ordenadas.
        merge(array, inicio, medio, fin);
    }

    public static void merge(int[] array, int inicio, int medio, int fin) {
        // Copia las mitades para poder compararlas sin perder sus valores.
        int[] izquierda = Arrays.copyOfRange(array, inicio, medio + 1);
        int[] derecha = Arrays.copyOfRange(array, medio + 1, fin + 1);

        System.out.println("Antes de combinar: izquierda "
                + Arrays.toString(izquierda) + ", derecha "
                + Arrays.toString(derecha));

        /*
         * i y j avanzan por las copias izquierda y derecha.
         * k indica la posición que se va llenando en el arreglo original.
         */
        int i = 0;
        int j = 0;
        int k = inicio;

        // Copia primero el menor de los dos valores actuales.
        while (i < izquierda.length && j < derecha.length) {
            if (izquierda[i] <= derecha[j]) {
                array[k] = izquierda[i];
                i++;
            } else {
                array[k] = derecha[j];
                j++;
            }
            k++;
        }

        // Copia los valores que queden en la mitad izquierda, si los hay.
        while (i < izquierda.length) {
            array[k] = izquierda[i];
            i++;
            k++;
        }

        // Copia los valores que queden en la mitad derecha, si los hay.
        while (j < derecha.length) {
            array[k] = derecha[j];
            j++;
            k++;
        }

        System.out.println("Después de combinar [" + inicio + ", " + fin
                + "]: " + Arrays.toString(
                        Arrays.copyOfRange(array, inicio, fin + 1)));
    }

    public static void main(String[] args) {
        int[] array = {38, 27, 43, 3, 9, 82, 10};

        long inicio = System.nanoTime();
        mergeSort(array, 0, array.length - 1);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Arreglo ordenado: " + Arrays.toString(array));
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}
