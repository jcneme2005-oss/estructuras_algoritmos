import java.util.Arrays;

public class ejercicio7_1 {

    /**
     * Crea y devuelve un nuevo arreglo con los elementos en orden inverso.
     * El arreglo original permanece sin cambios; a cambio, se necesita
     * memoria adicional para almacenar la copia.
     */
    public static int[] invertirConAuxiliar(int[] vector) {
        // Reserva un nuevo arreglo del mismo tamaño que el original.
        int[] invertido = new int[vector.length];

        // Recorre el arreglo original y coloca cada elemento en posición inversa.
        for (int i = 0; i < vector.length; i++) {
            /*
             * El último elemento va a la posición 0, el penúltimo a la 1,
             * y así sucesivamente. La fórmula calcula la posición opuesta
             * de i dentro del arreglo.
             */
            int indiceInverso = vector.length - 1 - i;
            invertido[i] = vector[indiceInverso];
        }

        // Devuelve la copia invertida; vector no fue modificado.
        return invertido;
    }

    /**
     * Invierte directamente el arreglo recibido.
     * Modifica el arreglo original y no crea una copia de tamaño proporcional
     * a su longitud.
     */
    public static void invertirInPlace(int[] vector) {
        int inicio = 0;
        int fin = vector.length - 1;

        /*
         * Continúa mientras los índices no se hayan cruzado.
         * Cuando inicio == fin, queda como máximo un elemento central,
         * que no necesita intercambiarse.
         */
        while (inicio < fin) {
            // Guarda temporalmente el valor para no perderlo al sobrescribir.
            int swap = vector[inicio];

            // Coloca el valor del extremo derecho en el extremo izquierdo.
            vector[inicio] = vector[fin];

            // Coloca el valor guardado en el extremo derecho.
            vector[fin] = swap;

            // Avanza ambos índices hacia el centro.
            inicio++;
            fin--;
        }
    }

    public static void main(String[] args) {
        int[] original = {1, 2, 3, 4, 5};

        long inicioAuxiliar = System.nanoTime();
        int[] resultadoAuxiliar = invertirConAuxiliar(original);
        long tiempoAuxiliar = System.nanoTime() - inicioAuxiliar;

        // Se usa una copia para que ambos métodos partan del mismo contenido.
        int[] vectorInPlace = Arrays.copyOf(original, original.length);

        long inicioInPlace = System.nanoTime();
        invertirInPlace(vectorInPlace);
        long tiempoInPlace = System.nanoTime() - inicioInPlace;

        System.out.println("Original: " + Arrays.toString(original));
        System.out.println("Con arreglo auxiliar: "
                + Arrays.toString(resultadoAuxiliar));
        System.out.println("In-place: " + Arrays.toString(vectorInPlace));
        System.out.println("Tiempo con auxiliar: " + tiempoAuxiliar + " ns");
        System.out.println("Tiempo in-place: " + tiempoInPlace + " ns");
    }
}