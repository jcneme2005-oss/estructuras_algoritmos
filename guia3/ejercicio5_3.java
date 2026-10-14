import java.util.Arrays;

public class ejercicio5_3 {

    public static void shellSortConPasadas(int[] array) {
        /*
         * gap determina la distancia entre los elementos que se comparan.
         * Comienza en la mitad del arreglo y se reduce hasta llegar a 1.
         */
        for (int gap = array.length / 2; gap > 0; gap = gap / 2) {

            for (int i = gap; i < array.length; i++) {
                // Guarda el valor que se insertará en su posición correcta.
                int clave = array[i];
                int j = i;

                /*
                 * Compara la clave con elementos anteriores separados por gap.
                 * Si array[j - gap] es mayor, lo desplaza hacia adelante para
                 * abrir espacio para la clave.
                 */
                while (j >= gap && array[j - gap] > clave) {
                    array[j] = array[j - gap];
                    j -= gap;
                }

                // Coloca la clave en el espacio que dejaron los desplazamientos.
                array[j] = clave;
            }

            System.out.println("Gap " + gap + ": " + Arrays.toString(array));
        }
    }

    public static void main(String[] args) {
        int[] array = {30, 15, 2, 21, 44, 8};

        long inicio = System.nanoTime();
        shellSortConPasadas(array);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("Arreglo ordenado: " + Arrays.toString(array));
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}
