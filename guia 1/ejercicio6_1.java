/**
 * Compara dos vectores de enteros.
 *
 * Usa O(1) de memoria auxiliar, ya que solo necesita un índice y no crea
 * estructuras adicionales que dependan del tamaño de los vectores.
 * Si los vectores tienen longitudes diferentes, la comparación retorna false.
 */
public class ejercicio6_1 {

    /**
     * Comprueba si dos vectores tienen la misma longitud y los mismos valores
     * en cada posición.
     *
     * @param v1 primer vector
     * @param v2 segundo vector
     * @return true si los vectores son iguales; false en caso contrario
     */
    public static boolean sonIguales(int[] v1, int[] v2) {
        // Si las longitudes difieren, los vectores no pueden ser iguales.
        if (v1.length != v2.length) {
            return false;
        }

        for (int i = 0; i < v1.length; i++) {
            // Corta la comparación en cuanto encuentra valores diferentes.
            if (v1[i] != v2[i]) {
                return false;
            }
        }

        return true;
    }

    public static void main(String[] args) {
        int[] v1 = {2, 4, 6, 8, 10};
        int[] v2 = {2, 4, 6, 8, 10};

        long inicio = System.nanoTime();
        boolean iguales = sonIguales(v1, v2);
        long tiempoTranscurrido = System.nanoTime() - inicio;

        System.out.println("¿Los vectores son iguales? " + iguales);
        System.out.println("Tiempo de ejecución: "
                + tiempoTranscurrido + " ns");
    }
}